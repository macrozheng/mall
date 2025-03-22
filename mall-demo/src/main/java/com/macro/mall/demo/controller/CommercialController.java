package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.demo.dto.CommercialDto;
import com.macro.mall.demo.service.CommercialService;
import com.macro.mall.model.Commercial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "CommercialController")
@Tag(name = "CommercialController", description = "商业管理接口")
@Controller
public class CommercialController {
    @Autowired
    private CommercialService commercialService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommercialController.class);

    @ApiOperation(value = "获取全部商业列表")
    @RequestMapping(value = "/commercial/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<Commercial>> getCommercialList() {
        return CommonResult.success(commercialService.listAllCommercial());
    }

    @ApiOperation(value = "添加商业")
    @RequestMapping(value = "/commercial/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createCommercial(@Validated @RequestBody CommercialDto commercialDto) {
        CommonResult commonResult;
        int count = commercialService.createCommercial(commercialDto);
        if (count == 1) {
            commonResult = CommonResult.success(commercialDto);
            LOGGER.debug("createCommercial success:{}", commercialDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createCommercial failed:{}", commercialDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新商业")
    @RequestMapping(value = "/commercial/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateCommercial(@PathVariable("id") Long id, @Validated @RequestBody CommercialDto commercialDto) {
        CommonResult commonResult;
        int count = commercialService.updateCommercial(id, commercialDto);
        if (count == 1) {
            commonResult = CommonResult.success(commercialDto);
            LOGGER.debug("updateCommercial success:{}", commercialDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateCommercial failed:{}", commercialDto);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除商业")
    @RequestMapping(value = "/commercial/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteCommercial(@PathVariable("id") Long id) {
        int count = commercialService.deleteCommercial(id);
        if (count == 1) {
            LOGGER.debug("deleteCommercial success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteCommercial failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation(value = "分页获取商业列表")
    @RequestMapping(value = "/commercial/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Commercial>> listCommercial(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<Commercial> commercialList = commercialService.listCommercial(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(commercialList));
    }

    @ApiOperation(value = "根据编号查询商业信息")
    @RequestMapping(value = "/commercial/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Commercial> commercial(@PathVariable("id") Long id) {
        return CommonResult.success(commercialService.getCommercial(id));
    }
} 