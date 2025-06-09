package com.macro.mall.portal.service.impl;

import com.macro.mall.common.service.StringRedisService;
import com.macro.mall.portal.service.UmsMemberCouponCacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UmsMemberCouponServiceCacheImpl implements UmsMemberCouponCacheService {
    private final StringRedisService stringRedisService;
    @Value("${redis.database}:")
    private String REDIS_DATABASE;
    @Value("${redis.key.coupon}")
    private String COUPON_PREFIX;

    public UmsMemberCouponServiceCacheImpl(StringRedisService stringRedisService) {
        this.stringRedisService = stringRedisService;
    }

    /**
     * 查询所有redis中key的数据
     * */
    @Override
    public HashMap<String, String> getAll(Long id) {
        String key = REDIS_DATABASE + COUPON_PREFIX + id;
        Map<Object, Object> rawMap = stringRedisService.getAll(key);
        HashMap<String, String> result = new HashMap<>();
        rawMap.forEach((k, v) -> result.put(
                String.valueOf(k),
                String.valueOf(v)
        ));
        return result;
    }

    /**
     * 查询特定键的值
     * */
    @Override
    public Object get(Long id, String hashKey) {
        String key = REDIS_DATABASE + COUPON_PREFIX + id;
        return stringRedisService.get(key, hashKey);
    }

    /**
     * 执行lua脚本
     * */
    @Override
    public long luaExecute(DefaultRedisScript<Long> luaScript, List<String> keyLists, Object... argv) {
        Long result = stringRedisService.execute(luaScript, keyLists, argv);
        return result != null ? result : 0;
    }
}
