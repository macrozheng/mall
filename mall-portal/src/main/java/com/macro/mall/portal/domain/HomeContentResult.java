package com.macro.mall.portal.domain;

import com.macro.mall.model.CmsSubject;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.SmsHomeAdvertise;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "轮播广告")
    private List<SmsHomeAdvertise> advertiseList;
    @Schema(title = "推荐品牌")
    private List<PmsBrand> brandList;
    @Schema(title = "当前秒杀场次")
    private HomeFlashPromotion homeFlashPromotion;
    @Schema(title = "新品推荐")
    private List<PmsProduct> newProductList;
    @Schema(title = "人气推荐")
    private List<PmsProduct> hotProductList;
    @Schema(title = "推荐专题")
    private List<CmsSubject> subjectList;
}
