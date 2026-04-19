package com.macro.mall.portal.service.impl;

import com.macro.mall.common.exception.ApiException;
import com.macro.mall.common.service.RedisService;
import com.macro.mall.mapper.OmsOrderItemMapper;
import com.macro.mall.mapper.OmsOrderMapper;
import com.macro.mall.mapper.SmsGroupBuyActivityMapper;
import com.macro.mall.mapper.SmsGroupBuyRecordMapper;
import com.macro.mall.mapper.SmsGroupBuyTeamMapper;
import com.macro.mall.model.OmsOrder;
import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyRecord;
import com.macro.mall.model.SmsGroupBuyTeam;
import com.macro.mall.model.UmsMember;
import com.macro.mall.model.UmsMemberReceiveAddress;
import com.macro.mall.portal.component.GroupBuyTimeOutSender;
import com.macro.mall.portal.dao.GroupBuyPortalDao;
import com.macro.mall.portal.domain.GroupBuyJoinParam;
import com.macro.mall.portal.domain.GroupBuyOpenParam;
import com.macro.mall.portal.domain.GroupBuyOrderResult;
import com.macro.mall.portal.service.GroupBuyLogService;
import com.macro.mall.portal.service.UmsMemberReceiveAddressService;
import com.macro.mall.portal.service.UmsMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupBuyOrderServiceImplTest {

    @Mock private UmsMemberService memberService;
    @Mock private UmsMemberReceiveAddressService addressService;
    @Mock private SmsGroupBuyActivityMapper activityMapper;
    @Mock private SmsGroupBuyTeamMapper teamMapper;
    @Mock private SmsGroupBuyRecordMapper recordMapper;
    @Mock private OmsOrderMapper orderMapper;
    @Mock private OmsOrderItemMapper orderItemMapper;
    @Mock private GroupBuyPortalDao portalDao;
    @Mock private GroupBuyLogService groupBuyLogService;
    @Mock private GroupBuyTimeOutSender timeOutSender;
    @Mock private RedisService redisService;

    @InjectMocks
    private GroupBuyOrderServiceImpl service;

    private UmsMember member;
    private SmsGroupBuyActivity activity;
    private SmsGroupBuyProduct product;
    private UmsMemberReceiveAddress address;

    @BeforeEach
    void setUp() {
        member = new UmsMember();
        member.setId(100L);
        member.setUsername("tom");
        member.setNickname("Tom");
        member.setIcon("http://icon");

        activity = new SmsGroupBuyActivity();
        activity.setId(1L);
        activity.setTitle("十一拼团");
        activity.setGroupSize(3);
        activity.setValidHours(24);
        activity.setStatus(1);
        activity.setStartTime(new Date(System.currentTimeMillis() - 3600_000));
        activity.setEndTime(new Date(System.currentTimeMillis() + 7 * 24 * 3600_000));
        activity.setLimitPerMember(1);

        product = new SmsGroupBuyProduct();
        product.setId(50L);
        product.setActivityId(1L);
        product.setProductId(10L);
        product.setProductSkuId(20L);
        product.setProductName("电动牙刷");
        product.setProductPic("http://pic");
        product.setSkuCode("SKU-20");
        product.setOriginalPrice(new BigDecimal("299.00"));
        product.setGroupPrice(new BigDecimal("199.00"));
        product.setGroupStock(50);
        product.setLockedStock(0);
        product.setSoldCount(0);
        product.setLimitPerOrder(5);

        address = new UmsMemberReceiveAddress();
        address.setId(500L);
        address.setName("张三");
        address.setPhoneNumber("13800000000");
        address.setProvince("广东省");
        address.setCity("深圳市");
        address.setRegion("南山区");
        address.setDetailAddress("科技园");
    }

    private GroupBuyOpenParam openParam() {
        GroupBuyOpenParam p = new GroupBuyOpenParam();
        p.setActivityId(1L);
        p.setProductSkuId(20L);
        p.setQuantity(1);
        p.setMemberReceiveAddressId(500L);
        return p;
    }

    // ================== launchGroup ==================

    @Test
    void launchGroup_shouldRejectIncompleteParam() {
        assertThrows(ApiException.class, () -> service.launchGroup(new GroupBuyOpenParam()));
    }

    @Test
    void launchGroup_shouldRejectWhenActivityOffline() {
        activity.setStatus(0);
        when(memberService.getCurrentMember()).thenReturn(member);
        when(activityMapper.selectByPrimaryKey(1L)).thenReturn(activity);

        assertThrows(ApiException.class, () -> service.launchGroup(openParam()));
        verify(portalDao, never()).lockStock(anyLong(), anyInt());
    }

    @Test
    void launchGroup_shouldRejectWhenMemberLimitReached() {
        when(memberService.getCurrentMember()).thenReturn(member);
        when(activityMapper.selectByPrimaryKey(1L)).thenReturn(activity);
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);
        when(portalDao.countMemberJoin(1L, 100L)).thenReturn(1);

        assertThrows(ApiException.class, () -> service.launchGroup(openParam()));
        verify(portalDao, never()).lockStock(anyLong(), anyInt());
    }

    @Test
    void launchGroup_shouldRejectWhenStockInsufficient() {
        when(memberService.getCurrentMember()).thenReturn(member);
        when(activityMapper.selectByPrimaryKey(1L)).thenReturn(activity);
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);
        when(portalDao.countMemberJoin(1L, 100L)).thenReturn(0);
        when(portalDao.lockStock(50L, 1)).thenReturn(0);

        assertThrows(ApiException.class, () -> service.launchGroup(openParam()));
        verify(teamMapper, never()).insertSelective(any());
        verify(orderMapper, never()).insertSelective(any());
    }

    @Test
    void launchGroup_happyPath_shouldCreateTeamOrderRecordAndSendTimeout() {
        when(memberService.getCurrentMember()).thenReturn(member);
        when(activityMapper.selectByPrimaryKey(1L)).thenReturn(activity);
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);
        when(portalDao.countMemberJoin(1L, 100L)).thenReturn(0);
        when(portalDao.lockStock(50L, 1)).thenReturn(1);
        when(addressService.getItem(500L)).thenReturn(address);
        when(redisService.incr(anyString(), eq(1L))).thenReturn(1L);
        // 让 insert 产生可用的 id
        doAnswer(inv -> { ((SmsGroupBuyTeam) inv.getArgument(0)).setId(77L); return 1; })
                .when(teamMapper).insertSelective(any());
        doAnswer(inv -> { ((OmsOrder) inv.getArgument(0)).setId(1000L); return 1; })
                .when(orderMapper).insertSelective(any());
        doAnswer(inv -> { ((SmsGroupBuyRecord) inv.getArgument(0)).setId(7L); return 1; })
                .when(recordMapper).insertSelective(any());

        GroupBuyOrderResult result = service.launchGroup(openParam());

        assertNotNull(result);
        assertEquals(77L, result.getTeamId());
        assertEquals(1000L, result.getOrderId());
        assertEquals(7L, result.getRecordId());
        assertEquals(new BigDecimal("199.00"), result.getPayAmount());

        ArgumentCaptor<SmsGroupBuyTeam> teamCaptor = ArgumentCaptor.forClass(SmsGroupBuyTeam.class);
        verify(teamMapper).insertSelective(teamCaptor.capture());
        SmsGroupBuyTeam t = teamCaptor.getValue();
        assertEquals(100L, t.getLeaderMemberId());
        assertEquals(3, t.getTargetNum());
        assertEquals(0, t.getCurrentNum());
        assertEquals(0, t.getStatus());

        ArgumentCaptor<OmsOrder> orderCaptor = ArgumentCaptor.forClass(OmsOrder.class);
        verify(orderMapper).insertSelective(orderCaptor.capture());
        OmsOrder order = orderCaptor.getValue();
        assertEquals(2, order.getOrderType());           // 拼团订单
        assertEquals(0, order.getStatus());              // 待付款
        assertEquals(1L, order.getGroupActivityId());
        assertEquals(77L, order.getGroupTeamId());

        ArgumentCaptor<SmsGroupBuyRecord> recCaptor = ArgumentCaptor.forClass(SmsGroupBuyRecord.class);
        verify(recordMapper).insertSelective(recCaptor.capture());
        assertEquals(1, recCaptor.getValue().getIsLeader()); // 团长
        assertEquals(0, recCaptor.getValue().getJoinStatus());

        verify(portalDao).incrementTotalGroupCount(1L);
        verify(timeOutSender).sendTimeoutMessage(eq(77L), anyLong());
        verify(groupBuyLogService).record(eq(GroupBuyLogService.OP_OPEN_GROUP),
                eq(GroupBuyLogService.SRC_MEMBER), any(), any(), any(), eq(0), anyString());
    }

    // ================== joinGroup ==================

    private SmsGroupBuyTeam ongoingTeam() {
        SmsGroupBuyTeam t = new SmsGroupBuyTeam();
        t.setId(77L);
        t.setTeamNo("T77");
        t.setActivityId(1L);
        t.setProductId(10L);
        t.setProductSkuId(20L);
        t.setGroupPrice(new BigDecimal("199.00"));
        t.setTargetNum(3);
        t.setCurrentNum(1);
        t.setStatus(0);
        t.setExpireTime(new Date(System.currentTimeMillis() + 3600_000));
        return t;
    }

    private GroupBuyJoinParam joinParam() {
        GroupBuyJoinParam p = new GroupBuyJoinParam();
        p.setTeamNo("T77");
        p.setQuantity(1);
        p.setMemberReceiveAddressId(500L);
        return p;
    }

    @Test
    void joinGroup_shouldRejectWhenTeamNotFound() {
        when(memberService.getCurrentMember()).thenReturn(member);
        when(portalDao.getTeamByNo("T77")).thenReturn(null);
        assertThrows(ApiException.class, () -> service.joinGroup(joinParam()));
    }

    @Test
    void joinGroup_shouldRejectWhenTeamExpired() {
        SmsGroupBuyTeam expired = ongoingTeam();
        expired.setExpireTime(new Date(System.currentTimeMillis() - 1000));
        when(memberService.getCurrentMember()).thenReturn(member);
        when(portalDao.getTeamByNo("T77")).thenReturn(expired);
        assertThrows(ApiException.class, () -> service.joinGroup(joinParam()));
    }

    @Test
    void joinGroup_shouldRejectWhenTeamFull() {
        SmsGroupBuyTeam full = ongoingTeam();
        full.setCurrentNum(3);
        when(memberService.getCurrentMember()).thenReturn(member);
        when(portalDao.getTeamByNo("T77")).thenReturn(full);
        assertThrows(ApiException.class, () -> service.joinGroup(joinParam()));
    }

    @Test
    void joinGroup_shouldRejectOnDuplicateParticipation() {
        when(memberService.getCurrentMember()).thenReturn(member);
        when(portalDao.getTeamByNo("T77")).thenReturn(ongoingTeam());
        when(activityMapper.selectByPrimaryKey(1L)).thenReturn(activity);
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);
        when(portalDao.countMemberJoin(1L, 100L)).thenReturn(0);
        when(portalDao.lockStock(50L, 1)).thenReturn(1);
        when(addressService.getItem(500L)).thenReturn(address);
        when(redisService.incr(anyString(), eq(1L))).thenReturn(1L);
        doAnswer(inv -> { ((OmsOrder) inv.getArgument(0)).setId(2000L); return 1; })
                .when(orderMapper).insertSelective(any());
        when(recordMapper.insertSelective(any()))
                .thenThrow(new DuplicateKeyException("uk_team_member"));

        assertThrows(ApiException.class, () -> service.joinGroup(joinParam()));
    }

    // ================== handlePaySuccess ==================

    @Test
    void handlePaySuccess_shouldBeIdempotentOnProcessedRecord() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(1L);
        record.setJoinStatus(1); // 已是已支付
        when(portalDao.getRecordByOrderSn("SN")).thenReturn(record);

        service.handlePaySuccess("SN");

        verify(recordMapper, never()).updateByPrimaryKeySelective(any());
        verify(portalDao, never()).incrementTeamCurrentNum(anyLong());
    }

    @Test
    void handlePaySuccess_shouldIgnoreNonGroupOrder() {
        when(portalDao.getRecordByOrderSn("SN")).thenReturn(null);
        service.handlePaySuccess("SN");
        verifyNoInteractions(recordMapper, teamMapper);
    }

    @Test
    void handlePaySuccess_happyPath_shouldIncrementAndNotFinalizeWhenNotFull() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(1L);
        record.setTeamId(77L);
        record.setJoinStatus(0);
        when(portalDao.getRecordByOrderSn("SN")).thenReturn(record);
        when(portalDao.incrementTeamCurrentNum(77L)).thenReturn(1);

        SmsGroupBuyTeam team = ongoingTeam();
        team.setCurrentNum(2); // 还未满员
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);

        service.handlePaySuccess("SN");

        verify(recordMapper).updateByPrimaryKeySelective(any());
        verify(portalDao, never()).finalizeRecords(anyLong());
        verify(portalDao, never()).finalizeStock(anyLong(), anyInt());
    }

    @Test
    void handlePaySuccess_shouldFinalizeWhenTeamBecomesFull() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(1L);
        record.setTeamId(77L);
        record.setJoinStatus(0);
        when(portalDao.getRecordByOrderSn("SN")).thenReturn(record);
        when(portalDao.incrementTeamCurrentNum(77L)).thenReturn(1);

        SmsGroupBuyTeam team = ongoingTeam();
        team.setCurrentNum(3); // 已满员
        team.setTargetNum(3);
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);

        SmsGroupBuyRecord r1 = new SmsGroupBuyRecord();
        r1.setQuantity(1); r1.setJoinStatus(2);
        SmsGroupBuyRecord r2 = new SmsGroupBuyRecord();
        r2.setQuantity(2); r2.setJoinStatus(2);
        when(portalDao.listRecordsByTeam(77L)).thenReturn(Arrays.asList(r1, r2));
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);

        service.handlePaySuccess("SN");

        verify(portalDao).finalizeRecords(77L);
        verify(portalDao).finalizeStock(50L, 3);
        verify(portalDao).incrementSuccessGroupCount(1L);
    }

    @Test
    void handlePaySuccess_shouldWarnAndLogWhenTeamCannotAccept() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(1L);
        record.setTeamId(77L);
        record.setJoinStatus(0);
        when(portalDao.getRecordByOrderSn("SN")).thenReturn(record);
        when(portalDao.incrementTeamCurrentNum(77L)).thenReturn(0); // 团已满员/结束

        service.handlePaySuccess("SN");

        verify(teamMapper, never()).selectByPrimaryKey(anyLong());
        verify(groupBuyLogService).record(eq(GroupBuyLogService.OP_PAY_SUCCESS),
                eq(GroupBuyLogService.SRC_PAY_CALLBACK), any(), any(), any(), any(), anyString());
    }

    // ================== handleTeamTimeout ==================

    @Test
    void handleTeamTimeout_shouldIgnoreAlreadyFinishedTeam() {
        SmsGroupBuyTeam team = ongoingTeam();
        team.setStatus(1);
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);

        service.handleTeamTimeout(77L);

        verify(teamMapper, never()).updateByPrimaryKeySelective(any());
        verify(portalDao, never()).failRecords(anyLong());
    }

    @Test
    void handleTeamTimeout_shouldFinalizeSuccessWhenExactlyFull() {
        SmsGroupBuyTeam team = ongoingTeam();
        team.setCurrentNum(3);
        team.setTargetNum(3);
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);
        when(portalDao.listRecordsByTeam(77L)).thenReturn(Collections.emptyList());

        service.handleTeamTimeout(77L);

        verify(portalDao).finalizeRecords(77L);
        verify(portalDao).incrementSuccessGroupCount(1L);
    }

    @Test
    void handleTeamTimeout_shouldMarkFailAndReleaseStock() {
        SmsGroupBuyTeam team = ongoingTeam();
        team.setCurrentNum(1); // 未满员
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);

        SmsGroupBuyRecord r1 = new SmsGroupBuyRecord();
        r1.setQuantity(1); r1.setJoinStatus(1);
        when(portalDao.listRecordsByTeam(77L)).thenReturn(Collections.singletonList(r1));
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);

        service.handleTeamTimeout(77L);

        verify(portalDao).failRecords(77L);
        verify(portalDao).releaseStock(50L, 1);
        verify(groupBuyLogService).record(eq(GroupBuyLogService.OP_GROUP_FAIL),
                eq(GroupBuyLogService.SRC_SYSTEM), any(), any(), eq(0), eq(2), anyString());
    }

    // ================== cancelRecord ==================

    @Test
    void cancelRecord_shouldRejectWhenNotOwner() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(3L);
        record.setMemberId(999L);
        record.setJoinStatus(0);
        when(recordMapper.selectByPrimaryKey(3L)).thenReturn(record);
        assertThrows(ApiException.class, () -> service.cancelRecord(3L, 100L));
    }

    @Test
    void cancelRecord_shouldRejectWhenAlreadyPaid() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(3L);
        record.setMemberId(100L);
        record.setJoinStatus(1); // 已支付
        when(recordMapper.selectByPrimaryKey(3L)).thenReturn(record);
        assertThrows(ApiException.class, () -> service.cancelRecord(3L, 100L));
    }

    @Test
    void cancelRecord_shouldReleaseStockAndCloseTeamIfLeaderAlone() {
        SmsGroupBuyRecord record = new SmsGroupBuyRecord();
        record.setId(3L);
        record.setMemberId(100L);
        record.setTeamId(77L);
        record.setJoinStatus(0);
        record.setIsLeader(1);
        record.setQuantity(1);
        when(recordMapper.selectByPrimaryKey(3L)).thenReturn(record);
        when(recordMapper.updateByPrimaryKeySelective(any())).thenReturn(1);

        SmsGroupBuyTeam team = ongoingTeam();
        team.setCurrentNum(0); // 团长是唯一成员(尚未支付过)
        when(teamMapper.selectByPrimaryKey(77L)).thenReturn(team);
        when(portalDao.getProductBySku(1L, 20L)).thenReturn(product);

        int count = service.cancelRecord(3L, 100L);

        assertEquals(1, count);
        verify(portalDao).releaseStock(50L, 1);

        ArgumentCaptor<SmsGroupBuyTeam> teamCaptor = ArgumentCaptor.forClass(SmsGroupBuyTeam.class);
        verify(teamMapper).updateByPrimaryKeySelective(teamCaptor.capture());
        assertEquals(3, teamCaptor.getValue().getStatus()); // 已关闭
    }
}
