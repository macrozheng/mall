package com.macro.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.UmsMemberParam;
import com.macro.mall.dto.UmsMemberQueryParam;
import com.macro.mall.model.UmsMember;
import com.macro.mall.service.UmsMemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 会员管理Controller
 * Created by pray on 2019/3/6.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员管理")
@RequestMapping("/user")
public class UmsMemberController {
	
	@Autowired
	private UmsMemberService memberService;
	
	 @ApiOperation("创建用户")
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    @ResponseBody
	    @PreAuthorize("hasAuthority('ums:member:create')")
	    public Object create(@RequestBody UmsMemberParam memberParam, BindingResult bindingResult) {
	        int count = memberService.create(memberParam);
	        if (count > 0) {
	            return new CommonResult().success(count);
	        } else {
	            return new CommonResult().failed();
	        }
	    }
	 @ApiOperation("更新会员")
	    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	    @ResponseBody
	    @PreAuthorize("hasAuthority('ums:member:update')")
	    public Object update(@PathVariable Long id, @RequestBody UmsMember member, BindingResult bindingResult) {
	        int count = memberService.update(id, member);
	        if (count > 0) {
	            return new CommonResult().success(count);
	        } else {
	            return new CommonResult().failed();
	        }
	    }
	 @ApiOperation("查询会员列表")
	    @RequestMapping(value = "/list", method = RequestMethod.GET)
	    @ResponseBody
	    @PreAuthorize("hasAuthority('ums:member:read')")
	    public Object getList(UmsMemberQueryParam memberQueryParam,
	                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
	                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
	        List<UmsMember> memberList = memberService.list(memberQueryParam, pageSize, pageNum);
	        return new CommonResult().pageSuccess(memberList);
	    }

	    @ApiOperation("根据商品名称或货号模糊查询")
	    @RequestMapping(value = "/simpleList", method = RequestMethod.GET)
	    @ResponseBody
	    public Object getList(String  keyword) {
	        List<UmsMember> memberList = memberService.list(keyword);
	        return new CommonResult().success(memberList);
	    }
	    
	    @ApiOperation("修改启用状态")
	    @RequestMapping(value = "/update/status",method = RequestMethod.POST)
	    @ResponseBody
	    @PreAuthorize("hasAuthority('ums:member:update')")
	    public Object updateStatus(@RequestParam("id") Long id,
	                                  @RequestParam("status") Integer status) {
	        int count = memberService.updateVerifyStatus(id, status);
	        if (count > 0) {
	            return new CommonResult().success(count);
	        } else {
	            return new CommonResult().failed();
	        }
	    }
	    @ApiOperation("删除会员账户")
	    @RequestMapping(value = "/update/delete",method = RequestMethod.POST)
	    @ResponseBody
	    @PreAuthorize("hasAuthority('ums:member:delete')")
	    public Object updateDeleteStatus(@RequestParam("id") Long id) {
	        int count = memberService.deleteMember(id);
	        if (count > 0) {
	            return new CommonResult().success(count);
	        } else {
	            return new CommonResult().failed();
	        }
	    }
}
