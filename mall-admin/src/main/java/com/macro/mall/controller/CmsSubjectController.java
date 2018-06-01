package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.CmsSubject;
import com.macro.mall.service.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品专题Controller
 * Created by macro on 2018/6/1.
 */
@Controller
@Api(tags = "CmsSubjectController", description = "商品专题管理")
@RequestMapping("/subject")
public class CmsSubjectController {
    @Autowired
    private CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Object listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return new CommonResult().success(subjectList);
    }
}
