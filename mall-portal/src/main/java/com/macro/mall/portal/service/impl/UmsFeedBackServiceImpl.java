package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.UmsFeedBackMapper;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsFeedBack;
//import com.macro.mall.model.UmsMemberReceiveAddressExample;
import com.macro.mall.portal.service.UmsFeedBackService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户意见反馈Service实现类
 */
@Service
public class UmsFeedBackServiceImpl implements UmsFeedBackService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsFeedBackMapper feedbackMapper;

    @Override
    public int add(UmsFeedBack feedback) {
        UmsMember currentMember = memberService.getCurrentMember();
        feedback.setMemberId(currentMember.getId());
        return feedbackMapper.insert(feedback);
    }
}