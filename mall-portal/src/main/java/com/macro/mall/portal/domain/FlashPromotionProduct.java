package com.macro.mall.portal.domain;

import com.macro.mall.model.PmsProduct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 秒杀信息和商品对象封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class FlashPromotionProduct extends PmsProduct{
    //秒杀价格
    private BigDecimal flashPromotionPrice;
    //用于秒杀到数量
    private Integer flashPromotionCount;
    //秒杀限购数量
    private Integer flashPromotionLimit;
}
