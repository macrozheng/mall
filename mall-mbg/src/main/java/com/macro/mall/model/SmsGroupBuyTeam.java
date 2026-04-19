package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsGroupBuyTeam implements Serializable {
    private Long id;

    @ApiModelProperty(value = "团编号(对外分享)")
    private String teamNo;

    @ApiModelProperty(value = "拼团活动ID")
    private Long activityId;

    @ApiModelProperty(value = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品SKU ID")
    private Long productSkuId;

    @ApiModelProperty(value = "成团时的拼团价(快照)")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "团长会员ID")
    private Long leaderMemberId;

    @ApiModelProperty(value = "冗余团长昵称")
    private String leaderNickname;

    @ApiModelProperty(value = "目标成团人数")
    private Integer targetNum;

    @ApiModelProperty(value = "当前已参团人数")
    private Integer currentNum;

    @ApiModelProperty(value = "状态:0->进行中;1->成团;2->失败;3->已关闭")
    private Integer status;

    @ApiModelProperty(value = "开团时间")
    private Date startTime;

    @ApiModelProperty(value = "成团截止时间")
    private Date expireTime;

    @ApiModelProperty(value = "成团时间")
    private Date successTime;

    @ApiModelProperty(value = "关团时间")
    private Date closeTime;

    @ApiModelProperty(value = "是否虚拟成团")
    private Integer virtualFlag;

    private static final long serialVersionUID = 1L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTeamNo() { return teamNo; }
    public void setTeamNo(String teamNo) { this.teamNo = teamNo; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Long getProductSkuId() { return productSkuId; }
    public void setProductSkuId(Long productSkuId) { this.productSkuId = productSkuId; }
    public BigDecimal getGroupPrice() { return groupPrice; }
    public void setGroupPrice(BigDecimal groupPrice) { this.groupPrice = groupPrice; }
    public Long getLeaderMemberId() { return leaderMemberId; }
    public void setLeaderMemberId(Long leaderMemberId) { this.leaderMemberId = leaderMemberId; }
    public String getLeaderNickname() { return leaderNickname; }
    public void setLeaderNickname(String leaderNickname) { this.leaderNickname = leaderNickname; }
    public Integer getTargetNum() { return targetNum; }
    public void setTargetNum(Integer targetNum) { this.targetNum = targetNum; }
    public Integer getCurrentNum() { return currentNum; }
    public void setCurrentNum(Integer currentNum) { this.currentNum = currentNum; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public Date getExpireTime() { return expireTime; }
    public void setExpireTime(Date expireTime) { this.expireTime = expireTime; }
    public Date getSuccessTime() { return successTime; }
    public void setSuccessTime(Date successTime) { this.successTime = successTime; }
    public Date getCloseTime() { return closeTime; }
    public void setCloseTime(Date closeTime) { this.closeTime = closeTime; }
    public Integer getVirtualFlag() { return virtualFlag; }
    public void setVirtualFlag(Integer virtualFlag) { this.virtualFlag = virtualFlag; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [id=").append(id);
        sb.append(", teamNo=").append(teamNo);
        sb.append(", activityId=").append(activityId);
        sb.append(", status=").append(status);
        sb.append(", currentNum=").append(currentNum);
        sb.append(", targetNum=").append(targetNum);
        sb.append(", expireTime=").append(expireTime);
        sb.append("]");
        return sb.toString();
    }
}
