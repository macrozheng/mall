package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsGroupActivity implements Serializable {
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动类型：0->普通拼团；1->老带新拼团；2->新人团")
    private Integer activityType;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "成团人数")
    private Integer groupCount;

    @ApiModelProperty(value = "成团有效时间(小时)")
    private Integer groupValidTime;

    @ApiModelProperty(value = "每人限购数量")
    private Integer limitCount;

    @ApiModelProperty(value = "每人限参加次数")
    private Integer useLimitCount;

    @ApiModelProperty(value = "最大参与人数：0->不限制")
    private Integer maxJoinCount;

    @ApiModelProperty(value = "最低消费金额")
    private BigDecimal minAmount;

    @ApiModelProperty(value = "活动状态：0->关闭；1->开启")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String note;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    public Integer getGroupValidTime() {
        return groupValidTime;
    }

    public void setGroupValidTime(Integer groupValidTime) {
        this.groupValidTime = groupValidTime;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public Integer getUseLimitCount() {
        return useLimitCount;
    }

    public void setUseLimitCount(Integer useLimitCount) {
        this.useLimitCount = useLimitCount;
    }

    public Integer getMaxJoinCount() {
        return maxJoinCount;
    }

    public void setMaxJoinCount(Integer maxJoinCount) {
        this.maxJoinCount = maxJoinCount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", activityType=").append(activityType);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", groupCount=").append(groupCount);
        sb.append(", groupValidTime=").append(groupValidTime);
        sb.append(", limitCount=").append(limitCount);
        sb.append(", useLimitCount=").append(useLimitCount);
        sb.append(", maxJoinCount=").append(maxJoinCount);
        sb.append(", minAmount=").append(minAmount);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", sort=").append(sort);
        sb.append(", note=").append(note);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
