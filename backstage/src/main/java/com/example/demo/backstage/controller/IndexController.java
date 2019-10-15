package com.example.demo.backstage.controller;

import com.example.demo.backstage.pojo.Result;
import com.example.demo.backstage.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lala
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    IndexService indexService;


    @PostMapping("/activity")
    public Result userLogin() {
        Result result = indexService.findActivity();
        return result;
    }
}
