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


    //返回首页的活动
    @PostMapping("/activity")
    public Result findActivity() {
        Result result = indexService.findActivity();
        return result;
    }

    //返回首页展示的每日推荐的店铺
    @PostMapping("/shop")
    public Result findShop() {
        Result result = indexService.findShop();
        return result;
    }

    //返回销量前5的上装
    @PostMapping("/topsSort")
    public Result findTops() {
        Result result = indexService.findTops();
        return result;
    }

    //返回销量前5的裤子
    @PostMapping("/pantsSort")
    public Result findPants() {
        Result result = indexService.findPants();
        return result;
    }

    //返回销量前5的裙子
    @PostMapping("/skirtsSort")
    public Result findSkirts() {
        Result result = indexService.findSkirts();
        return result;
    }

    //返回销量前5的鞋子
    @PostMapping("/shoesSort")
    public Result findShoes() {
        Result result = indexService.findShoes();
        return result;
    }

    //返回销量前5的手机
    @PostMapping("/phonesSort")
    public Result findPhones() {
        Result result = indexService.findPhones();
        return result;
    }

    //返回销量前5的电脑
    @PostMapping("/computersSort")
    public Result findComputers() {
        Result result = indexService.findComputers();
        return result;
    }

    //返回销量前5的零食
    @PostMapping("/snacksSort")
    public Result findSnacks() {
        Result result = indexService.findSnacks();
        return result;
    }
}
