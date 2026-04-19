package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 拼团下单返回结果
 */
public class GroupBuyOrderResult {

    @ApiModelProperty(value = "团ID")
    private Long teamId;

    @ApiModelProperty(value = "团编号(用于分享)")
    private String teamNo;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "参团记录ID")
    private Long recordId;

    @ApiModelProperty(value = "应付金额")
    private java.math.BigDecimal payAmount;

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public String getTeamNo() { return teamNo; }
    public void setTeamNo(String teamNo) { this.teamNo = teamNo; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getOrderSn() { return orderSn; }
    public void setOrderSn(String orderSn) { this.orderSn = orderSn; }
    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }
    public java.math.BigDecimal getPayAmount() { return payAmount; }
    public void setPayAmount(java.math.BigDecimal payAmount) { this.payAmount = payAmount; }
}
