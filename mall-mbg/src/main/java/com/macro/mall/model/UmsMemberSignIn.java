package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsMemberSignIn implements Serializable {
    private Long id;

    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    @ApiModelProperty(value = "签到日期")
    private Date signInDate;

    @ApiModelProperty(value = "获得积分数")
    private Integer integration;

    @ApiModelProperty(value = "连续签到天数")
    private Integer continueDays;

    @ApiModelProperty(value = "是否额外奖励：0->否；1->是")
    private Integer isExtra;

    @ApiModelProperty(value = "额外奖励积分")
    private Integer extraIntegration;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    public Integer getIntegration() {
        return integration;
    }

    public void setIntegration(Integer integration) {
        this.integration = integration;
    }

    public Integer getContinueDays() {
        return continueDays;
    }

    public void setContinueDays(Integer continueDays) {
        this.continueDays = continueDays;
    }

    public Integer getIsExtra() {
        return isExtra;
    }

    public void setIsExtra(Integer isExtra) {
        this.isExtra = isExtra;
    }

    public Integer getExtraIntegration() {
        return extraIntegration;
    }

    public void setExtraIntegration(Integer extraIntegration) {
        this.extraIntegration = extraIntegration;
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
        sb.append(", memberId=").append(memberId);
        sb.append(", signInDate=").append(signInDate);
        sb.append(", integration=").append(integration);
        sb.append(", continueDays=").append(continueDays);
        sb.append(", isExtra=").append(isExtra);
        sb.append(", extraIntegration=").append(extraIntegration);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
