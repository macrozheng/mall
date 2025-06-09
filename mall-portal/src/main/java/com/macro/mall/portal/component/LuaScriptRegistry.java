package com.macro.mall.portal.component;

import com.macro.mall.portal.util.LuaUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LuaScriptRegistry {
    private final Map<String, DefaultRedisScript<?>> scriptMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        scriptMap.put("couponSale", LuaUtil.getLuaScript("lua/couponSale.lua", Long.class));
    }

    @SuppressWarnings("unchecked")
    public <T> DefaultRedisScript<T> get(String name) {
        return (DefaultRedisScript<T>) scriptMap.get(name);
    }
}
