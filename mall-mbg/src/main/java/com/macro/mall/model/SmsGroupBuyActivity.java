package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SmsGroupBuyActivity implements Serializable {
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String title;

    @ApiModelProperty(value = "活动副标题/描述")
    private String subTitle;

    @ApiModelProperty(value = "活动主图")
    private String pic;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "成团人数(含团长)")
    private Integer groupSize;

    @ApiModelProperty(value = "成团有效时长(小时)")
    private Integer validHours;

    @ApiModelProperty(value = "每个会员的参团次数限制,0=不限")
    private Integer limitPerMember;

    @ApiModelProperty(value = "是否支持虚拟成团:0->否;1->是")
    private Integer virtualGroupFlag;

    @ApiModelProperty(value = "团长是否免单:0->否;1->是")
    private Integer allowLeaderFree;

    @ApiModelProperty(value = "上下线状态:0->下线;1->上线")
    private Integer status;

    @ApiModelProperty(value = "累计开团数")
    private Integer totalGroupCount;

    @ApiModelProperty(value = "累计成团数")
    private Integer successGroupCount;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSubTitle() { return subTitle; }
    public void setSubTitle(String subTitle) { this.subTitle = subTitle; }
    public String getPic() { return pic; }
    public void setPic(String pic) { this.pic = pic; }
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    public Integer getGroupSize() { return groupSize; }
    public void setGroupSize(Integer groupSize) { this.groupSize = groupSize; }
    public Integer getValidHours() { return validHours; }
    public void setValidHours(Integer validHours) { this.validHours = validHours; }
    public Integer getLimitPerMember() { return limitPerMember; }
    public void setLimitPerMember(Integer limitPerMember) { this.limitPerMember = limitPerMember; }
    public Integer getVirtualGroupFlag() { return virtualGroupFlag; }
    public void setVirtualGroupFlag(Integer virtualGroupFlag) { this.virtualGroupFlag = virtualGroupFlag; }
    public Integer getAllowLeaderFree() { return allowLeaderFree; }
    public void setAllowLeaderFree(Integer allowLeaderFree) { this.allowLeaderFree = allowLeaderFree; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public Integer getTotalGroupCount() { return totalGroupCount; }
    public void setTotalGroupCount(Integer totalGroupCount) { this.totalGroupCount = totalGroupCount; }
    public Integer getSuccessGroupCount() { return successGroupCount; }
    public void setSuccessGroupCount(Integer successGroupCount) { this.successGroupCount = successGroupCount; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", status=").append(status);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", groupSize=").append(groupSize);
        sb.append(", validHours=").append(validHours);
        sb.append("]");
        return sb.toString();
    }
}
