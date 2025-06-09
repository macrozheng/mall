package com.macro.mall.service.impl;

import com.macro.mall.common.service.StringRedisService;
import com.macro.mall.dto.SmsCouponParam;
import com.macro.mall.service.SmsCouponCacheService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SmsCouponCacheServiceImpl implements SmsCouponCacheService {
    private final StringRedisService stringRedisService;
    @Value("${redis.database}:")
    private String REDIS_DATABASE;
    @Value("${redis.key.coupon}")
    private String COUPON_PREFIX;

    public SmsCouponCacheServiceImpl(StringRedisService stringRedisService) {
        this.stringRedisService = stringRedisService;
    }

    /**
     * 新增优惠卷时加入到redis中
     * */
    @Override
    public void create(SmsCouponParam couponParam) {
        String key = REDIS_DATABASE + COUPON_PREFIX + couponParam.getId();
        HashMap<String, String> couponMap = getStringStringHashMap(couponParam, couponParam.getCount());
        stringRedisService.hSet(key, couponMap);
    }

    /**
     * 更新redis
     * */
    @Override
    public void update(SmsCouponParam couponParam) {
        String key = REDIS_DATABASE + COUPON_PREFIX + couponParam.getId();
        int originalPublicCount = Optional.ofNullable(stringRedisService.hGet(key, "publishCount"))
            .map(Object::toString)
            .map(Integer::parseInt)
            .orElse(couponParam.getPublishCount());
        int originCount = Optional.ofNullable(stringRedisService.hGet(key, "count"))
                .map(Object::toString)
                .map(Integer::parseInt)
                .orElse(originalPublicCount);
        int count = couponParam.getPublishCount() - originalPublicCount + originCount;
        HashMap<String, String> couponMap = getStringStringHashMap(couponParam, count);
        stringRedisService.hSet(key, couponMap);
    }

    /**
     * 删除redis
     * */
    @Override
    public void delete(Long id) {
        String key = REDIS_DATABASE + COUPON_PREFIX + id;
        stringRedisService.hDel(key);
    }

    /**
     * 将优惠卷数据放入HashMap中
     * */
    @NotNull
    private static HashMap<String, String> getStringStringHashMap(SmsCouponParam couponParam, int count) {
        return new HashMap<>(){{
            put("count", String.valueOf(count));
            put("perLimit", String.valueOf((couponParam.getPerLimit())));
            put("startTime", String.valueOf(couponParam.getStartTime()));
            put("endTime", String.valueOf(couponParam.getEndTime()));
            put("enableTime", String.valueOf(couponParam.getEnableTime()));
            put("amount", String.valueOf(couponParam.getAmount()));
            put("publishCount", String.valueOf(couponParam.getPublishCount()));
        }};
    }
}
