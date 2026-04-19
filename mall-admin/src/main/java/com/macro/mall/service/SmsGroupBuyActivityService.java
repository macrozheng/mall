package com.macro.mall.service;

import com.macro.mall.model.SmsGroupBuyActivity;

import java.util.List;

/**
 * 拼团活动管理Service
 */
public interface SmsGroupBuyActivityService {
    int create(SmsGroupBuyActivity activity);

    int update(Long id, SmsGroupBuyActivity activity);

    int delete(Long id);

    int updateStatus(Long id, Integer status);

    SmsGroupBuyActivity getItem(Long id);

    List<SmsGroupBuyActivity> list(String keyword, Integer status, Integer pageSize, Integer pageNum);
}
