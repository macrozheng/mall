package com.macro.mall.promotion.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class PromotionActivityParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动名称")
    @NotEmpty(message = "活动名称不能为空")
    private String name;

    @ApiModelProperty(value = "活动类型：1->满减；2->满折；3->第N件优惠；4->套餐价；5->SKU特价；6->会员专享")
    @NotNull(message = "活动类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "使用平台：0->全部；1->移动；2->PC")
    private Integer platform;

    @ApiModelProperty(value = "活动开始时间")
    @NotNull(message = "活动开始时间不能为空")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    @NotNull(message = "活动结束时间不能为空")
    private Date endTime;

    @ApiModelProperty(value = "状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "优先级，数值越大优先级越高")
    private Integer priority;

    @ApiModelProperty(value = "是否可叠加：0->不可叠加；1->可叠加")
    private Boolean stackable;

    @ApiModelProperty(value = "互斥活动ID列表，逗号分隔")
    private String exclusiveWith;

    @ApiModelProperty(value = "适用范围：0->全场通用；1->指定分类；2->指定商品；3->指定品牌")
    private Integer useType;

    @ApiModelProperty(value = "最低订单金额门槛")
    private BigDecimal minOrderAmount;

    @ApiModelProperty(value = "最大优惠金额上限")
    private BigDecimal maxDiscountAmount;

    @ApiModelProperty(value = "每人限享次数")
    private Integer perLimit;

    @ApiModelProperty(value = "活动总限享次数")
    private Integer totalLimit;

    @ApiModelProperty(value = "活动描述")
    private String description;

    @ApiModelProperty(value = "活动规则列表")
    private List<PromotionRuleParam> rules;

    @ApiModelProperty(value = "适用商品ID列表")
    private List<Long> productIds;

    @ApiModelProperty(value = "适用分类ID列表")
    private List<Long> categoryIds;

    @ApiModelProperty(value = "适用品牌ID列表")
    private List<Long> brandIds;

    @ApiModelProperty(value = "SKU特价列表（活动类型为5时使用）")
    private List<SkuSpecialPriceParam> skuSpecialPrices;

    @ApiModelProperty(value = "会员价列表（活动类型为6时使用）")
    private List<MemberPriceParam> memberPrices;

    @ApiModelProperty(value = "套餐列表（活动类型为4时使用）")
    private List<PackageParam> packages;
}
