package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsGroupBuyRecord implements Serializable {
    private Long id;

    @ApiModelProperty(value = "团ID")
    private Long teamId;

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "冗余昵称")
    private String memberNickname;

    @ApiModelProperty(value = "冗余头像")
    private String memberIcon;

    @ApiModelProperty(value = "是否团长:0->否;1->是")
    private Integer isLeader;

    @ApiModelProperty(value = "订单ID")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "参团状态:0->待支付;1->已支付待成团;2->成团成功;3->成团失败已退款;4->已取消")
    private Integer joinStatus;

    @ApiModelProperty(value = "参团时间")
    private Date joinTime;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "结算时间")
    private Date finishTime;

    private static final long serialVersionUID = 1L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public String getMemberNickname() { return memberNickname; }
    public void setMemberNickname(String memberNickname) { this.memberNickname = memberNickname; }
    public String getMemberIcon() { return memberIcon; }
    public void setMemberIcon(String memberIcon) { this.memberIcon = memberIcon; }
    public Integer getIsLeader() { return isLeader; }
    public void setIsLeader(Integer isLeader) { this.isLeader = isLeader; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getOrderSn() { return orderSn; }
    public void setOrderSn(String orderSn) { this.orderSn = orderSn; }
    public BigDecimal getPayAmount() { return payAmount; }
    public void setPayAmount(BigDecimal payAmount) { this.payAmount = payAmount; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Integer getJoinStatus() { return joinStatus; }
    public void setJoinStatus(Integer joinStatus) { this.joinStatus = joinStatus; }
    public Date getJoinTime() { return joinTime; }
    public void setJoinTime(Date joinTime) { this.joinTime = joinTime; }
    public Date getPayTime() { return payTime; }
    public void setPayTime(Date payTime) { this.payTime = payTime; }
    public Date getFinishTime() { return finishTime; }
    public void setFinishTime(Date finishTime) { this.finishTime = finishTime; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [id=").append(id);
        sb.append(", teamId=").append(teamId);
        sb.append(", memberId=").append(memberId);
        sb.append(", isLeader=").append(isLeader);
        sb.append(", joinStatus=").append(joinStatus);
        sb.append("]");
        return sb.toString();
    }
}
