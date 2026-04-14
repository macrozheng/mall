package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsPromotionCalcLog implements Serializable {
    private Long id;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "计算类型：1->试算；2->下单计算")
    private Integer calcType;

    @ApiModelProperty(value = "原始金额")
    private BigDecimal originalAmount;

    @ApiModelProperty(value = "最终金额")
    private BigDecimal finalAmount;

    @ApiModelProperty(value = "总优惠金额")
    private BigDecimal totalDiscount;

    @ApiModelProperty(value = "计算详情（JSON格式）")
    private String calcDetail;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getCalcType() {
        return calcType;
    }

    public void setCalcType(Integer calcType) {
        this.calcType = calcType;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getCalcDetail() {
        return calcDetail;
    }

    public void setCalcDetail(String calcDetail) {
        this.calcDetail = calcDetail;
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
        sb.append(", orderSn=").append(orderSn);
        sb.append(", memberId=").append(memberId);
        sb.append(", calcType=").append(calcType);
        sb.append(", originalAmount=").append(originalAmount);
        sb.append(", finalAmount=").append(finalAmount);
        sb.append(", totalDiscount=").append(totalDiscount);
        sb.append(", calcDetail=").append(calcDetail);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
