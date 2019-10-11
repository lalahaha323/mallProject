package com.example.demo.backstage.mapper;

import com.example.demo.backstage.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author lala
 */

@Repository
@Mapper
public interface IndexMapper {
    public Activity findActivity();
}
