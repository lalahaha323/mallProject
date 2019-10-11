package com.example.demo.backstage.config;

import com.example.demo.backstage.mapper.LogMapper;
import com.example.demo.backstage.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * @author lala
 */
public class MyRealm extends AuthorizingRealm {

    //用户登录次数计数 redisKey前缀
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";
    //用户登录是否被锁定 一小时 redisKey前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LogMapper logMapper;

    /**
     * 用户授权
     * 用户请求有权限要求的接口时要经过此认证
     * 例如我在某个Controller的方法上加了注解@RequiresPermissions(value = "permis[get]"),
     * 那么该用户的角色拥有的资源必须要包含“permis[get]”权限才能访问此接口,
     * "permis[get]"对应文章开头说的表结构那里的系统资源表中的permission_code
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        //添加角色和权限
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        //获取用户名，此用户名实在登录接口里new UsernamePasswordToken()时设置的
//        String username = (String)principalCollection.getPrimaryPrincipal();
//        User user = logMapper.getByUserName(username);
////        for(User_Role user_role : logMapper.getRoles(user.getId())){
////            for(Role_Permission role_permission: logMapper.getPermissions(user_role.getRoleId())){
////                Permission permission = logMapper.getById(role_permission.getPermissionId());
////                authorizationInfo.addStringPermission(permission.getName());
////            }
////        }
//        return authorizationInfo;
        return null;
    }


    /**
     * 用户认证
     * 调用了subject.login()方法就会进入到这个方法进行具体登录验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        /**
         * 设置key的值为value
         *  如果key不存在，添加key保存值为value
         *  如果key存在，对value进行覆盖
         */
        //用户输入的用户名和密码
        String user_name = token.getPrincipal().toString();
        //数据库找
        User user = logMapper.getByUserName(user_name);
        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT + user_name, 1);
        if (Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + user_name)) >= 5) {
            opsForValue.set(SHIRO_IS_LOCK + user_name, "LOCK");
            stringRedisTemplate.expire(SHIRO_IS_LOCK + user_name, 1, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + user_name))) {
            System.out.println("禁止");
            throw new DisabledAccountException("由于密码输入错误次数大于5次，帐号已经禁止登录！");
        }
        if (user != null) {
            Object principal = user.getName();
            Object credentials = user.getPassword();
            ByteSource salt = ByteSource.Util.bytes(user.getName());
            String realmName = this.getName();
            //SimpleAuthenticationInfo会默认将数据库查出来的密码与用户输入的密码对比，如果相同则通过，否则抛出异常
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    principal,
                    credentials,
                    salt,
                    realmName);
            //opsForValue.set(SHIRO_LOGIN_COUNT+user_name, "0");
            return authenticationInfo;
        } else {
            throw new UnknownAccountException("用户不存在");
        }
    }
}

