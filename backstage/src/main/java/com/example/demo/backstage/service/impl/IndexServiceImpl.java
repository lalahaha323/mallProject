package com.example.demo.backstage.service.impl;

import com.example.demo.backstage.mapper.IndexMapper;
import com.example.demo.backstage.pojo.Activity;
import com.example.demo.backstage.pojo.Result;
import com.example.demo.backstage.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lala
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexMapper indexMapper;

    @Override
    public Result findActivity() {
        Result result = new Result();
        Activity activity = indexMapper.findActivity();
        result.setCode(23);
        result.setMsg("发送首页成功");
        result.setData(activity);
        return result;
    }
}
