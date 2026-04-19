package com.macro.mall.portal.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * 参团入参
 */
public class GroupBuyJoinParam {

    @ApiModelProperty(value = "团编号", required = true)
    private String teamNo;

    @ApiModelProperty(value = "购买数量", required = true)
    private Integer quantity;

    @ApiModelProperty(value = "收货地址ID", required = true)
    private Long memberReceiveAddressId;

    public String getTeamNo() { return teamNo; }
    public void setTeamNo(String teamNo) { this.teamNo = teamNo; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Long getMemberReceiveAddressId() { return memberReceiveAddressId; }
    public void setMemberReceiveAddressId(Long memberReceiveAddressId) { this.memberReceiveAddressId = memberReceiveAddressId; }
}
