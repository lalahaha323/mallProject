package com.example.demo.backstage.mapper;

import com.example.demo.backstage.pojo.Activity;
import com.example.demo.backstage.pojo.IndexProduct;
import com.example.demo.backstage.pojo.IndexProductShow;
import com.example.demo.backstage.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author lala
 */

@Repository
@Mapper
public interface IndexMapper {
    public ArrayList<Shop> findShop();

    public ArrayList<IndexProduct> findTops();

    public IndexProductShow getOne(@Param("product_shop_id") int product_shop_id,
                                      @Param("product_spu_id") int product_spu_id);

    public ArrayList<IndexProduct> findPants();
    public ArrayList<IndexProduct> findSkirts();
}
