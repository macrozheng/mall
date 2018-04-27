package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.model.PmsSkuStock;
import com.macro.mall.service.PmsSkuStockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * sku库存Controller
 * Created by macro on 2018/4/27.
 */
@Api("sku商品库存管理")
@Controller
@RequestMapping("/sku")
public class PmsSkuStockController {
    @Autowired
    private PmsSkuStockService skuStockService;
    @ApiOperation("根据商品编号及编号模糊搜索sku库存")
    @RequestMapping("/{id}")
    @ResponseBody
    public Object getList(@PathVariable Long id, @RequestParam("keyword") String keyword){
        List<PmsSkuStock> skuStockList = skuStockService.getList(id,keyword);
        return new CommonResult().success(skuStockList);
    }
}
