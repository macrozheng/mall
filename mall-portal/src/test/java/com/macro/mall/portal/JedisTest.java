package com.macro.mall.portal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;


@SpringBootTest
public class JedisTest {
    @Test
    public void testJedis() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("key", "value");
        System.out.println(jedis.get("key"));
    }
}
