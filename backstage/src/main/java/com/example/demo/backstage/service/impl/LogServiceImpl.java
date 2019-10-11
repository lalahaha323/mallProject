package com.example.demo.backstage.service.impl;



import com.example.demo.backstage.mapper.LogMapper;
import com.example.demo.backstage.mapper.RedisMapper;
import com.example.demo.backstage.pojo.RandomSecurityCode;
import com.example.demo.backstage.pojo.Result;
import com.example.demo.backstage.service.LogService;
import org.apache.commons.mail.SimpleEmail;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lala
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Autowired
    private RedisMapper redisMapper;

    @Override
    public Result findEmail(String user_email) {
        Result result = new Result();
        Integer count = logMapper.findEmail(user_email);
        if(count == 0) {
            //发送验证码
            SimpleEmail email = new SimpleEmail();
            try{
                email.setHostName("smtp.qq.com");
                email.setCharset("utf-8");
                //收件人
                email.addTo(user_email);
                email.setFrom("2665687186@qq.com", "mall");
                //需要认证信息,用户名,密码(授权码)
                email.setAuthentication("2665687186@qq.com", "mddnittagwwlecfd");
                email.setSSLOnConnect(true);
                String securityCode = RandomSecurityCode.getCode();
                email.setSubject("注册验证码");
                email.setMsg("尊敬的用户您好!\n注册验证码为:" + securityCode + "\n");
                email.send();
                result.setCode(44);
                result.setMsg("验证码发送成功");
                result.setData(null);
                redisMapper.setKey("securityCode", securityCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //失败
            result.setCode(40);
            result.setMsg("邮箱已经被注册");
            result.setData(null);
        }
        return result;
    }

    @Override
    public Result userRegister(Map<Object, Object> map) {
        Result result = new Result();
        //看用户名是否注册过
        String user_name = (String)(map.get("user_name"));
        String user_password = (String)(map.get("user_password"));
        String user_email = (String)(map.get("user_email"));
        String securityCode = redisMapper.getValue("securityCode");
        String salt = user_name;
        //验证码正确
        if(securityCode.equals(map.get("securityCode"))) {
            //看用户名是否注册过
            if(logMapper.findName(user_name) == 0) {
                //没有注册过
                Md5Hash md5Hash = new Md5Hash(user_password, salt, 2);
                logMapper.insertRegister(user_name, md5Hash.toString(), user_email);
                result.setCode(21);
                result.setMsg("注册成功");
                result.setData(null);
            } else {
                //注册过
                result.setCode(41);
                result.setMsg("用户名被注册");
                result.setData(null);
            }
        } else {
            //验证码错误，注册失败
            result.setCode(42);
            result.setMsg("验证码错误");
            result.setData(null);
        }
        return result;
    }
}


