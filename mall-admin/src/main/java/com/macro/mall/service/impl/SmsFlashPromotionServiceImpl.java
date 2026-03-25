package com.macro.mall.service.impl;

import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsFlashPromotionRepository;
import com.macro.mall.model.SmsFlashPromotion;
import com.macro.mall.service.SmsFlashPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 限时购活动管理Service实现类
 * Created by macro on 2018/11/16.
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Autowired
    private SmsFlashPromotionRepository flashPromotionRepository;

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        flashPromotionRepository.save(flashPromotion);
        return 1;
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        flashPromotionRepository.save(flashPromotion);
        return 1;
    }

    @Override
    public int delete(Long id) {
        flashPromotionRepository.deleteById(id);
        return 1;
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
        flashPromotionRepository.save(flashPromotion);
        return 1;
    }

    @Override
    public SmsFlashPromotion getItem(Long id) {
        return flashPromotionRepository.findById(id).orElse(null);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsFlashPromotion> builder = SpecificationBuilder.create();
        if (!StrUtil.isEmpty(keyword)) {
            builder.like("title", keyword);
        }
        return flashPromotionRepository.findAll(builder.build());
    }
}
