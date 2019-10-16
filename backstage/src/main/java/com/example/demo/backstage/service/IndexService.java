package com.example.demo.backstage.service;

import com.example.demo.backstage.pojo.Activity;
import com.example.demo.backstage.pojo.Result;

/**
 * @author lala
 */

public interface IndexService {
    public Result findActivity();
    public Result findShop();
    public Result findTops();
    public Result findPants();
    public Result findSkirts();
    public Result findShoes();
    public Result findPhones();
    public Result findComputers();
    public Result findSnacks();
}
