package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 修改订单费用信息参数
 * Created by macro on 2018/10/29.
 */ //封装订单修改所需信息的对象
@Getter
@Setter
public class OmsMoneyInfoParam {
    @ApiModelProperty("订单ID")//ApiModelProperty注解用于swagger的解释
    private Long orderId;
    @ApiModelProperty("运费金额")//运费
    private BigDecimal freightAmount;
    @ApiModelProperty("管理员后台调整订单所使用的折扣金额")
    private BigDecimal discountAmount;//折扣
    @ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    private Integer status;
}
