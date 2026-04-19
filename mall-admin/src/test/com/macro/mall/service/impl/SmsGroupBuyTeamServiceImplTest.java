package com.macro.mall.service.impl;

import com.macro.mall.common.exception.ApiException;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.mapper.SmsGroupBuyTeamMapper;
import com.macro.mall.model.SmsGroupBuyTeam;
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
class SmsGroupBuyTeamServiceImplTest {

    @Mock
    private SmsGroupBuyTeamMapper teamMapper;

    @Mock
    private SmsGroupBuyActivityDao activityDao;

    @InjectMocks
    private SmsGroupBuyTeamServiceImpl service;

    @Test
    void forceClose_shouldRejectWhenTeamNotFound() {
        when(teamMapper.selectByPrimaryKey(1L)).thenReturn(null);
        assertThrows(ApiException.class, () -> service.forceClose(1L));
    }

    @Test
    void forceClose_shouldRejectWhenTeamNotOngoing() {
        SmsGroupBuyTeam team = new SmsGroupBuyTeam();
        team.setId(1L);
        team.setStatus(1); // 已成团
        when(teamMapper.selectByPrimaryKey(1L)).thenReturn(team);

        assertThrows(ApiException.class, () -> service.forceClose(1L));
        verify(teamMapper, never()).updateByPrimaryKeySelective(any());
    }

    @Test
    void forceClose_shouldSetStatusToClosedAndCloseTime() {
        SmsGroupBuyTeam team = new SmsGroupBuyTeam();
        team.setId(1L);
        team.setStatus(0);
        when(teamMapper.selectByPrimaryKey(1L)).thenReturn(team);
        when(teamMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        ArgumentCaptor<SmsGroupBuyTeam> captor = ArgumentCaptor.forClass(SmsGroupBuyTeam.class);
        assertEquals(1, service.forceClose(1L));
        verify(teamMapper).updateByPrimaryKeySelective(captor.capture());
        assertEquals(3, captor.getValue().getStatus());
        assertNotNull(captor.getValue().getCloseTime());
    }
}
