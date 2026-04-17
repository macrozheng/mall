package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.model.SmsGroupActivity;
import com.macro.mall.portal.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface SmsGroupBuyingService {

    List<SmsGroupActivity> listActivity(Integer pageSize, Integer pageNum);

    GroupActivityDetail getActivityDetail(Long activityId);

    GroupProductItem getProductDetail(Long groupProductId);

    List<GroupTeamDetail> listAvailableTeam(Long groupProductId, Integer pageSize, Integer pageNum);

    GroupTeamDetail getTeamDetail(Long teamId);

    @Transactional
    Map<String, Object> createGroupOrder(GroupOrderParam param);

    @Transactional
    void paySuccess(Long orderId);

    CommonPage<GroupTeamDetail> listMyGroup(Integer status, Integer pageSize, Integer pageNum);

    void cancelTimeOutTeam();
}
