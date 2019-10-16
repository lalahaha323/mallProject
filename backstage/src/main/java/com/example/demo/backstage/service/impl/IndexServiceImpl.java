package com.example.demo.backstage.service.impl;

import com.example.demo.backstage.mapper.IndexMapper;
import com.example.demo.backstage.mapper.RedisMapper;
import com.example.demo.backstage.pojo.Activity;
import com.example.demo.backstage.pojo.IndexProduct;
import com.example.demo.backstage.pojo.Result;
import com.example.demo.backstage.pojo.Shop;
import com.example.demo.backstage.service.IndexService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.SysexMessage;
import javax.swing.plaf.synth.SynthUI;
import java.util.*;

/**
 * @author lala
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    RedisMapper redisMapper;
    @Autowired
    IndexMapper indexMapper;

    @Override
    public Result findActivity() {
        Result result = new Result();
        HashMap<String,String> map = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        String activity_name = "activity";
        String name = "activity:name:";
        String pic = "activity:pic:";
        String link = "activity:link:";
//                map.put("activity:name:1","一周年");
//                map.put("activity:name:2","二周年");
//                map.put("activity:name:3","三周年");
//        map.put("activity:name:4","4周年");
//        map.put("activity:name:5","5周年");
//                map.put("activity:pic:1","xx");
//                map.put("activity:pic:2","xx");
//                map.put("activity:pic:3","xx");
//        map.put("activity:pic:4","xx");
//        map.put("activity:pic:5","xx");
//                map.put("activity:link:1","xx");
//                map.put("activity:link:2","xx");
//                map.put("activity:link:3","xx");
//        map.put("activity:link:4","xx");
//        map.put("activity:link:5","xx");
//                redisMapper.putMap("activity",map);
        Long number = redisMapper.getNumber(activity_name) / 3;//一共有多少个活动
        if(number <= 4) {
            for(int i = 1; i <= number; i++) {
                Activity activity = new Activity();
                activity.setId(i);
                activity.setName(redisMapper.getMapValue(activity_name, name + i));
                activity.setPic(redisMapper.getMapValue(activity_name, pic + i));
                activity.setLink(redisMapper.getMapValue(activity_name, link + i));
                arrayList.add(activity);
            }
            System.out.println("lala");
        } else {
            for(int i = 1; i <= 4; i++) {
                Activity activity = new Activity();
                activity.setId(i);
                activity.setName(redisMapper.getMapValue(activity_name, name + i));
                activity.setPic(redisMapper.getMapValue(activity_name, pic + i));
                activity.setLink(redisMapper.getMapValue(activity_name, link + i));
                arrayList.add(activity);
            }
        }
        result.setCode(23);
        result.setMsg("发送首页活动成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findShop() {
        Result result = new Result();
        ArrayList<Shop> arraylist = indexMapper.findShop();
        result.setCode(24);
        result.setMsg("发送首页商家成功");
        result.setData(arraylist);
        return result;
    }

    @Override
    public Result findTops() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findTops();
        result.setCode(25);
        result.setMsg("发送上装销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findPants() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findPants();
        result.setCode(26);
        result.setMsg("发送裤子销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findSkirts() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findSkirts();
        result.setCode(27);
        result.setMsg("发送裙子销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findShoes() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findShoes();
        result.setCode(28);
        result.setMsg("发送鞋子销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findPhones() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findPhones();
        result.setCode(29);
        result.setMsg("发送手机销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findComputers() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findComputers();
        result.setCode(30);
        result.setMsg("发送电脑销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

    @Override
    public Result findSnacks() {
        Result result = new Result();
        ArrayList<IndexProduct> arrayList = indexMapper.findSnacks();
        result.setCode(31);
        result.setMsg("发送零食销量排行前5成功");
        result.setData(arrayList);
        return result;
    }

}
