package com.example.demo.backstage.mapper;

<<<<<<< HEAD
=======
import com.example.demo.backstage.pojo.Activity;
<<<<<<< HEAD
>>>>>>> lcl
=======
import com.example.demo.backstage.pojo.IndexProduct;
import com.example.demo.backstage.pojo.IndexProductShow;
>>>>>>> lcl
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
    public ArrayList<IndexProduct> findShoes();
    public ArrayList<IndexProduct> findPhones();
    public ArrayList<IndexProduct> findComputers();
    public ArrayList<IndexProduct> findSnacks();
}
