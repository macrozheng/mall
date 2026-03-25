package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import com.macro.mall.dto.SmsFlashPromotionProduct;
import com.macro.mall.repository.SmsFlashPromotionProductRelationRepository;
import com.macro.mall.model.SmsFlashPromotionProductRelation;
import com.macro.mall.service.SmsFlashPromotionProductRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 限时购商品关联管理Service实现类
 * Created by macro on 2018/11/16.
 */
@Service
public class SmsFlashPromotionProductRelationServiceImpl implements SmsFlashPromotionProductRelationService {
    @Autowired
    private SmsFlashPromotionProductRelationRepository relationRepository;

    @Override
    public int create(List<SmsFlashPromotionProductRelation> relationList) {
        for (SmsFlashPromotionProductRelation relation : relationList) {
            relationRepository.save(relation);
        }
        return relationList.size();
    }

    @Override
    public int update(Long id, SmsFlashPromotionProductRelation relation) {
        relation.setId(id);
        relationRepository.save(relation);
        return 1;
    }

    @Override
    public int delete(Long id) {
        relationRepository.deleteById(id);
        return 1;
    }

    @Override
    public SmsFlashPromotionProductRelation getItem(Long id) {
        return relationRepository.findById(id).orElse(null);
    }

    @Override
    public List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum) {
        // TODO: 实现复杂查询，暂时返回空列表
        SpecificationBuilder<SmsFlashPromotionProductRelation> builder = SpecificationBuilder.create();
        builder.eq("flashPromotionId", flashPromotionId)
               .eq("flashPromotionSessionId", flashPromotionSessionId);
        List<SmsFlashPromotionProductRelation> relations = relationRepository.findAll(builder.build());
        // 转换为 DTO
        return relations.stream().map(relation -> {
            SmsFlashPromotionProduct product = new SmsFlashPromotionProduct();
            // 复制属性
            product.setId(relation.getId());
            product.setFlashPromotionId(relation.getFlashPromotionId());
            product.setFlashPromotionSessionId(relation.getFlashPromotionSessionId());
            product.setProductId(relation.getProductId());
            product.setFlashPromotionPrice(relation.getFlashPromotionPrice());
            product.setFlashPromotionCount(relation.getFlashPromotionCount());
            product.setFlashPromotionLimit(relation.getFlashPromotionLimit());
            product.setSort(relation.getSort());
            return product;
        }).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public long getCount(Long flashPromotionId, Long flashPromotionSessionId) {
        SpecificationBuilder<SmsFlashPromotionProductRelation> builder = SpecificationBuilder.create();
        builder.eq("flashPromotionId", flashPromotionId)
               .eq("flashPromotionSessionId", flashPromotionSessionId);
        return relationRepository.count(builder.build());
    }
}
