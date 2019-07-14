package com.macro.mall.portal.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 首页当前秒杀场次信息
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class HomeFlashPromotion {
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
	public Date getNextStartTime() {
		return nextStartTime;
	}
	public void setNextStartTime(Date nextStartTime) {
		this.nextStartTime = nextStartTime;
	}
	public Date getNextEndTime() {
		return nextEndTime;
	}
	public void setNextEndTime(Date nextEndTime) {
		this.nextEndTime = nextEndTime;
	}
	public List<FlashPromotionProduct> getProductList() {
		return productList;
	}
	public void setProductList(List<FlashPromotionProduct> productList) {
		this.productList = productList;
	}
	private Date startTime;
    private Date endTime;
    private Date nextStartTime;
    private Date nextEndTime;
    //属于该秒杀活动的商品
    private List<FlashPromotionProduct> productList;
}
