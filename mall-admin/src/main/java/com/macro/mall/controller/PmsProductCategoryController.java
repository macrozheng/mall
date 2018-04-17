package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.PmsProductCategoryParam;
import com.macro.mall.model.PmsProductCategory;
import com.macro.mall.service.PmsProductCategoryService;
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
 * 商品分类模块Controller
 */
@Controller
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductCategoryController.class);
    @Autowired
    private PmsProductCategoryService productCategoryService;

    @ApiOperation("添加产品分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@Validated @RequestBody PmsProductCategoryParam pmsProductCategoryParam, BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result);
        }
        int count = productCategoryService.create(pmsProductCategoryParam);
        if (count > 0) {
            LOGGER.debug("create success {}", pmsProductCategoryParam);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("create failed {}", pmsProductCategoryParam);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("修改商品分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id,
                         @Validated
                         @RequestBody PmsProductCategoryParam pmsProductCategoryParam,
                         BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result);
        }
        int count = productCategoryService.update(id, pmsProductCategoryParam);
        if (count > 0) {
            LOGGER.debug("update success {}", pmsProductCategoryParam);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("update failed {}", pmsProductCategoryParam);
            return new CommonResult().failed();
        }
    }

    @ApiOperation("分页查询商品分类")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@PathVariable Long parentId,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.list(parentId, pageSize, pageNum);
        return new CommonResult().pageSuccess(productCategoryList);
    }

    @ApiOperation("删除商品分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@PathVariable Long id) {
        int count = productCategoryService.delete(id);
        if (count > 0) {
            LOGGER.debug("delete success id:{}", id);
            return new CommonResult().success(count);
        } else {
            LOGGER.debug("delete failed id:{}", id);
            return new CommonResult().failed();
        }
    }
}
