package com.macro.mall.portal.service;

import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.HashMap;
import java.util.List;

public interface UmsMemberCouponCacheService {
    /**
     * 获取优惠卷信息
     * */
    HashMap<String, String> getAll(Long id);

    /**
     * 获取特定hashkey的优惠卷信息
     * */
    Object get(Long id, String hashKey);

    /**
     * 执行lua脚本
     * */
    long luaExecute(DefaultRedisScript<Long> redisScript, List<String> keyLists, Object... argv);
}
