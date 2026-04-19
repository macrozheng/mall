package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.portal.service.GroupBuyActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拼团活动前台Controller
 */
@Controller
@Api(tags = "GroupBuyActivityController")
@Tag(name = "GroupBuyActivityController", description = "拼团活动")
@RequestMapping("/groupBuy/activity")
public class GroupBuyActivityController {

    @Autowired
    private GroupBuyActivityService activityService;

    @ApiOperation("进行中的拼团活动列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupBuyActivity>> list() {
        return CommonResult.success(activityService.listOngoing());
    }

    @ApiOperation("活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsGroupBuyActivity> detail(@PathVariable Long id) {
        return CommonResult.success(activityService.getDetail(id));
    }

    @ApiOperation("活动下的商品列表")
    @RequestMapping(value = "/product/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupBuyProduct>> productList(@PathVariable("id") Long activityId) {
        return CommonResult.success(activityService.listProduct(activityId));
    }

    @ApiOperation("活动下进行中的可参与团列表")
    @RequestMapping(value = "/team/ongoing/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupBuyTeam>> ongoingTeam(@PathVariable("id") Long activityId) {
        return CommonResult.success(activityService.listOngoingTeam(activityId));
    }
}
