package com.macro.mall.portal.service;

import com.macro.mall.portal.domain.GroupBuyJoinParam;
import com.macro.mall.portal.domain.GroupBuyOpenParam;
import com.macro.mall.portal.domain.GroupBuyOrderResult;
import com.macro.mall.portal.domain.GroupBuyTeamDetail;

import java.util.List;

import com.macro.mall.model.SmsGroupBuyRecord;

/**
 * 拼团独立下单Service
 * 与普通 OmsPortalOrderService 平行,不侵入正常下单链路
 */
public interface GroupBuyOrderService {

    /**
     * 开团下单
     */
    GroupBuyOrderResult launchGroup(GroupBuyOpenParam param);

    /**
     * 参团下单
     */
    GroupBuyOrderResult joinGroup(GroupBuyJoinParam param);

    /**
     * 支付回调:由支付网关回调触发,更新参团与团状态
     */
    void handlePaySuccess(String orderSn);

    /**
     * 超时处理:MQ延时到期后执行,未满员则失败退款
     */
    void handleTeamTimeout(Long teamId);

    /**
     * 取消未支付参团
     */
    int cancelRecord(Long recordId, Long memberId);

    /**
     * 团详情(含参团成员)
     */
    GroupBuyTeamDetail getTeamDetail(String teamNo);

    /**
     * 我的参团记录
     */
    List<SmsGroupBuyRecord> listMyRecords(Long memberId);
}
