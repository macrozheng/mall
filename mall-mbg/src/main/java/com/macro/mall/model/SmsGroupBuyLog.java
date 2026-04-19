package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SmsGroupBuyLog implements Serializable {
    private Long id;

    @ApiModelProperty(value = "操作会员ID")
    private Long memberId;

    @ApiModelProperty(value = "冗余昵称")
    private String memberNickname;

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "团ID")
    private Long teamId;

    @ApiModelProperty(value = "团编号")
    private String teamNo;

    @ApiModelProperty(value = "参团记录ID")
    private Long recordId;

    @ApiModelProperty(value = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "操作类型:1->开团;2->参团;3->支付成功;4->取消参团;5->成团;6->成团失败;7->退款完成;8->分享;9->系统补团;10->管理员强制关闭")
    private Integer operateType;

    @ApiModelProperty(value = "来源:0->用户;1->系统任务;2->支付回调;3->后台管理员")
    private Integer operateSource;

    @ApiModelProperty(value = "操作前状态")
    private Integer beforeStatus;

    @ApiModelProperty(value = "操作后状态")
    private Integer afterStatus;

    @ApiModelProperty(value = "详细描述/参数快照(JSON)")
    private String detail;

    @ApiModelProperty(value = "操作IP")
    private String ip;

    @ApiModelProperty(value = "操作时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }
    public String getMemberNickname() { return memberNickname; }
    public void setMemberNickname(String memberNickname) { this.memberNickname = memberNickname; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public String getTeamNo() { return teamNo; }
    public void setTeamNo(String teamNo) { this.teamNo = teamNo; }
    public Long getRecordId() { return recordId; }
    public void setRecordId(Long recordId) { this.recordId = recordId; }
    public String getOrderSn() { return orderSn; }
    public void setOrderSn(String orderSn) { this.orderSn = orderSn; }
    public Integer getOperateType() { return operateType; }
    public void setOperateType(Integer operateType) { this.operateType = operateType; }
    public Integer getOperateSource() { return operateSource; }
    public void setOperateSource(Integer operateSource) { this.operateSource = operateSource; }
    public Integer getBeforeStatus() { return beforeStatus; }
    public void setBeforeStatus(Integer beforeStatus) { this.beforeStatus = beforeStatus; }
    public Integer getAfterStatus() { return afterStatus; }
    public void setAfterStatus(Integer afterStatus) { this.afterStatus = afterStatus; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [id=").append(id);
        sb.append(", memberId=").append(memberId);
        sb.append(", teamId=").append(teamId);
        sb.append(", operateType=").append(operateType);
        sb.append(", operateSource=").append(operateSource);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}
