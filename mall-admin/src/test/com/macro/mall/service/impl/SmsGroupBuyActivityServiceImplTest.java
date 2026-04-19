package com.macro.mall.service.impl;

import com.macro.mall.common.exception.ApiException;
import com.macro.mall.dao.SmsGroupBuyActivityDao;
import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.model.SmsGroupBuyActivity;
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
class SmsGroupBuyActivityServiceImplTest {

    @Mock
    private SmsGroupBuyActivityMapper activityMapper;

    @Mock
    private SmsGroupBuyActivityDao activityDao;

    @InjectMocks
    private SmsGroupBuyActivityServiceImpl service;

    private SmsGroupBuyActivity validActivity() {
        SmsGroupBuyActivity a = new SmsGroupBuyActivity();
        a.setTitle("双11拼团");
        a.setGroupSize(3);
        a.setValidHours(24);
        return a;
    }

    @Test
    void create_shouldRejectGroupSizeLessThanTwo() {
        SmsGroupBuyActivity a = validActivity();
        a.setGroupSize(1);
        assertThrows(ApiException.class, () -> service.create(a));
        verify(activityMapper, never()).insertSelective(any());
    }

    @Test
    void create_shouldDefaultValidHoursWhenInvalid() {
        SmsGroupBuyActivity a = validActivity();
        a.setValidHours(0);
        when(activityMapper.insertSelective(any())).thenReturn(1);

        int count = service.create(a);

        assertEquals(1, count);
        assertEquals(24, a.getValidHours());
        assertEquals(0, a.getStatus());
        assertEquals(0, a.getTotalGroupCount());
        assertEquals(0, a.getSuccessGroupCount());
        assertNotNull(a.getCreateTime());
    }

    @Test
    void update_shouldRejectWhenOngoingTeamExistsAndActivityOnline() {
        SmsGroupBuyActivity current = validActivity();
        current.setId(10L);
        current.setStatus(1);
        when(activityMapper.selectByPrimaryKey(10L)).thenReturn(current);
        when(activityDao.countOngoingTeam(10L)).thenReturn(2);

        assertThrows(ApiException.class, () -> service.update(10L, validActivity()));
        verify(activityMapper, never()).updateByPrimaryKeySelective(any());
    }

    @Test
    void update_shouldAllowWhenActivityOffline() {
        SmsGroupBuyActivity current = validActivity();
        current.setId(10L);
        current.setStatus(0);
        when(activityMapper.selectByPrimaryKey(10L)).thenReturn(current);
        when(activityMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        int count = service.update(10L, validActivity());

        assertEquals(1, count);
        verify(activityDao, never()).countOngoingTeam(any());
    }

    @Test
    void delete_shouldRejectWhenOngoingTeamExists() {
        when(activityDao.countOngoingTeam(5L)).thenReturn(1);
        assertThrows(ApiException.class, () -> service.delete(5L));
        verify(activityMapper, never()).deleteByPrimaryKey(any());
    }

    @Test
    void delete_shouldRejectWhenActivityOnline() {
        SmsGroupBuyActivity current = validActivity();
        current.setStatus(1);
        when(activityDao.countOngoingTeam(5L)).thenReturn(0);
        when(activityMapper.selectByPrimaryKey(5L)).thenReturn(current);
        assertThrows(ApiException.class, () -> service.delete(5L));
        verify(activityMapper, never()).deleteByPrimaryKey(any());
    }

    @Test
    void updateStatus_shouldSetIdAndStatusOnly() {
        when(activityMapper.updateByPrimaryKeySelective(any())).thenReturn(1);
        ArgumentCaptor<SmsGroupBuyActivity> captor = ArgumentCaptor.forClass(SmsGroupBuyActivity.class);

        int count = service.updateStatus(7L, 1);

        assertEquals(1, count);
        verify(activityMapper).updateByPrimaryKeySelective(captor.capture());
        assertEquals(7L, captor.getValue().getId());
        assertEquals(1, captor.getValue().getStatus());
        assertNotNull(captor.getValue().getUpdateTime());
    }
}
