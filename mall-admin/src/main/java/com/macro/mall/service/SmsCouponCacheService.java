package com.macro.mall.service;

import com.macro.mall.dto.SmsCouponParam;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.HashMap;
import java.util.List;

public interface SmsCouponCacheService {
    /**
     * 新增优惠卷
     * */
    void create(SmsCouponParam couponParam);

    /**
     * 更新优惠卷
     * */
    void update(SmsCouponParam couponParam);

    /**
     * 删除优惠卷
     * */
    void delete(Long id);
}
