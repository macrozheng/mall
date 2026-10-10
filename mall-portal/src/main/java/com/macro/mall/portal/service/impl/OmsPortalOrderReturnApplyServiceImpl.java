package com.macro.mall.portal.service.impl;

import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.OmsOrderReturnApplyMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.OmsOrderReturnApply;
import com.macro.mall.model.UmsMember;
import com.macro.mall.portal.domain.OmsOrderReturnApplyParam;
import com.macro.mall.portal.service.OmsPortalOrderReturnApplyService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 订单退货管理Service实现类
 * Created by macro on 2018/10/17.
 */
@Service
public class OmsPortalOrderReturnApplyServiceImpl implements OmsPortalOrderReturnApplyService {
    @Autowired
    private OmsOrderReturnApplyMapper returnApplyMapper;
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private UmsMemberService memberService;
    @Override
    public int create(OmsOrderReturnApplyParam returnApply) {
        //校验订单归属，不能对其他会员的订单发起退货申请
        UmsMember currentMember = memberService.getCurrentMember();
        OmsOrder order = orderMapper.selectByPrimaryKey(returnApply.getOrderId());
        if (order == null || !currentMember.getId().equals(order.getMemberId())) {
            Asserts.fail("订单不存在或无权限操作");
        }
        OmsOrderReturnApply realApply = new OmsOrderReturnApply();
        BeanUtils.copyProperties(returnApply,realApply);
        realApply.setCreateTime(new Date());
        realApply.setStatus(0);
        return returnApplyMapper.insert(realApply);
    }
}
