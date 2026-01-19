package com.macro.mall.security.component;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 路径匹配测试类
 * 用于验证 context-path 处理是否正确
 */
public class PathMatchingTest {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Test
    public void testPathMatchingWithoutContextPath() {
        // 测试没有 context-path 的情况
        String pattern = "/admin/**";
        String requestPath = "/admin/users";
        
        assertTrue(pathMatcher.match(pattern, requestPath));
    }

    @Test
    public void testPathMatchingWithContextPath() {
        // 测试有 context-path 的情况
        String pattern = "/admin/**";
        String fullPath = "/mall/admin/users";
        String contextPath = "/mall";
        
        // 移除 context-path 前缀
        String requestPath = fullPath;
        if (contextPath != null && !contextPath.isEmpty() && requestPath.startsWith(contextPath)) {
            requestPath = requestPath.substring(contextPath.length());
        }
        
        assertEquals("/admin/users", requestPath);
        assertTrue(pathMatcher.match(pattern, requestPath));
    }

    @Test
    public void testPathMatchingWithEmptyContextPath() {
        // 测试空 context-path 的情况
        String pattern = "/admin/**";
        String requestPath = "/admin/users";
        String contextPath = "";
        
        // 移除 context-path 前缀
        if (contextPath != null && !contextPath.isEmpty() && requestPath.startsWith(contextPath)) {
            requestPath = requestPath.substring(contextPath.length());
        }
        
        assertEquals("/admin/users", requestPath);
        assertTrue(pathMatcher.match(pattern, requestPath));
    }

    @Test
    public void testPathMatchingWithNullContextPath() {
        // 测试 null context-path 的情况
        String pattern = "/admin/**";
        String requestPath = "/admin/users";
        String contextPath = null;
        
        // 移除 context-path 前缀
        if (contextPath != null && !contextPath.isEmpty() && requestPath.startsWith(contextPath)) {
            requestPath = requestPath.substring(contextPath.length());
        }
        
        assertEquals("/admin/users", requestPath);
        assertTrue(pathMatcher.match(pattern, requestPath));
    }

    @Test
    public void testPathMatchingWithDifferentContextPath() {
        // 测试不同的 context-path
        String pattern = "/api/**";
        String fullPath = "/api/v1/users";
        String contextPath = "/app";
        
        // 移除 context-path 前缀
        String requestPath = fullPath;
        if (contextPath != null && !contextPath.isEmpty() && requestPath.startsWith(contextPath)) {
            requestPath = requestPath.substring(contextPath.length());
        }
        
        // 由于 context-path 不匹配，路径不应该被修改
        assertEquals("/api/v1/users", requestPath);
        assertTrue(pathMatcher.match(pattern, requestPath));
    }
}