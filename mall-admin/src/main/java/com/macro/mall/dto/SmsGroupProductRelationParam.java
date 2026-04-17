package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
public class SmsGroupProductRelationParam {
    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("拼团价格")
    private BigDecimal groupPrice;

    @ApiModelProperty("原价")
    private BigDecimal originalPrice;

    @ApiModelProperty("拼团库存")
    private Integer groupStock;

    @ApiModelProperty("排序")
    private Integer sort;
}
