package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsFeedBack;
import com.macro.mall.portal.service.UmsFeedBackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "FeedBackController", description = "会员意见反馈")
@RequestMapping("/feedback")
public class FeedBackController {
    @Autowired
    private UmsFeedBackService feedbackService;

    @ApiOperation("填写意见反馈")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody UmsFeedBack feedback) {
        int count = feedbackService.add(feedback);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
