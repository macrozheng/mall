package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.service.SmsGroupBuyTeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拼团团管理Controller (后台)
 */
@Controller
@Api(tags = "SmsGroupBuyTeamController")
@Tag(name = "SmsGroupBuyTeamController", description = "拼团团管理(后台)")
@RequestMapping("/groupBuy/team")
public class SmsGroupBuyTeamController {

    @Autowired
    private SmsGroupBuyTeamService teamService;

    @ApiOperation("分页查询团记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsGroupBuyTeam>> list(
            @RequestParam(value = "activityId", required = false) Long activityId,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "teamNo", required = false) String teamNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsGroupBuyTeam> list = teamService.list(activityId, status, teamNo, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("团详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<SmsGroupBuyTeam> getItem(@PathVariable Long id) {
        return CommonResult.success(teamService.getItem(id));
    }

    @ApiOperation("强制关闭团(应急)")
    @RequestMapping(value = "/close/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult close(@PathVariable Long id) {
        int count = teamService.forceClose(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
