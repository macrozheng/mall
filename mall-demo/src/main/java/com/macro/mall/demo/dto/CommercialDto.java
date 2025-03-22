package com.macro.mall.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommercialDto {
    @ApiModelProperty(value = "商业名称")
    @NotEmpty(message = "商业名称不能为空")
    private String name;

    @ApiModelProperty(value = "商业描述")
    private String description;

    @ApiModelProperty(value = "商业地址")
    @NotEmpty(message = "商业地址不能为空")
    private String address;

    @ApiModelProperty(value = "联系电话")
    @NotEmpty(message = "联系电话不能为空")
    private String phone;

    @ApiModelProperty(value = "状态：0->禁用；1->启用")
    @NotNull(message = "状态不能为空")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
} 