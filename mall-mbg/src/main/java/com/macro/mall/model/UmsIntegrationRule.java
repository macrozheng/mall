package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsIntegrationRule implements Serializable {
    private Long id;

    @ApiModelProperty(value = "规则类型：0->消费积分；1->签到积分；2->评价积分；3->注册积分；4->分享积分；5->生日积分")
    private Integer ruleType;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "最低消费金额（消费积分规则用）")
    private BigDecimal minAmount;

    @ApiModelProperty(value = "每消费多少金额获得1积分")
    private BigDecimal amountPerIntegration;

    @ApiModelProperty(value = "每消费金额获得的积分数")
    private Integer integrationPerAmount;

    @ApiModelProperty(value = "每单最高积分（NULL表示不限制）")
    private Integer maxIntegrationPerOrder;

    @ApiModelProperty(value = "基础积分（签到/评价等用）")
    private Integer baseIntegration;

    @ApiModelProperty(value = "连续签到额外积分")
    private Integer continueDaysIntegration;

    @ApiModelProperty(value = "最大连续签到天数（NULL表示不限制）")
    private Integer maxContinueDays;

    @ApiModelProperty(value = "带图评价额外积分")
    private Integer commentImageIntegration;

    @ApiModelProperty(value = "分享获得积分")
    private Integer shareIntegration;

    @ApiModelProperty(value = "生日赠送积分")
    private Integer birthdayIntegration;

    @ApiModelProperty(value = "注册赠送积分")
    private Integer registerIntegration;

    @ApiModelProperty(value = "规则生效开始时间")
    private Date startTime;

    @ApiModelProperty(value = "规则生效结束时间")
    private Date endTime;

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

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getAmountPerIntegration() {
        return amountPerIntegration;
    }

    public void setAmountPerIntegration(BigDecimal amountPerIntegration) {
        this.amountPerIntegration = amountPerIntegration;
    }

    public Integer getIntegrationPerAmount() {
        return integrationPerAmount;
    }

    public void setIntegrationPerAmount(Integer integrationPerAmount) {
        this.integrationPerAmount = integrationPerAmount;
    }

    public Integer getMaxIntegrationPerOrder() {
        return maxIntegrationPerOrder;
    }

    public void setMaxIntegrationPerOrder(Integer maxIntegrationPerOrder) {
        this.maxIntegrationPerOrder = maxIntegrationPerOrder;
    }

    public Integer getBaseIntegration() {
        return baseIntegration;
    }

    public void setBaseIntegration(Integer baseIntegration) {
        this.baseIntegration = baseIntegration;
    }

    public Integer getContinueDaysIntegration() {
        return continueDaysIntegration;
    }

    public void setContinueDaysIntegration(Integer continueDaysIntegration) {
        this.continueDaysIntegration = continueDaysIntegration;
    }

    public Integer getMaxContinueDays() {
        return maxContinueDays;
    }

    public void setMaxContinueDays(Integer maxContinueDays) {
        this.maxContinueDays = maxContinueDays;
    }

    public Integer getCommentImageIntegration() {
        return commentImageIntegration;
    }

    public void setCommentImageIntegration(Integer commentImageIntegration) {
        this.commentImageIntegration = commentImageIntegration;
    }

    public Integer getShareIntegration() {
        return shareIntegration;
    }

    public void setShareIntegration(Integer shareIntegration) {
        this.shareIntegration = shareIntegration;
    }

    public Integer getBirthdayIntegration() {
        return birthdayIntegration;
    }

    public void setBirthdayIntegration(Integer birthdayIntegration) {
        this.birthdayIntegration = birthdayIntegration;
    }

    public Integer getRegisterIntegration() {
        return registerIntegration;
    }

    public void setRegisterIntegration(Integer registerIntegration) {
        this.registerIntegration = registerIntegration;
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
        sb.append(", ruleType=").append(ruleType);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", status=").append(status);
        sb.append(", minAmount=").append(minAmount);
        sb.append(", amountPerIntegration=").append(amountPerIntegration);
        sb.append(", integrationPerAmount=").append(integrationPerAmount);
        sb.append(", maxIntegrationPerOrder=").append(maxIntegrationPerOrder);
        sb.append(", baseIntegration=").append(baseIntegration);
        sb.append(", continueDaysIntegration=").append(continueDaysIntegration);
        sb.append(", maxContinueDays=").append(maxContinueDays);
        sb.append(", commentImageIntegration=").append(commentImageIntegration);
        sb.append(", shareIntegration=").append(shareIntegration);
        sb.append(", birthdayIntegration=").append(birthdayIntegration);
        sb.append(", registerIntegration=").append(registerIntegration);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
