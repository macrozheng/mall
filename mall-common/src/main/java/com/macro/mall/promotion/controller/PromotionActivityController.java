package com.macro.mall.promotion.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsPromotionActivity;
import com.macro.mall.promotion.domain.PromotionCalcResult;
import com.macro.mall.promotion.domain.PromotionContext;
import com.macro.mall.promotion.dto.PromotionActivityParam;
import com.macro.mall.promotion.service.PromotionActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "PromotionActivityController", description = "营销活动管理")
@RequestMapping("/promotion/activity")
public class PromotionActivityController {

    @Autowired
    private PromotionActivityService promotionActivityService;

    @ApiOperation("创建营销活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> create(@Validated @RequestBody PromotionActivityParam param) {
        int count = promotionActivityService.create(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新营销活动")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> update(@PathVariable Long id, 
                                         @Validated @RequestBody PromotionActivityParam param) {
        int count = promotionActivityService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除营销活动")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = promotionActivityService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除营销活动")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = promotionActivityService.deleteBatch(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取营销活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsPromotionActivity> getById(@PathVariable Long id) {
        SmsPromotionActivity activity = promotionActivityService.getById(id);
        return CommonResult.success(activity);
    }

    @ApiOperation("分页查询营销活动列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsPromotionActivity>> list(
            @ApiParam("活动名称") @RequestParam(value = "name", required = false) String name,
            @ApiParam("活动类型") @RequestParam(value = "type", required = false) Integer type,
            @ApiParam("状态") @RequestParam(value = "status", required = false) Integer status,
            @ApiParam("页码") @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @ApiParam("每页数量") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        CommonPage<SmsPromotionActivity> page = promotionActivityService.list(name, type, status, pageNum, pageSize);
        return CommonResult.success(page);
    }

    @ApiOperation("更新营销活动状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Integer> updateStatus(@PathVariable Long id, 
                                               @RequestParam("status") Integer status) {
        int count = promotionActivityService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("计算优惠")
    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PromotionCalcResult> calculate(@RequestBody PromotionContext context) {
        PromotionCalcResult result = promotionActivityService.calculate(context);
        return CommonResult.success(result);
    }

    @ApiOperation("试算优惠")
    @RequestMapping(value = "/tryCalculate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PromotionCalcResult> tryCalculate(@RequestBody PromotionContext context) {
        PromotionCalcResult result = promotionActivityService.tryCalculate(context);
        return CommonResult.success(result);
    }

    @ApiOperation("查找最优优惠组合")
    @RequestMapping(value = "/optimal", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<PromotionCalcResult> findOptimalCombination(@RequestBody PromotionContext context) {
        PromotionCalcResult result = promotionActivityService.findOptimalCombination(context);
        return CommonResult.success(result);
    }

    @ApiOperation("查找TopN优惠组合")
    @RequestMapping(value = "/topN", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<PromotionCalcResult>> findTopNCombinations(
            @RequestBody PromotionContext context,
            @RequestParam(value = "n", defaultValue = "5") int n) {
        List<PromotionCalcResult> results = promotionActivityService.findTopNCombinations(context, n);
        return CommonResult.success(results);
    }
}
