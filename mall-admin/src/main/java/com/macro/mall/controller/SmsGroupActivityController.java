package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.SmsGroupActivityParam;
import com.macro.mall.model.SmsGroupActivity;
import com.macro.mall.model.SmsGroupProductRelation;
import com.macro.mall.model.SmsGroupTeam;
import com.macro.mall.service.SmsGroupActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "SmsGroupActivityController")
@Tag(name = "SmsGroupActivityController", description = "拼团活动管理")
@RequestMapping("/group")
public class SmsGroupActivityController {

    @Autowired
    private SmsGroupActivityService groupActivityService;

    @ApiOperation("添加拼团活动")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SmsGroupActivityParam param) {
        int count = groupActivityService.create(param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("编辑拼团活动")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SmsGroupActivityParam param) {
        int count = groupActivityService.update(id, param);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除拼团活动")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = groupActivityService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改活动上下线状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        int count = groupActivityService.updateStatus(id, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取活动详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsGroupActivity> getItem(@PathVariable Long id) {
        SmsGroupActivity activity = groupActivityService.getItem(id);
        return CommonResult.success(activity);
    }

    @ApiOperation("分页查询拼团活动")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsGroupActivity>> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsGroupActivity> activityList = groupActivityService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(activityList));
    }

    @ApiOperation("获取活动关联商品")
    @RequestMapping(value = "/product/list/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupProductRelation>> getProductRelationList(@PathVariable Long activityId) {
        List<SmsGroupProductRelation> relationList = groupActivityService.getProductRelationList(activityId);
        return CommonResult.success(relationList);
    }

    @ApiOperation("获取活动拼团队伍列表")
    @RequestMapping(value = "/team/list/{activityId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsGroupTeam>> getTeamList(
            @PathVariable Long activityId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsGroupTeam> teamList = groupActivityService.getTeamList(activityId, status, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(teamList));
    }
}
