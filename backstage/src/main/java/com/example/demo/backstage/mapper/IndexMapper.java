package com.example.demo.backstage.mapper;

<<<<<<< HEAD
=======
import com.example.demo.backstage.pojo.Activity;
>>>>>>> lcl
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
