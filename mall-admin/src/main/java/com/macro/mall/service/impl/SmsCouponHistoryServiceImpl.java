package com.macro.mall.service.impl;


import com.macro.mall.common.util.SpecificationBuilder;
import cn.hutool.core.util.StrUtil;
import com.macro.mall.repository.SmsCouponHistoryRepository;
import com.macro.mall.model.SmsCouponHistory;
import com.macro.mall.service.SmsCouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 优惠券领取记录管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsCouponHistoryServiceImpl implements SmsCouponHistoryService {
    @Autowired
    private SmsCouponHistoryRepository historyRepository;
    @Override
    public List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum) {
        SpecificationBuilder<SmsCouponHistory> builder = SpecificationBuilder.create();
        if(couponId!=null){
            builder.eq("couponId", couponId);
        }
        if(useStatus!=null){
            builder.eq("useStatus", useStatus);
        }
        if(!StrUtil.isEmpty(orderSn)){
            builder.eq("orderSn", orderSn);
        }
        return historyRepository.findAll(builder.build());
    }
}
