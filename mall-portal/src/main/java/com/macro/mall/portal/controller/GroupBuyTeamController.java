package com.macro.mall.portal.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.portal.domain.GroupBuyJoinParam;
import com.macro.mall.portal.domain.GroupBuyOpenParam;
import com.macro.mall.portal.domain.GroupBuyOrderResult;
import com.macro.mall.portal.domain.GroupBuyTeamDetail;
import com.macro.mall.portal.service.GroupBuyOrderService;
import com.macro.mall.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拼团团操作Controller
 */
@Controller
@Api(tags = "GroupBuyTeamController")
@Tag(name = "GroupBuyTeamController", description = "拼团开团/参团")
@RequestMapping("/groupBuy/team")
public class GroupBuyTeamController {

    @Autowired
    private GroupBuyOrderService groupBuyOrderService;

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("开团下单")
    @RequestMapping(value = "/launch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<GroupBuyOrderResult> launch(@RequestBody GroupBuyOpenParam param) {
        return CommonResult.success(groupBuyOrderService.launchGroup(param));
    }

    @ApiOperation("参团下单")
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<GroupBuyOrderResult> join(@RequestBody GroupBuyJoinParam param) {
        return CommonResult.success(groupBuyOrderService.joinGroup(param));
    }

    @ApiOperation("团详情(分享页)")
    @RequestMapping(value = "/detail/{teamNo}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<GroupBuyTeamDetail> detail(@PathVariable String teamNo) {
        return CommonResult.success(groupBuyOrderService.getTeamDetail(teamNo));
    }

    @ApiOperation("我的参团记录")
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SmsGroupBuyRecord>> my() {
        Long memberId = memberService.getCurrentMember().getId();
        return CommonResult.success(groupBuyOrderService.listMyRecords(memberId));
    }

    @ApiOperation("取消未支付参团")
    @RequestMapping(value = "/cancel/{recordId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult cancel(@PathVariable Long recordId) {
        Long memberId = memberService.getCurrentMember().getId();
        int count = groupBuyOrderService.cancelRecord(recordId, memberId);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
