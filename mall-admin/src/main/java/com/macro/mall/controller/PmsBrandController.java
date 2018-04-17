package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.PmsBrandParam;
import com.macro.mall.service.PmsBrandService;
import com.macro.mall.validator.FlagValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌功能Controller
 */
@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation(value = "获取全部品牌列表")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public Object getBrandList() {
        return new CommonResult().success(brandService.listAllBrand());
    }

    @ApiOperation(value = "添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object createBrand(@Validated @RequestBody PmsBrandParam pmsBrand, BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
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
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateBrand(@PathVariable("id") Long id, @Validated @RequestBody PmsBrandParam pmsBrandParam, BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandParam);
        if (count == 1) {
            commonResult = new CommonResult().success(pmsBrandParam);
            LOGGER.debug("updateBrand success:{}", pmsBrandParam);
        } else {
            commonResult = new CommonResult().failed();
            LOGGER.debug("updateBrand failed:{}", pmsBrandParam);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success:id={}", id);
            return new CommonResult().success(null);
        } else {
            LOGGER.debug("deleteBrand failed:id={}", id);
            return new CommonResult().failed();
        }
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listBrand(@RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        return new CommonResult().pageSuccess(brandService.listBrand(keyword, pageNum, pageSize));
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getBrand(@PathVariable("id") Long id) {
        return new CommonResult().success(brandService.getBrand(id));
    }

    @ApiOperation(value = "批量删除品牌")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteBrandBatch(@RequestParam("ids") List<Long> ids) {
        int count = brandService.deleteBrand(ids);
        if (count > 0) {
            LOGGER.debug("deleteBrandBatch success:ids={}", ids);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("deleteBrandBatch failed:ids={}", ids);
            return new CommonResult().failed();
        }
    }

    @ApiOperation(value = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object updateShowStatus(@RequestParam("ids") List<Long> ids,
                                   @RequestParam("showStatus") Integer showStatus) {
        int count = brandService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            LOGGER.debug("updateShowStatus success:ids={}", ids);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("updateShowStatus failed:ids={}", ids);
            return new CommonResult().failed();
        }
    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.GET)
    @ResponseBody
    public Object updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                      @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = brandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            LOGGER.debug("updateFactoryStatus success:{}", ids);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("updateFactoryStatus failed:{}", ids);
            return new CommonResult().failed();
        }
    }
}
