package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsIntegrationExpireSetting implements Serializable {
    private Long id;

    @ApiModelProperty(value = "过期类型：0->固定有效期；1->滚动有效期")
    private Integer expireType;

    @ApiModelProperty(value = "有效天数")
    private Integer validDays;

    @ApiModelProperty(value = "过期月份（固定有效期用）")
    private Integer expireMonth;

    @ApiModelProperty(value = "过期日期（固定有效期用）")
    private Integer expireDay;

    @ApiModelProperty(value = "过期年份偏移（固定有效期用，如1表示下一年）")
    private Integer expireYearOffset;

    @ApiModelProperty(value = "是否自动过期：0->否；1->是")
    private Integer autoExpire;

    @ApiModelProperty(value = "过期前几天通知")
    private Integer notifyBeforeDays;

    @ApiModelProperty(value = "状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExpireType() {
        return expireType;
    }

    public void setExpireType(Integer expireType) {
        this.expireType = expireType;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
    }

    public Integer getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(Integer expireDay) {
        this.expireDay = expireDay;
    }

    public Integer getExpireYearOffset() {
        return expireYearOffset;
    }

    public void setExpireYearOffset(Integer expireYearOffset) {
        this.expireYearOffset = expireYearOffset;
    }

    public Integer getAutoExpire() {
        return autoExpire;
    }

    public void setAutoExpire(Integer autoExpire) {
        this.autoExpire = autoExpire;
    }

    public Integer getNotifyBeforeDays() {
        return notifyBeforeDays;
    }

    public void setNotifyBeforeDays(Integer notifyBeforeDays) {
        this.notifyBeforeDays = notifyBeforeDays;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", expireType=").append(expireType);
        sb.append(", validDays=").append(validDays);
        sb.append(", expireMonth=").append(expireMonth);
        sb.append(", expireDay=").append(expireDay);
        sb.append(", expireYearOffset=").append(expireYearOffset);
        sb.append(", autoExpire=").append(autoExpire);
        sb.append(", notifyBeforeDays=").append(notifyBeforeDays);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
