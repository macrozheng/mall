package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SmsGroupTeam implements Serializable {
    private Long id;

    @ApiModelProperty(value = "拼团活动id")
    private Long groupActivityId;

    @ApiModelProperty(value = "拼团商品id")
    private Long groupProductId;

    @ApiModelProperty(value = "团号")
    private String teamNumber;

    @ApiModelProperty(value = "团长id")
    private Long leaderId;

    @ApiModelProperty(value = "团长名称")
    private String leaderName;

    @ApiModelProperty(value = "团长头像")
    private String leaderIcon;

    @ApiModelProperty(value = "成团人数")
    private Integer groupCount;

    @ApiModelProperty(value = "当前参与人数")
    private Integer joinCount;

    @ApiModelProperty(value = "过期时间")
    private Date expireTime;

    @ApiModelProperty(value = "状态：0->进行中；1->已成团；2->已过期；3->已取消")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "成团时间")
    private Date completeTime;

    @ApiModelProperty(value = "取消时间")
    private Date cancelTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupActivityId() {
        return groupActivityId;
    }

    public void setGroupActivityId(Long groupActivityId) {
        this.groupActivityId = groupActivityId;
    }

    public Long getGroupProductId() {
        return groupProductId;
    }

    public void setGroupProductId(Long groupProductId) {
        this.groupProductId = groupProductId;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderIcon() {
        return leaderIcon;
    }

    public void setLeaderIcon(String leaderIcon) {
        this.leaderIcon = leaderIcon;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Integer getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(Integer joinCount) {
        this.joinCount = joinCount;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", groupActivityId=").append(groupActivityId);
        sb.append(", groupProductId=").append(groupProductId);
        sb.append(", teamNumber=").append(teamNumber);
        sb.append(", leaderId=").append(leaderId);
        sb.append(", leaderName=").append(leaderName);
        sb.append(", leaderIcon=").append(leaderIcon);
        sb.append(", groupCount=").append(groupCount);
        sb.append(", joinCount=").append(joinCount);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", completeTime=").append(completeTime);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
