package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsIntegrationExchangeRecord;
import com.macro.mall.model.UmsPointMallCategory;
import com.macro.mall.model.UmsPointMallProduct;
import com.macro.mall.portal.service.UmsPointMallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "UmsPointMallController")
@Tag(name = "UmsPointMallController", description = "积分商城管理")
@RequestMapping("/pointMall")
public class UmsPointMallController {

    @Autowired
    private UmsPointMallService pointMallService;

    @ApiOperation("获取积分商城分类列表")
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPointMallCategory>> getCategories() {
        List<UmsPointMallCategory> categories = pointMallService.getCategories();
        return CommonResult.success(categories);
    }

    @ApiOperation("获取积分商城商品列表")
    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsPointMallProduct>> getProducts(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "sort") String sortBy) {
        List<UmsPointMallProduct> products = pointMallService.getProducts(categoryId, pageNum, pageSize, sortBy);
        return CommonResult.success(CommonPage.restPage(products));
    }

    @ApiOperation("获取积分商城商品详情")
    @RequestMapping(value = "/product/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsPointMallProduct> getProductDetail(@PathVariable Long id) {
        UmsPointMallProduct product = pointMallService.getProductDetail(id);
        return CommonResult.success(product);
    }

    @ApiOperation("兑换积分商品")
    @RequestMapping(value = "/exchange", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsIntegrationExchangeRecord> exchangeProduct(
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") Integer quantity,
            @RequestParam(required = false) Long addressId) {
        UmsIntegrationExchangeRecord record = pointMallService.exchangeProduct(productId, quantity, addressId);
        return CommonResult.success(record);
    }

    @ApiOperation("获取兑换记录列表")
    @RequestMapping(value = "/exchange/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsIntegrationExchangeRecord>> getExchangeRecords(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status) {
        List<UmsIntegrationExchangeRecord> records = pointMallService.getExchangeRecords(pageNum, pageSize, status);
        return CommonResult.success(CommonPage.restPage(records));
    }

    @ApiOperation("获取兑换记录详情")
    @RequestMapping(value = "/exchange/detail/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsIntegrationExchangeRecord> getExchangeRecordDetail(@PathVariable Long id) {
        UmsIntegrationExchangeRecord record = pointMallService.getExchangeRecordDetail(id);
        return CommonResult.success(record);
    }

    @ApiOperation("取消兑换")
    @RequestMapping(value = "/exchange/cancel/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancelExchange(@PathVariable Long id) {
        pointMallService.cancelExchange(id);
        return CommonResult.success(null);
    }

    @ApiOperation("确认收货")
    @RequestMapping(value = "/exchange/confirm/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult confirmReceive(@PathVariable Long id) {
        pointMallService.confirmReceive(id);
        return CommonResult.success(null);
    }

    @ApiOperation("获取热门商品")
    @RequestMapping(value = "/product/hot", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPointMallProduct>> getHotProducts(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<UmsPointMallProduct> products = pointMallService.getHotProducts(limit);
        return CommonResult.success(products);
    }

    @ApiOperation("获取新品商品")
    @RequestMapping(value = "/product/new", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPointMallProduct>> getNewProducts(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<UmsPointMallProduct> products = pointMallService.getNewProducts(limit);
        return CommonResult.success(products);
    }

    @ApiOperation("获取推荐商品")
    @RequestMapping(value = "/product/recommend", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPointMallProduct>> getRecommendProducts(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        List<UmsPointMallProduct> products = pointMallService.getRecommendProducts(limit);
        return CommonResult.success(products);
    }
}
