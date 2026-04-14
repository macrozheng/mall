package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SmsPromotionActivity implements Serializable {
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动类型：1->满减；2->满折；3->第N件优惠；4->套餐价；5->SKU特价；6->会员专享")
    private Integer type;

    @ApiModelProperty(value = "使用平台：0->全部；1->移动；2->PC")
    private Integer platform;

    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    @ApiModelProperty(value = "状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "优先级，数值越大优先级越高")
    private Integer priority;

    @ApiModelProperty(value = "是否可叠加：0->不可叠加；1->可叠加")
    private Boolean stackable;

    @ApiModelProperty(value = "互斥活动ID列表，逗号分隔")
    private String exclusiveWith;

    @ApiModelProperty(value = "适用范围：0->全场通用；1->指定分类；2->指定商品；3->指定品牌")
    private Integer useType;

    @ApiModelProperty(value = "最低订单金额门槛")
    private BigDecimal minOrderAmount;

    @ApiModelProperty(value = "最大优惠金额上限")
    private BigDecimal maxDiscountAmount;

    @ApiModelProperty(value = "每人限享次数")
    private Integer perLimit;

    @ApiModelProperty(value = "活动总限享次数")
    private Integer totalLimit;

    @ApiModelProperty(value = "已使用次数")
    private Integer usedCount;

    @ApiModelProperty(value = "活动描述")
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getStackable() {
        return stackable;
    }

    public void setStackable(Boolean stackable) {
        this.stackable = stackable;
    }

    public String getExclusiveWith() {
        return exclusiveWith;
    }

    public void setExclusiveWith(String exclusiveWith) {
        this.exclusiveWith = exclusiveWith;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public BigDecimal getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(BigDecimal minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }

    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    public Integer getPerLimit() {
        return perLimit;
    }

    public void setPerLimit(Integer perLimit) {
        this.perLimit = perLimit;
    }

    public Integer getTotalLimit() {
        return totalLimit;
    }

    public void setTotalLimit(Integer totalLimit) {
        this.totalLimit = totalLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", platform=").append(platform);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", priority=").append(priority);
        sb.append(", stackable=").append(stackable);
        sb.append(", exclusiveWith=").append(exclusiveWith);
        sb.append(", useType=").append(useType);
        sb.append(", minOrderAmount=").append(minOrderAmount);
        sb.append(", maxDiscountAmount=").append(maxDiscountAmount);
        sb.append(", perLimit=").append(perLimit);
        sb.append(", totalLimit=").append(totalLimit);
        sb.append(", usedCount=").append(usedCount);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
