package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsHomeRecommendSubject;
import com.macro.mall.service.SmsHomeRecommendSubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页专题推荐管理Controller
 * Created by macro on 2018/11/6.
 */
@Controller
@Tag(name = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService recommendSubjectService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        int count = recommendSubjectService.create(homeRecommendSubjectList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendSubjectService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = recommendSubjectService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeRecommendSubject> homeRecommendSubjectList = recommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeRecommendSubjectList));
    }
}
