package com.example.demo.backstage.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lala
 */

@Data
public class IndexProduct {
    private Integer monthSaleNumber;
    private Integer productShopId;
    private Integer productSpuId;

    private IndexProductShow indexProductShow;
}
