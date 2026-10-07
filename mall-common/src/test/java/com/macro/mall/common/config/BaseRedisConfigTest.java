package com.macro.mall.common.config;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for the Redis serializer configured in {@link BaseRedisConfig}: polymorphic
 * deserialization must stay restricted to the application's own model package and
 * common JDK collection types, and must reject cache values declaring any other class.
 */
class BaseRedisConfigTest {

    private RedisSerializer<Object> serializer;

    /**
     * A non-final type covered by the com.macro.mall allowlist, mimicking the cached
     * model objects such as UmsAdmin and UmsMember.
     */
    public static class CachedUser {
        private Long id;
        private String username;

        public CachedUser() {
        }

        public CachedUser(final Long id, final String username) {
            this.id = id;
            this.username = username;
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }
    }

    @BeforeEach
    void setup() {
        serializer = new BaseRedisConfig().redisSerializer();
    }

    @Test
    void shouldRoundTripAllowListedModel() {
        CachedUser user = new CachedUser(1L, "macro");
        byte[] bytes = serializer.serialize(user);
        CachedUser restored = (CachedUser) serializer.deserialize(bytes);
        assertEquals(user.getId(), restored.getId());
        assertEquals(user.getUsername(), restored.getUsername());
    }

    @Test
    void shouldRoundTripAllowListedCollection() {
        List<CachedUser> users = Arrays.asList(new CachedUser(1L, "macro"), new CachedUser(2L, "admin"));
        byte[] bytes = serializer.serialize(users);
        List<?> restored = (List<?>) serializer.deserialize(bytes);
        assertEquals(2, restored.size());
        assertEquals("macro", ((CachedUser) restored.get(0)).getUsername());
        assertEquals("admin", ((CachedUser) restored.get(1)).getUsername());
    }

    @Test
    void shouldRejectTypeOutsideAllowList() {
        byte[] malicious = "{\"@class\":\"java.io.File\"}".getBytes();
        assertThrows(RuntimeException.class, () -> serializer.deserialize(malicious));
    }

    @Test
    void shouldRejectNamingContextFactory() {
        byte[] malicious = "{\"@class\":\"javax.naming.InitialContext\"}".getBytes();
        assertThrows(RuntimeException.class, () -> serializer.deserialize(malicious));
    }

    @Test
    void shouldRejectUnknownSubTypeWithInvalidTypeIdException() {
        byte[] malicious = "[\"org.example.NotOnClasspath\",{}]".getBytes();
        SerializationException ex = assertThrows(SerializationException.class, () -> serializer.deserialize(malicious));
        assertEquals(InvalidTypeIdException.class, ex.getCause().getClass());
    }
}
