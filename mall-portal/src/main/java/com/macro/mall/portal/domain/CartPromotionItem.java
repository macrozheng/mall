package com.macro.mall.portal.domain;

import com.macro.mall.model.OmsCartItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 购物车中促销信息的封装
 * Created by macro on 2018/8/27.
 */
@Getter
@Setter
public class CartPromotionItem extends OmsCartItem{
    @ApiModelProperty("促销活动信息")
    private String promotionMessage;
    @ApiModelProperty("促销活动减去的金额，针对每个商品")
    private BigDecimal reduceAmount;
    @ApiModelProperty("剩余库存-锁定库存")
    private Integer realStock;
    @ApiModelProperty("购买商品赠送积分")
    private Integer integration;
    @ApiModelProperty("购买商品赠送成长值")
    private Integer growth;
}
