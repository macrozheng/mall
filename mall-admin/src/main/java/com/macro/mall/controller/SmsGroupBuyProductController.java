package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.SmsGroupBuyProductParam;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.service.SmsGroupBuyProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拼团活动商品管理Controller
 */
@Controller
@Api(tags = "SmsGroupBuyProductController")
@Tag(name = "SmsGroupBuyProductController", description = "拼团活动商品管理")
@RequestMapping("/groupBuy/product")
public class SmsGroupBuyProductController {

    @Autowired
    private SmsGroupBuyProductService productService;

    @ApiOperation("批量关联商品到活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createBatch(@RequestBody SmsGroupBuyProductParam param) {
        int count = productService.createBatch(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新活动商品(价格/库存/限购)")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsGroupBuyProduct product) {
        int count = productService.update(id, product);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("移除活动商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = productService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("查询活动下的商品列表")
    @RequestMapping(value = "/list/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupBuyProduct>> list(@PathVariable Long activityId) {
        return CommonResult.success(productService.listByActivity(activityId));
    }
}
