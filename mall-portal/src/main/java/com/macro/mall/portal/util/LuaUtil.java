package com.macro.mall.portal.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * lua脚本工具类
 * */
public class LuaUtil {
    public  static <T> DefaultRedisScript<T> getLuaScript(String path, Class<T> resultType) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            String scriptText = StreamUtils.copyToString(resource.getStream(), StandardCharsets.UTF_8);
            DefaultRedisScript<T> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(scriptText);
            redisScript.setResultType(resultType);
            return redisScript;
        } catch (IOException e) {
            throw new RuntimeException("加载Lua脚本失败" + path, e);
        }
    }
}
