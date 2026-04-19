package com.macro.mall.service;

import com.macro.mall.model.SmsGroupBuyTeam;

import java.util.List;

/**
 * 拼团团管理Service (后台)
 */
public interface SmsGroupBuyTeamService {
    List<SmsGroupBuyTeam> list(Long activityId, Integer status, String teamNo,
                               Integer pageSize, Integer pageNum);

    SmsGroupBuyTeam getItem(Long id);

    /**
     * 后台强制关闭团(应急处理,触发退款)
     */
    int forceClose(Long id);
}
