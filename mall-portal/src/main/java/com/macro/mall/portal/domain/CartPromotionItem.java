package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsCartItem;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 带促销信息的购物车商品封装
 * Created by macro on 2018/8/27.
 */
@Getter
@Setter
public class CartPromotionItem extends OmsCartItem{
    private String promotionMessage;
    private BigDecimal reduceAmount;
    private Integer realStock;
    private Integer integration;
    private Integer growth;
}
