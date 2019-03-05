package com.macro.mall.dto;

import io.swagger.annotations.ApiModelProperty;

public class UmsMemberQueryParam {
	
	@ApiModelProperty("启用状态")
    private Integer status;
    @ApiModelProperty("商品名称模糊关键字")
    private String keyword;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
    
}
