package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PromotionRuleParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "规则类型：1->满减阶梯；2->满折阶梯；3->第N件优惠；4->固定金额；5->固定折扣")
    private Integer ruleType;

    @ApiModelProperty(value = "门槛值（金额或数量）")
    private BigDecimal threshold;

    @ApiModelProperty(value = "优惠值（金额或折扣比例）")
    private BigDecimal discountValue;

    @ApiModelProperty(value = "优惠类型：1->金额减免；2->折扣比例")
    private Integer discountType;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
