package com.macro.mall.portal.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.model.PmsProduct;

/**
 * 个性化商品推荐服务
 * Created by macro on 2024/5/20.
 */
public interface PersonalizedRecommendationService {
    /**
     * 根据用户浏览历史和消费习惯获取个性化推荐商品
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 推荐商品列表
     */
    CommonPage<PmsProduct> getPersonalizedRecommendations(Integer pageNum, Integer pageSize);
}