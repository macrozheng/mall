package com.macro.mall.portal.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.UmsIntegrationChangeHistoryMapper;
import com.macro.mall.mapper.UmsIntegrationRuleMapper;
import com.macro.mall.mapper.UmsMemberMapper;
import com.macro.mall.mapper.UmsMemberSignInMapper;
import com.macro.mall.model.*;
import com.macro.mall.portal.service.UmsMemberService;
import com.macro.mall.portal.service.UmsMemberSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UmsMemberSignInServiceImpl implements UmsMemberSignInService {

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberSignInMapper signInMapper;
    @Autowired
    private UmsIntegrationRuleMapper integrationRuleMapper;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private UmsIntegrationChangeHistoryMapper integrationChangeHistoryMapper;

    @Override
    @Transactional
    public UmsMemberSignIn signIn() {
        UmsMember currentMember = memberService.getCurrentMember();
        
        if (isSignedInToday()) {
            Asserts.fail("今日已签到");
        }
        
        UmsIntegrationRule signInRule = getSignInRule();
        if (signInRule == null) {
            Asserts.fail("签到规则未配置");
        }
        
        int continueDays = calculateContinueDays(currentMember.getId());
        int baseIntegration = signInRule.getBaseIntegration() != null ? signInRule.getBaseIntegration() : 10;
        int extraIntegration = 0;
        int isExtra = 0;
        
        if (signInRule.getContinueDaysIntegration() != null && signInRule.getContinueDaysIntegration() > 0) {
            if (signInRule.getMaxContinueDays() == null || continueDays <= signInRule.getMaxContinueDays()) {
                extraIntegration = signInRule.getContinueDaysIntegration();
                isExtra = 1;
            }
        }
        
        int totalIntegration = baseIntegration + extraIntegration;
        
        UmsMemberSignIn signIn = new UmsMemberSignIn();
        signIn.setMemberId(currentMember.getId());
        signIn.setSignInDate(new Date());
        signIn.setIntegration(baseIntegration);
        signIn.setContinueDays(continueDays + 1);
        signIn.setIsExtra(isExtra);
        signIn.setExtraIntegration(extraIntegration);
        signIn.setCreateTime(new Date());
        signInMapper.insertSelective(signIn);
        
        addIntegration(currentMember.getId(), totalIntegration, "签到获得积分");
        
        return signIn;
    }

    @Override
    public boolean isSignedInToday() {
        UmsMember currentMember = memberService.getCurrentMember();
        Date today = new Date();
        Date startOfDay = DateUtil.beginOfDay(today);
        Date endOfDay = DateUtil.endOfDay(today);
        
        UmsMemberSignInExample example = new UmsMemberSignInExample();
        example.createCriteria()
                .andMemberIdEqualTo(currentMember.getId())
                .andSignInDateBetween(startOfDay, endOfDay);
        long count = signInMapper.countByExample(example);
        return count > 0;
    }

    @Override
    public Integer getContinueDays() {
        UmsMember currentMember = memberService.getCurrentMember();
        return calculateContinueDays(currentMember.getId());
    }

    @Override
    public List<UmsMemberSignIn> getSignInHistory(Integer pageNum, Integer pageSize) {
        UmsMember currentMember = memberService.getCurrentMember();
        PageHelper.startPage(pageNum, pageSize);
        UmsMemberSignInExample example = new UmsMemberSignInExample();
        example.createCriteria().andMemberIdEqualTo(currentMember.getId());
        example.setOrderByClause("sign_in_date desc");
        return signInMapper.selectByExample(example);
    }

    private UmsIntegrationRule getSignInRule() {
        UmsIntegrationRuleExample example = new UmsIntegrationRuleExample();
        example.createCriteria().andRuleTypeEqualTo(1).andStatusEqualTo(1);
        List<UmsIntegrationRule> rules = integrationRuleMapper.selectByExample(example);
        if (rules != null && !rules.isEmpty()) {
            return rules.get(0);
        }
        return null;
    }

    private int calculateContinueDays(Long memberId) {
        Date yesterday = DateUtil.yesterday();
        Date startOfYesterday = DateUtil.beginOfDay(yesterday);
        Date endOfYesterday = DateUtil.endOfDay(yesterday);
        
        UmsMemberSignInExample example = new UmsMemberSignInExample();
        example.createCriteria()
                .andMemberIdEqualTo(memberId)
                .andSignInDateBetween(startOfYesterday, endOfYesterday);
        example.setOrderByClause("sign_in_date desc");
        List<UmsMemberSignIn> yesterdaySignIn = signInMapper.selectByExample(example);
        
        if (yesterdaySignIn == null || yesterdaySignIn.isEmpty()) {
            return 0;
        }
        
        return yesterdaySignIn.get(0).getContinueDays();
    }

    private void addIntegration(Long memberId, Integer amount, String note) {
        UmsMember member = memberMapper.selectByPrimaryKey(memberId);
        if (member == null) return;
        
        int newIntegration = (member.getIntegration() != null ? member.getIntegration() : 0) + amount;
        memberService.updateIntegration(memberId, newIntegration);
        
        UmsIntegrationChangeHistory history = new UmsIntegrationChangeHistory();
        history.setMemberId(memberId);
        history.setCreateTime(new Date());
        history.setChangeType(0);
        history.setChangeCount(amount);
        history.setOperateMan("系统");
        history.setOperateNote(note);
        history.setSourceType(6);
        integrationChangeHistoryMapper.insertSelective(history);
    }
}
