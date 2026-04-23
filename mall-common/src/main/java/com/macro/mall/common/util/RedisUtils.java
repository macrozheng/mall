package com.macro.mall.portal.service.impl;

import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
// 导入这个
import io.lettuce.core.api.async.RedisAsyncCommands; // 关键导入
import io.lettuce.core.cluster.api.async.RedisClusterAsyncCommands; // 关键导入

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取 ZSet 列表（仅数据，不带分数）
     */
    public <T> List<T> zRange(String key, long start, long end, Class<T> clazz) {
        Set<Object> rawValues = redisTemplate.opsForZSet().range(key, start, end);
        if (CollectionUtils.isEmpty(rawValues)) {
            return Collections.emptyList();
        }
        // 使用 Stream 进行安全转换
        return rawValues.stream()
                .map(clazz::cast)
                .collect(Collectors.toList()); // 保持 ZSet 的顺序
    }




    public List<Boolean> bfMExists(String key, List<String> items) {
        if (key == null || items == null || items.isEmpty()) {
            return Collections.emptyList();
        }

        return redisTemplate.execute((RedisCallback<List<Boolean>>) connection -> {
            Object nativeConnection = connection.getNativeConnection();

            // 1. 定义命令
            ProtocolKeyword type = new ProtocolKeyword() {
                @Override public byte[] getBytes() { return "BF.MEXISTS".getBytes(StandardCharsets.UTF_8); }
                @Override public String name() { return "BF.MEXISTS"; }
            };

            // 2. 准备参数
            CommandArgs<byte[], byte[]> args = new CommandArgs<>(ByteArrayCodec.INSTANCE);
            args.add(key.getBytes(StandardCharsets.UTF_8));
            for (String item : items) {
                args.add(item.getBytes(StandardCharsets.UTF_8));
            }

            // 3. 关键修复：使用 BooleanListOutput，完美匹配 RESP3 的 Boolean 返回值
            FixedBooleanListOutput<byte[], byte[]> output = new FixedBooleanListOutput<>(ByteArrayCodec.INSTANCE);

            // 4. 发送命令
            try {
                if (nativeConnection instanceof RedisAsyncCommands) {
                    RedisAsyncCommands<byte[], byte[]> async = (RedisAsyncCommands<byte[], byte[]>) nativeConnection;
                    async.dispatch(type, output, args).get(5, TimeUnit.SECONDS);
                } else if (nativeConnection instanceof RedisClusterAsyncCommands) {
                    RedisClusterAsyncCommands<byte[], byte[]> async = (RedisClusterAsyncCommands<byte[], byte[]>) nativeConnection;
                    async.dispatch(type, output, args).get(5, TimeUnit.SECONDS);
                } else {
                    throw new IllegalStateException("Unsupported connection type: " + nativeConnection.getClass());
                }

                // 5. 直接获取 List<Boolean>，无需再做转换！
                List<Boolean> resultList = output.get();
                return resultList == null ? Collections.emptyList() : resultList;

            } catch (Exception e) {
                throw new RuntimeException("RedisBloom command execution failed: " + e.getMessage(), e);
            }
        });
    }

    public void bfReserve(String key,Double errorRate,Integer capacity){
        redisTemplate.execute((RedisCallback)connection -> {
            connection.execute("BF.RESERVE",key.getBytes(),errorRate.toString().getBytes(),capacity.toString().getBytes());
            return null;
        });
    }

    public List<Boolean> bfMadd(String key, List<String> items) {
        if (key == null || items == null || items.isEmpty()) {
            return Collections.emptyList();
        }

        return redisTemplate.execute((RedisCallback<List<Boolean>>) connection -> {
            Object nativeConnection = connection.getNativeConnection();

            // 1. 定义命令
            ProtocolKeyword type = new ProtocolKeyword() {
                @Override public byte[] getBytes() { return "BF.MADD".getBytes(StandardCharsets.UTF_8); }
                @Override public String name() { return "BF.MADD"; }
            };

            // 2. 准备参数
            CommandArgs<byte[], byte[]> args = new CommandArgs<>(ByteArrayCodec.INSTANCE);
            args.add(key.getBytes(StandardCharsets.UTF_8));
            for (String item : items) {
                args.add(item.getBytes(StandardCharsets.UTF_8));
            }

            // 3. 关键修复：使用 BooleanListOutput，完美匹配 RESP3 的 Boolean 返回值
            FixedBooleanListOutput<byte[], byte[]> output = new FixedBooleanListOutput<>(ByteArrayCodec.INSTANCE);

            // 4. 发送命令
            try {
                if (nativeConnection instanceof RedisAsyncCommands) {
                    RedisAsyncCommands<byte[], byte[]> async = (RedisAsyncCommands<byte[], byte[]>) nativeConnection;
                    async.dispatch(type, output, args).get(5, TimeUnit.SECONDS);
                } else if (nativeConnection instanceof RedisClusterAsyncCommands) {
                    RedisClusterAsyncCommands<byte[], byte[]> async = (RedisClusterAsyncCommands<byte[], byte[]>) nativeConnection;
                    async.dispatch(type, output, args).get(5, TimeUnit.SECONDS);
                } else {
                    throw new IllegalStateException("Unsupported connection type: " + nativeConnection.getClass());
                }

                // 5. 直接获取 List<Boolean>，无需再做转换！
                List<Boolean> resultList = output.get();
                return resultList == null ? Collections.emptyList() : resultList;

            } catch (Exception e) {
                throw new RuntimeException("RedisBloom command execution failed: " + e.getMessage(), e);
            }
        });
    }
}