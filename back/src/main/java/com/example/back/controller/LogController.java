package com.example.back.controller;

import com.example.back.pojo.Result;
import com.example.back.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lala
 */
@RestController
@RequestMapping("/user")
public class LogController {

    @Autowired
    private LogService logService;

    //登录
    @PostMapping("/login")
    public Result userLogin(@RequestBody HashMap<Object, Object> map) {
        Result result = new Result();
        String user_name = (String)(map.get("user_name"));
        String user_password = (String)(map.get("user_password"));
        //subject代表当前用户
        Subject subject = SecurityUtils.getSubject();
        //用请求的用户名和密码创建UsernamePasswordToken(此类来自shiro包下)
        UsernamePasswordToken token = new UsernamePasswordToken(user_name, user_password);
        try {
            //shiro帮我们匹配密码什么的，我们只需要把东西传给它，它会根据我们在UserRealm里认证方法设置的来验证
            //调用subject.login进行验证，验证不通过会抛出AuthenticationException异常，然后自定义返回信息
            subject.login(token);
        } catch (AuthenticationException e) {
            //账号不存在
            result.setCode(43);
            result.setMsg("登录失败");
            result.setData(null);
            return result;
        }
        result.setCode(22);
        result.setMsg("登录成功");
        result.setData(map);
        return result;
    }


    //发送验证码
    @PostMapping("/sendSecurityCode")
    public Result getSecurityCode(@RequestBody HashMap<Object, Object> map) {
        String user_email = (String)(map.get("user_email"));
        Result result = logService.findEmail(user_email);
        return result;
    }
    //注册
    @PostMapping("/register")
    public Result userRegister(@RequestBody HashMap<Object, Object> map) {
        Result result = logService.userRegister(map);
        return result;
    }

}
