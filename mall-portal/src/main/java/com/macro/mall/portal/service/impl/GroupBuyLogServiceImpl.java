package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.SmsGroupBuyLogMapper;
import com.macro.mall.model.SmsGroupBuyLog;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.portal.service.GroupBuyLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 拼团日志Service实现
 * 使用 REQUIRES_NEW 事务,避免主业务回滚影响日志;同时 catch 所有异常保证不影响主流程
 */
@Service
public class GroupBuyLogServiceImpl implements GroupBuyLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBuyLogServiceImpl.class);

    @Autowired
    private SmsGroupBuyLogMapper logMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void record(SmsGroupBuyLog log) {
        try {
            if (log == null) {
                return;
            }
            if (log.getCreateTime() == null) {
                log.setCreateTime(new Date());
            }
            if (log.getOperateSource() == null) {
                log.setOperateSource(SRC_MEMBER);
            }
            logMapper.insertSelective(log);
        } catch (Exception e) {
            LOGGER.error("write groupBuy log failed, operateType={}, teamId={}",
                    log == null ? null : log.getOperateType(),
                    log == null ? null : log.getTeamId(), e);
        }
    }

    @Override
    public void record(int operateType, int operateSource, SmsGroupBuyTeam team,
                       SmsGroupBuyRecord record, Integer beforeStatus, Integer afterStatus,
                       String detail) {
        SmsGroupBuyLog log = new SmsGroupBuyLog();
        log.setOperateType(operateType);
        log.setOperateSource(operateSource);
        if (team != null) {
            log.setTeamId(team.getId());
            log.setTeamNo(team.getTeamNo());
            log.setActivityId(team.getActivityId());
        }
        if (record != null) {
            log.setRecordId(record.getId());
            log.setMemberId(record.getMemberId());
            log.setMemberNickname(record.getMemberNickname());
            log.setOrderSn(record.getOrderSn());
            if (log.getActivityId() == null) log.setActivityId(record.getActivityId());
        }
        log.setBeforeStatus(beforeStatus);
        log.setAfterStatus(afterStatus);
        log.setDetail(detail);
        record(log);
    }
}
