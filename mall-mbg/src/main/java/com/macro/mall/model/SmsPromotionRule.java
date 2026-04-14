package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsPromotionRule implements Serializable {
    private Long id;

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

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

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", ruleType=").append(ruleType);
        sb.append(", threshold=").append(threshold);
        sb.append(", discountValue=").append(discountValue);
        sb.append(", discountType=").append(discountType);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
