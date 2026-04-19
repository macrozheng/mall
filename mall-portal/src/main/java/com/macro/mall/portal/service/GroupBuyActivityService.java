package com.macro.mall.portal.service;

import com.macro.mall.model.SmsGroupBuyActivity;
import com.macro.mall.model.SmsGroupBuyProduct;
import com.macro.mall.model.SmsGroupBuyTeam;

import java.util.List;

/**
 * 拼团活动前台Service
 */
public interface GroupBuyActivityService {
    /**
     * 进行中的活动列表(首页/活动专区)
     */
    List<SmsGroupBuyActivity> listOngoing();

    /**
     * 活动详情(含商品列表)
     */
    SmsGroupBuyActivity getDetail(Long activityId);

    /**
     * 活动下的商品列表
     */
    List<SmsGroupBuyProduct> listProduct(Long activityId);

    /**
     * 活动下进行中的可参与团
     */
    List<SmsGroupBuyTeam> listOngoingTeam(Long activityId);
}
