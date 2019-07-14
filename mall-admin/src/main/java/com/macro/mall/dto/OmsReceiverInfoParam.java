package com.macro.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单修改收货人信息参数
 * Created by macro on 2018/10/29.
 */
@Getter
@Setter
public class OmsReceiverInfoParam {
    private Long orderId;
    private String receiverName;
    private String receiverPhone;
    private String receiverPostCode;
    private String receiverDetailAddress;
    private String receiverProvince;
    private String receiverCity;
    public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReceiverPostCode() {
		return receiverPostCode;
	}
	public void setReceiverPostCode(String receiverPostCode) {
		this.receiverPostCode = receiverPostCode;
	}
	public String getReceiverDetailAddress() {
		return receiverDetailAddress;
	}
	public void setReceiverDetailAddress(String receiverDetailAddress) {
		this.receiverDetailAddress = receiverDetailAddress;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setReceiverRegion(String receiverRegion) {
		this.receiverRegion = receiverRegion;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private String receiverRegion;
    private Integer status;
	public Long getOrderId() {
		return this.orderId;
	}
	public Integer getStatus() {
		return this.status;
	}
	public String getReceiverRegion() {
		return this.receiverRegion;
	}
}
