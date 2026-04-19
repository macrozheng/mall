package com.macro.mall.service;

import com.macro.mall.dto.SmsGroupBuyProductParam;
import com.macro.mall.model.SmsGroupBuyProduct;

import java.util.List;

/**
 * 拼团活动商品Service
 */
public interface SmsGroupBuyProductService {
    int createBatch(SmsGroupBuyProductParam param);

    int update(Long id, SmsGroupBuyProduct product);

    int delete(Long id);

    List<SmsGroupBuyProduct> listByActivity(Long activityId);
}
