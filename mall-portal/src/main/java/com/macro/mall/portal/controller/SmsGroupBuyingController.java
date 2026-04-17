package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsGroupActivity;
import com.macro.mall.portal.domain.*;
import com.macro.mall.portal.service.SmsGroupBuyingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "SmsGroupBuyingController")
@Tag(name = "SmsGroupBuyingController", description = "拼团活动管理")
@RequestMapping("/group")
public class SmsGroupBuyingController {

    @Autowired
    private SmsGroupBuyingService groupBuyingService;

    @ApiOperation("获取拼团活动列表")
    @RequestMapping(value = "/activity/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsGroupActivity>> listActivity(
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsGroupActivity> activityList = groupBuyingService.listActivity(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(activityList));
    }

    @ApiOperation("获取拼团活动详情")
    @RequestMapping(value = "/activity/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<GroupActivityDetail> getActivityDetail(@PathVariable Long activityId) {
        GroupActivityDetail detail = groupBuyingService.getActivityDetail(activityId);
        return CommonResult.success(detail);
    }

    @ApiOperation("获取拼团商品详情")
    @RequestMapping(value = "/product/{groupProductId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<GroupProductItem> getProductDetail(@PathVariable Long groupProductId) {
        GroupProductItem item = groupBuyingService.getProductDetail(groupProductId);
        return CommonResult.success(item);
    }

    @ApiOperation("获取可加入的拼团队伍列表")
    @RequestMapping(value = "/team/available/{groupProductId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<GroupTeamDetail>> listAvailableTeam(
            @PathVariable Long groupProductId,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<GroupTeamDetail> teamList = groupBuyingService.listAvailableTeam(groupProductId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(teamList));
    }

    @ApiOperation("获取拼团队伍详情")
    @RequestMapping(value = "/team/{teamId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<GroupTeamDetail> getTeamDetail(@PathVariable Long teamId) {
        GroupTeamDetail detail = groupBuyingService.getTeamDetail(teamId);
        return CommonResult.success(detail);
    }

    @ApiOperation("创建拼团订单（开团或参团）")
    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> createGroupOrder(@RequestBody GroupOrderParam param) {
        Map<String, Object> result = groupBuyingService.createGroupOrder(param);
        return CommonResult.success(result);
    }

    @ApiOperation("拼团支付成功回调")
    @RequestMapping(value = "/order/paySuccess/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult paySuccess(@PathVariable Long orderId) {
        groupBuyingService.paySuccess(orderId);
        return CommonResult.success(null, "支付成功");
    }

    @ApiOperation("获取我的拼团列表")
    @RequestMapping(value = "/my/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<GroupTeamDetail>> listMyGroup(
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        CommonPage<GroupTeamDetail> result = groupBuyingService.listMyGroup(status, pageNum, pageSize);
        return CommonResult.success(result);
    }
}
