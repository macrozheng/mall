package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.PmsProductAttributeCategory;
import com.macro.mall.service.PmsProductAttributeCategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品属性分类Controller
 */
@Controller
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductAttributeCategoryController.class);
    @Autowired
    private PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("添加商品属性分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestParam String name) {
        int count = productAttributeCategoryService.create(name);
        if (count > 0) {
            LOGGER.debug("create success name:{}", name);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("create failed name:{}", name);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("修改商品属性分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestParam String name) {
        int count = productAttributeCategoryService.update(id, name);
        if (count > 0) {
            LOGGER.debug("update success id:{}", id);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("update failed id:{}", id);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = productAttributeCategoryService.delete(id);
        if (count > 0) {
            LOGGER.debug("delete success name:{}", id);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("delete failed name:{}", id);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryService.getItem(id);
        return new CommonResult().success(productAttributeCategory);
    }

    @ApiOperation("分页获取所有商品属性分类")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getList(@RequestParam(defaultValue = "5") Integer pageSize,@RequestParam(defaultValue = "1") Integer pageNum) {
        List<PmsProductAttributeCategory> productAttributeCategoryList = productAttributeCategoryService.getList(pageSize,pageNum);
        return new CommonResult().pageSuccess(productAttributeCategoryList);
    }
}
