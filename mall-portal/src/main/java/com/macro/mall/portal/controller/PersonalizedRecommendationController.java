package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.PmsProduct;
import com.macro.mall.portal.service.PersonalizedRecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个性化商品推荐Controller
 * Created by macro on 2024/5/20.
 */
@RestController
@Api(tags = "PersonalizedRecommendationController")
@RequestMapping("/recommendation")
public class PersonalizedRecommendationController {

    @Autowired
    private PersonalizedRecommendationService recommendationService;

    @ApiOperation("获取个性化推荐商品")
    @RequestMapping(value = "/personalized", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProduct>> getPersonalizedRecommendations(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonPage<PmsProduct> productPage = recommendationService.getPersonalizedRecommendations(pageNum, pageSize);
        return CommonResult.success(productPage);
    }
}
