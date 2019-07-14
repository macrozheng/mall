package com.macro.mall.portal.domain;

import com.macro.mall.model.CmsSubject;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.SmsHomeAdvertise;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 首页内容返回信息封装
 * Created by macro on 2019/1/28.
 */
@Getter
@Setter
public class HomeContentResult {
    public List<SmsHomeAdvertise> getAdvertiseList() {
		return advertiseList;
	}
	public void setAdvertiseList(List<SmsHomeAdvertise> advertiseList) {
		this.advertiseList = advertiseList;
	}
	public List<PmsBrand> getBrandList() {
		return brandList;
	}
	public void setBrandList(List<PmsBrand> brandList) {
		this.brandList = brandList;
	}
	public HomeFlashPromotion getHomeFlashPromotion() {
		return homeFlashPromotion;
	}
	public void setHomeFlashPromotion(HomeFlashPromotion homeFlashPromotion) {
		this.homeFlashPromotion = homeFlashPromotion;
	}
	public List<PmsProduct> getNewProductList() {
		return newProductList;
	}
	public void setNewProductList(List<PmsProduct> newProductList) {
		this.newProductList = newProductList;
	}
	public List<PmsProduct> getHotProductList() {
		return hotProductList;
	}
	public void setHotProductList(List<PmsProduct> hotProductList) {
		this.hotProductList = hotProductList;
	}
	public List<CmsSubject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<CmsSubject> subjectList) {
		this.subjectList = subjectList;
	}
	//轮播广告
    private List<SmsHomeAdvertise> advertiseList;
    //推荐品牌
    private List<PmsBrand> brandList;
    //当前秒杀场次
    private HomeFlashPromotion homeFlashPromotion;
    //新品推荐
    private List<PmsProduct> newProductList;
    //人气推荐
    private List<PmsProduct> hotProductList;
    //推荐专题
    private List<CmsSubject> subjectList;
}
