package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UmsIntegrationLevel implements Serializable {
    private Long id;

    @ApiModelProperty(value = "等级名称")
    private String levelName;

    @ApiModelProperty(value = "最低积分")
    private Integer minIntegration;

    @ApiModelProperty(value = "等级图标")
    private String icon;

    @ApiModelProperty(value = "等级背景图")
    private String background;

    @ApiModelProperty(value = "折扣特权（如9.5折，存储9.5）")
    private BigDecimal priviledgeDiscount;

    @ApiModelProperty(value = "积分倍率（如1.5倍，存储1.5）")
    private BigDecimal priviledgeIntegrationRate;

    @ApiModelProperty(value = "生日额外积分")
    private Integer priviledgeBirthdayIntegration;

    @ApiModelProperty(value = "是否免运费：0->否；1->是")
    private Integer priviledgeFreeShipping;

    @ApiModelProperty(value = "排序")
    private Integer sort;

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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getMinIntegration() {
        return minIntegration;
    }

    public void setMinIntegration(Integer minIntegration) {
        this.minIntegration = minIntegration;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public BigDecimal getPriviledgeDiscount() {
        return priviledgeDiscount;
    }

    public void setPriviledgeDiscount(BigDecimal priviledgeDiscount) {
        this.priviledgeDiscount = priviledgeDiscount;
    }

    public BigDecimal getPriviledgeIntegrationRate() {
        return priviledgeIntegrationRate;
    }

    public void setPriviledgeIntegrationRate(BigDecimal priviledgeIntegrationRate) {
        this.priviledgeIntegrationRate = priviledgeIntegrationRate;
    }

    public Integer getPriviledgeBirthdayIntegration() {
        return priviledgeBirthdayIntegration;
    }

    public void setPriviledgeBirthdayIntegration(Integer priviledgeBirthdayIntegration) {
        this.priviledgeBirthdayIntegration = priviledgeBirthdayIntegration;
    }

    public Integer getPriviledgeFreeShipping() {
        return priviledgeFreeShipping;
    }

    public void setPriviledgeFreeShipping(Integer priviledgeFreeShipping) {
        this.priviledgeFreeShipping = priviledgeFreeShipping;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        sb.append(", levelName=").append(levelName);
        sb.append(", minIntegration=").append(minIntegration);
        sb.append(", icon=").append(icon);
        sb.append(", background=").append(background);
        sb.append(", priviledgeDiscount=").append(priviledgeDiscount);
        sb.append(", priviledgeIntegrationRate=").append(priviledgeIntegrationRate);
        sb.append(", priviledgeBirthdayIntegration=").append(priviledgeBirthdayIntegration);
        sb.append(", priviledgeFreeShipping=").append(priviledgeFreeShipping);
        sb.append(", sort=").append(sort);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
