package com.example.demo.backstage.mapper;

import com.example.demo.backstage.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author lala
 */

@Repository
@Mapper
public interface IndexMapper {
    public ArrayList<Shop> findShop();
}
