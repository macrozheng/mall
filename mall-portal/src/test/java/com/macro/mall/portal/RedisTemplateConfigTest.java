package com.macro.mall.portal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTemplateConfigTest {

    // 注入容器中的RedisTemplate实例
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void printRedisTemplateConfig() {
        if (redisTemplate == null) {
            System.out.println("【RedisTemplate】容器中未创建RedisTemplate实例");
            return;
        }

        // 1. 打印核心序列化配置（最常用）
        System.out.println("===== RedisTemplate 序列化配置 =====");
        RedisSerializer<?> keySerializer = redisTemplate.getKeySerializer();
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        RedisSerializer<?> hashKeySerializer = redisTemplate.getHashKeySerializer();
        RedisSerializer<?> hashValueSerializer = redisTemplate.getHashValueSerializer();

        System.out.println("key序列化器：" + (keySerializer == null ? "默认(JdkSerializationRedisSerializer)" : keySerializer.getClass().getName()));
        System.out.println("value序列化器：" + (valueSerializer == null ? "默认(JdkSerializationRedisSerializer)" : valueSerializer.getClass().getName()));
        System.out.println("hashKey序列化器：" + (hashKeySerializer == null ? "默认(JdkSerializationRedisSerializer)" : hashKeySerializer.getClass().getName()));
        System.out.println("hashValue序列化器：" + (hashValueSerializer == null ? "默认(JdkSerializationRedisSerializer)" : hashValueSerializer.getClass().getName()));

        // 2. 打印Redis连接信息（核心）
        System.out.println("\n===== Redis 连接配置 =====");
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        if (connectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory lettuceFactory = (LettuceConnectionFactory) connectionFactory;
            RedisStandaloneConfiguration config = (RedisStandaloneConfiguration) lettuceFactory.getStandaloneConfiguration();

            System.out.println("Redis主机：" + config.getHostName());
            System.out.println("Redis端口：" + config.getPort());
            System.out.println("Redis数据库：" + config.getDatabase());
            System.out.println("Redis用户名：" + (config.getUsername() == null ? "无" : config.getUsername()));
            System.out.println("Redis密码：" + (config.getPassword() == null ? "无" : config.getPassword().get()));
        } else {
            System.out.println("连接工厂类型：" + connectionFactory.getClass().getName());
        }
    }
}