package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsMemberSignIn;
import com.macro.mall.portal.service.UmsMemberSignInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(tags = "UmsMemberSignInController")
@Tag(name = "UmsMemberSignInController", description = "会员签到管理")
@RequestMapping("/member/signIn")
public class UmsMemberSignInController {

    @Autowired
    private UmsMemberSignInService signInService;

    @ApiOperation("会员签到")
    @RequestMapping(value = "/doSignIn", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsMemberSignIn> doSignIn() {
        UmsMemberSignIn signIn = signInService.signIn();
        return CommonResult.success(signIn);
    }

    @ApiOperation("查询今日是否已签到")
    @RequestMapping(value = "/isSigned", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Map<String, Object>> isSigned() {
        Map<String, Object> result = new HashMap<>();
        boolean isSigned = signInService.isSignedInToday();
        Integer continueDays = signInService.getContinueDays();
        result.put("isSigned", isSigned);
        result.put("continueDays", continueDays);
        return CommonResult.success(result);
    }

    @ApiOperation("获取签到历史")
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMemberSignIn>> getHistory(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<UmsMemberSignIn> history = signInService.getSignInHistory(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(history));
    }
}
