package com.macro.mall.service;

import com.macro.mall.dto.SmsFlashPromotionProduct;
import com.macro.mall.model.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 限时购商品关联管理Service
 * Created by macro on 2018/11/16.
 */
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     */
    @Transactional
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * 修改关联相关信息
     */
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     */
    int delete(Long id);

    /**
     * 获取关联详情
     */
    SmsFlashPromotionProductRelation getItem(Long id);

    /**
     * 分页查询相关商品及促销信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     */
    List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据活动和场次id获取商品关系数量
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    long getCount(Long flashPromotionId,Long flashPromotionSessionId);
}
