package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsIntegrationChangeHistory;
import com.macro.mall.model.UmsIntegrationLevel;
import com.macro.mall.portal.service.UmsIntegrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "UmsIntegrationController")
@Tag(name = "UmsIntegrationController", description = "会员积分管理")
@RequestMapping("/member/integration")
public class UmsIntegrationController {

    @Autowired
    private UmsIntegrationService integrationService;

    @ApiOperation("获取积分统计信息")
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> getStats() {
        Map<String, Object> stats = integrationService.getIntegrationStats();
        return CommonResult.success(stats);
    }

    @ApiOperation("获取积分变更历史")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsIntegrationChangeHistory>> getHistory(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "changeType", required = false) Integer changeType) {
        List<UmsIntegrationChangeHistory> history = integrationService.getIntegrationHistory(pageNum, pageSize, changeType);
        return CommonResult.success(CommonPage.restPage(history));
    }

    @ApiOperation("获取当前积分等级")
    @RequestMapping(value = "/level", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsIntegrationLevel> getCurrentLevel() {
        UmsIntegrationLevel level = integrationService.getCurrentLevel();
        return CommonResult.success(level);
    }

    @ApiOperation("获取所有积分等级列表")
    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsIntegrationLevel>> getAllLevels() {
        List<UmsIntegrationLevel> levels = integrationService.getAllLevels();
        return CommonResult.success(levels);
    }
}
