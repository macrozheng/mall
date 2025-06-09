package com.macro.mall.common.service.impl;

import com.macro.mall.common.service.StringRedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StringRedisService实现类
 * Created by kaomita on 2025年6月5日
 * */

@Service
public class StringRedisServiceImpl implements StringRedisService {
    private final StringRedisTemplate stringRedisTemplate;

    public StringRedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public void hSet(String key, HashMap<String, String> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public Object hGet(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public void hDel(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public Map<Object, Object> getAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    @Override
    public Object get(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Long execute(DefaultRedisScript<Long> redisScript, List<String> keyLists, Object... argv) {
        return stringRedisTemplate.execute(redisScript, keyLists, argv);
    }

}
