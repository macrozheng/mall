package com.macro.mall.portal.service.impl;

import com.macro.mall.mapper.SmsGroupBuyLogMapper;
import com.macro.mall.model.SmsGroupBuyLog;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.portal.service.GroupBuyLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupBuyLogServiceImplTest {

    @Mock
    private SmsGroupBuyLogMapper logMapper;

    @InjectMocks
    private GroupBuyLogServiceImpl service;

    @Test
    void record_shouldSwallowExceptionsToProtectMainFlow() {
        when(logMapper.insertSelective(any())).thenThrow(new RuntimeException("DB down"));

        SmsGroupBuyLog log = new SmsGroupBuyLog();
        log.setOperateType(GroupBuyLogService.OP_OPEN_GROUP);
        log.setTeamId(1L);

        // 最关键断言:异常不能抛出,保证主业务不被日志失败波及
        assertDoesNotThrow(() -> service.record(log));
    }

    @Test
    void record_shouldFillCreateTimeAndDefaultSource() {
        when(logMapper.insertSelective(any())).thenReturn(1);
        SmsGroupBuyLog log = new SmsGroupBuyLog();
        log.setOperateType(GroupBuyLogService.OP_PAY_SUCCESS);

        service.record(log);

        ArgumentCaptor<SmsGroupBuyLog> captor = ArgumentCaptor.forClass(SmsGroupBuyLog.class);
        verify(logMapper).insertSelective(captor.capture());
        assertNotNull(captor.getValue().getCreateTime());
        assertEquals(GroupBuyLogService.SRC_MEMBER, captor.getValue().getOperateSource());
    }

    @Test
    void record_nullLogShouldNoop() {
        assertDoesNotThrow(() -> service.record(null));
        verify(logMapper, never()).insertSelective(any());
    }

    @Test
    void convenienceRecord_shouldPopulateFieldsFromTeamAndRecord() {
        when(logMapper.insertSelective(any())).thenReturn(1);
        SmsGroupBuyTeam team = new SmsGroupBuyTeam();
        team.setId(1L);
        team.setTeamNo("T-001");
        team.setActivityId(9L);
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(2L);
        record.setMemberId(100L);
        record.setMemberNickname("tom");
        record.setOrderSn("SN123");
        record.setActivityId(9L);

        service.record(GroupBuyLogService.OP_JOIN_GROUP, GroupBuyLogService.SRC_MEMBER,
                team, record, null, 0, "参团");

        ArgumentCaptor<SmsGroupBuyLog> captor = ArgumentCaptor.forClass(SmsGroupBuyLog.class);
        verify(logMapper).insertSelective(captor.capture());
        SmsGroupBuyLog saved = captor.getValue();
        assertEquals(GroupBuyLogService.OP_JOIN_GROUP, saved.getOperateType());
        assertEquals(1L, saved.getTeamId());
        assertEquals("T-001", saved.getTeamNo());
        assertEquals(9L, saved.getActivityId());
        assertEquals(2L, saved.getRecordId());
        assertEquals(100L, saved.getMemberId());
        assertEquals("tom", saved.getMemberNickname());
        assertEquals("SN123", saved.getOrderSn());
        assertEquals("参团", saved.getDetail());
    }
}
