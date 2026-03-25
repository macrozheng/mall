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
    private List<SmsHomeAdvertise> advertiseList;
    private List<PmsBrand> brandList;
    private HomeFlashPromotion homeFlashPromotion;
    private List<PmsProduct> newProductList;
    private List<PmsProduct> hotProductList;
    private List<CmsSubject> subjectList;
}
