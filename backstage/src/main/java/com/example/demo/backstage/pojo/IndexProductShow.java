package com.example.demo.backstage.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lala
 */

@Data
public class IndexProductShow {
    private String pic;
    private String title;
    private BigDecimal price;
    private String link;
}
