package com.macro.mall.demo.controller;

import com.macro.mall.demo.dto.CommonResult;
import com.macro.mall.demo.dto.PmsBrandDto;
import com.macro.mall.demo.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 测试controller
 */
@Api(value = "demo", description = "demo详情")
@Controller
public class DemoController {
    @Autowired
    private DemoService demoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/brand/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Object getBrandList() {
        return new CommonResult().success(demoService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/brand/create", method = RequestMethod.POST)
    @ResponseBody
    public Object createBrand(@Validated @RequestBody PmsBrandDto pmsBrand, BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = demoService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = new CommonResult().success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = new CommonResult().failed();
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌")
    @RequestMapping(value = "/brand/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBrand(@PathVariable("id") Long id, @Validated @RequestBody PmsBrandDto pmsBrandDto,BindingResult result) {
        if(result.hasErrors()){
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = demoService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = new CommonResult().success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = new CommonResult().failed();
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/brand/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteBrand(@PathVariable("id") Long id) {
        int count = demoService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return new CommonResult().success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return new CommonResult().failed();
        }
    }

    @ApiOperation(value = "分页获取品牌列表")
    @RequestMapping(value = "/brand/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        return new CommonResult().pageSuccess(demoService.listBrand(pageNum, pageSize));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/brand/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object brand(@PathVariable("id") Long id) {
        return new CommonResult().success(demoService.getBrand(id));
    }
}
