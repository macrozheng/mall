package com.macro.mall.controller;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.OmsOrderReturnApplyResult;
import com.macro.mall.dto.OmsReturnApplyQueryParam;
import com.macro.mall.dto.OmsUpdateStatusParam;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.service.OmsOrderReturnApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货申请管理
 * Created by macro on 2018/10/18.
 */
@Controller
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(OmsReturnApplyQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return new CommonResult().pageSuccess(returnApplyList);
    }

    @ApiOperation("批量删除申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
        return new CommonResult().success(result);
    }

    @ApiOperation("修改申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

}
