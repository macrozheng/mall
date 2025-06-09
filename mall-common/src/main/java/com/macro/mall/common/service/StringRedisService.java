package com.macro.mall.common.service;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StringRedisTemplate操作Service类
 * Created by kaomita on 2025年6月4日
 * */
public interface StringRedisService {
    /**
     * 添加hash属性
     * */
    void hSet(String key, HashMap<String, String> map);

    Object hGet(String key, String hashKey);

    /**
     * 删除优惠卷
     * */
    void hDel(String key);

    /**
     * 获取所有优惠卷信息
     * */
    Map<Object, Object> getAll(String key);

    /**
     * 查询优惠卷数据
     * */
    Object get(String key, String hashKey);

    Long execute(DefaultRedisScript<Long> redisScript, List<String> keyLists, Object... argv);
}
