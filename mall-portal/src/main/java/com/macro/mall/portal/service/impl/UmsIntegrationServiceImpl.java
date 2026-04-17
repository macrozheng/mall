package com.macro.mall.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.*;
import com.macro.mall.model.*;
import com.macro.mall.portal.service.UmsIntegrationService;
import com.macro.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class UmsIntegrationServiceImpl implements UmsIntegrationService {

    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private UmsIntegrationChangeHistoryMapper integrationChangeHistoryMapper;
    @Autowired
    private UmsIntegrationRuleMapper integrationRuleMapper;
    @Autowired
    private UmsIntegrationLevelMapper integrationLevelMapper;
    @Autowired
    private UmsIntegrationConsumeSettingMapper integrationConsumeSettingMapper;

    @Override
    @Transactional
    public int addIntegration(Long memberId, Integer amount, String sourceType, String note, Long orderId) {
        UmsMember member = memberMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            Asserts.fail("会员不存在");
        }
        
        UmsIntegrationLevel currentLevel = getMemberLevel(member.getIntegration());
        if (currentLevel != null && currentLevel.getPriviledgeIntegrationRate() != null) {
            amount = (int) (amount * currentLevel.getPriviledgeIntegrationRate().doubleValue());
        }
        
        int newIntegration = (member.getIntegration() != null ? member.getIntegration() : 0) + amount;
        memberService.updateIntegration(memberId, newIntegration);
        
        UmsIntegrationChangeHistory history = new UmsIntegrationChangeHistory();
        history.setMemberId(memberId);
        history.setCreateTime(new Date());
        history.setChangeType(0);
        history.setChangeCount(amount);
        history.setOperateMan("系统");
        history.setOperateNote(note);
        history.setSourceType(getSourceTypeValue(sourceType));
        return integrationChangeHistoryMapper.insertSelective(history);
    }

    @Override
    @Transactional
    public int consumeIntegration(Long memberId, Integer amount, String note, Long orderId) {
        UmsMember member = memberMapper.selectByPrimaryKey(memberId);
        if (member == null) {
            Asserts.fail("会员不存在");
        }
        
        int currentIntegration = member.getIntegration() != null ? member.getIntegration() : 0;
        if (currentIntegration < amount) {
            Asserts.fail("积分不足");
        }
        
        int newIntegration = currentIntegration - amount;
        memberService.updateIntegration(memberId, newIntegration);
        
        UmsIntegrationChangeHistory history = new UmsIntegrationChangeHistory();
        history.setMemberId(memberId);
        history.setCreateTime(new Date());
        history.setChangeType(1);
        history.setChangeCount(amount);
        history.setOperateMan("系统");
        history.setOperateNote(note);
        history.setSourceType(5);
        return integrationChangeHistoryMapper.insertSelective(history);
    }

    @Override
    public List<UmsIntegrationChangeHistory> getIntegrationHistory(Integer pageNum, Integer pageSize, Integer changeType) {
        UmsMember currentMember = memberService.getCurrentMember();
        PageHelper.startPage(pageNum, pageSize);
        UmsIntegrationChangeHistoryExample example = new UmsIntegrationChangeHistoryExample();
        UmsIntegrationChangeHistoryExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(currentMember.getId());
        if (changeType != null) {
            criteria.andChangeTypeEqualTo(changeType);
        }
        example.setOrderByClause("create_time desc");
        return integrationChangeHistoryMapper.selectByExample(example);
    }

    @Override
    public UmsIntegrationLevel getCurrentLevel() {
        UmsMember currentMember = memberService.getCurrentMember();
        return getMemberLevel(currentMember.getIntegration());
    }

    @Override
    public List<UmsIntegrationLevel> getAllLevels() {
        UmsIntegrationLevelExample example = new UmsIntegrationLevelExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("sort asc");
        return integrationLevelMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> getIntegrationStats() {
        UmsMember currentMember = memberService.getCurrentMember();
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalIntegration", currentMember.getIntegration() != null ? currentMember.getIntegration() : 0);
        
        UmsIntegrationChangeHistoryExample addExample = new UmsIntegrationChangeHistoryExample();
        addExample.createCriteria().andMemberIdEqualTo(currentMember.getId()).andChangeTypeEqualTo(0);
        long totalEarned = integrationChangeHistoryMapper.countByExample(addExample);
        stats.put("totalEarned", totalEarned);
        
        UmsIntegrationChangeHistoryExample consumeExample = new UmsIntegrationChangeHistoryExample();
        consumeExample.createCriteria().andMemberIdEqualTo(currentMember.getId()).andChangeTypeEqualTo(1);
        long totalConsumed = integrationChangeHistoryMapper.countByExample(consumeExample);
        stats.put("totalConsumed", totalConsumed);
        
        UmsIntegrationLevel currentLevel = getMemberLevel(currentMember.getIntegration());
        stats.put("currentLevel", currentLevel);
        
        if (currentLevel != null) {
            List<UmsIntegrationLevel> allLevels = getAllLevels();
            int currentIndex = allLevels.indexOf(currentLevel);
            if (currentIndex < allLevels.size() - 1) {
                UmsIntegrationLevel nextLevel = allLevels.get(currentIndex + 1);
                stats.put("nextLevel", nextLevel);
                int needed = nextLevel.getMinIntegration() - currentMember.getIntegration();
                stats.put("neededForNextLevel", needed > 0 ? needed : 0);
            }
        }
        
        return stats;
    }

    @Override
    public Integer calculateConsumeIntegration(BigDecimal amount) {
        UmsIntegrationConsumeSetting setting = integrationConsumeSettingMapper.selectByPrimaryKey(1L);
        if (setting == null) {
            return 0;
        }
        return amount.multiply(new BigDecimal(setting.getDeductionPerAmount())).intValue();
    }

    @Override
    public BigDecimal calculateIntegrationAmount(Integer integration) {
        UmsIntegrationConsumeSetting setting = integrationConsumeSettingMapper.selectByPrimaryKey(1L);
        if (setting == null || setting.getDeductionPerAmount() == null || setting.getDeductionPerAmount() == 0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(integration).divide(new BigDecimal(setting.getDeductionPerAmount()), 2, RoundingMode.HALF_EVEN);
    }

    @Override
    @Transactional
    public void grantCommentIntegration(Long orderId, boolean hasImage) {
        UmsIntegrationRule rule = getRuleByType(2);
        if (rule == null || rule.getStatus() == 0) return;
        
        UmsMember currentMember = memberService.getCurrentMember();
        int baseIntegration = rule.getBaseIntegration() != null ? rule.getBaseIntegration() : 20;
        int imageIntegration = hasImage && rule.getCommentImageIntegration() != null ? rule.getCommentImageIntegration() : 0;
        int total = baseIntegration + imageIntegration;
        
        addIntegration(currentMember.getId(), total, "评价", "评价获得积分", orderId);
    }

    @Override
    @Transactional
    public void grantShareIntegration(Long memberId) {
        UmsIntegrationRule rule = getRuleByType(4);
        if (rule == null || rule.getStatus() == 0) return;
        
        int shareIntegration = rule.getShareIntegration() != null ? rule.getShareIntegration() : 5;
        addIntegration(memberId, shareIntegration, "分享", "分享获得积分", null);
    }

    @Override
    @Transactional
    public void grantRegisterIntegration(Long memberId) {
        UmsIntegrationRule rule = getRuleByType(3);
        if (rule == null || rule.getStatus() == 0) return;
        
        int registerIntegration = rule.getRegisterIntegration() != null ? rule.getRegisterIntegration() : 50;
        addIntegration(memberId, registerIntegration, "注册", "注册获得积分", null);
    }

    @Override
    @Transactional
    public void grantBirthdayIntegration(Long memberId) {
        UmsIntegrationRule rule = getRuleByType(5);
        if (rule == null || rule.getStatus() == 0) return;
        
        UmsIntegrationLevel level = getMemberLevel(memberMapper.selectByPrimaryKey(memberId).getIntegration());
        int baseIntegration = rule.getBirthdayIntegration() != null ? rule.getBirthdayIntegration() : 100;
        int extraIntegration = level != null && level.getPriviledgeBirthdayIntegration() != null ? level.getPriviledgeBirthdayIntegration() : 0;
        int total = baseIntegration + extraIntegration;
        
        addIntegration(memberId, total, "生日", "生日赠送积分", null);
    }

    private UmsIntegrationRule getRuleByType(Integer ruleType) {
        UmsIntegrationRuleExample example = new UmsIntegrationRuleExample();
        example.createCriteria().andRuleTypeEqualTo(ruleType);
        List<UmsIntegrationRule> rules = integrationRuleMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(rules)) {
            return rules.get(0);
        }
        return null;
    }

    private UmsIntegrationLevel getMemberLevel(Integer integration) {
        if (integration == null) integration = 0;
        
        UmsIntegrationLevelExample example = new UmsIntegrationLevelExample();
        example.createCriteria().andStatusEqualTo(1).andMinIntegrationLessThanOrEqualTo(integration);
        example.setOrderByClause("min_integration desc");
        
        List<UmsIntegrationLevel> levels = integrationLevelMapper.selectByExample(example);
        if (CollUtil.isNotEmpty(levels)) {
            return levels.get(0);
        }
        return null;
    }

    private Integer getSourceTypeValue(String sourceType) {
        Map<String, Integer> typeMap = new HashMap<>();
        typeMap.put("购物", 0);
        typeMap.put("管理员修改", 1);
        typeMap.put("签到", 6);
        typeMap.put("评价", 7);
        typeMap.put("分享", 8);
        typeMap.put("注册", 9);
        typeMap.put("生日", 10);
        return typeMap.getOrDefault(sourceType, 0);
    }
}
