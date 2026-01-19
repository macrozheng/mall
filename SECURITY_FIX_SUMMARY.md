# Issue #884 修复总结

## 问题描述
**Issue #884: 动态鉴权管理器路径匹配优化**

当应用配置了 `server.servlet.context-path` 时，动态权限管理器的路径匹配会失败，导致无法正确查询到所需的权限。

## 问题原因
在 `DynamicSecurityMetadataSource.java` 和 `DynamicSecurityFilter.java` 中，路径匹配逻辑没有正确处理 context-path 前缀，导致：
- 白名单路径匹配失败
- 权限资源路径匹配失败

## 修复方案

### 1. 修复 DynamicSecurityMetadataSource.java
**文件位置**: `mall-security/src/main/java/com/macro/mall/security/component/DynamicSecurityMetadataSource.java`

**修改内容**:
```java
// 获取当前访问的路径
FilterInvocation filterInvocation = (FilterInvocation) o;
String url = filterInvocation.getRequestUrl();
String path = URLUtil.getPath(url);

// 移除 context-path 前缀，确保路径匹配正确
String contextPath = filterInvocation.getHttpRequest().getContextPath();
if (contextPath != null && !contextPath.isEmpty() && path.startsWith(contextPath)) {
    path = path.substring(contextPath.length());
}
```

### 2. 修复 DynamicSecurityFilter.java
**文件位置**: `mall-security/src/main/java/com/macro/mall/security/component/DynamicSecurityFilter.java`

**修改内容**:
```java
//白名单请求直接放行
PathMatcher pathMatcher = new AntPathMatcher();
String requestPath = request.getRequestURI();

// 移除 context-path 前缀，确保路径匹配正确
String contextPath = request.getContextPath();
if (contextPath != null && !contextPath.isEmpty() && requestPath.startsWith(contextPath)) {
    requestPath = requestPath.substring(contextPath.length());
}

for (String path : ignoreUrlsConfig.getUrls()) {
    if(pathMatcher.match(path, requestPath)){
        fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        return;
    }
}
```

### 3. 修复编译问题
由于 Lombok 注解处理器没有正确工作，手动添加了必要的 getter/setter 方法：

- **SwaggerProperties.java**: 添加了所有属性的 getter/setter 方法
- **WebLog.java**: 添加了所有属性的 getter/setter 方法  
- **IgnoreUrlsConfig.java**: 添加了 `getUrls()` 和 `setUrls()` 方法

## 测试验证

创建了 `PathMatchingTest.java` 测试类，包含以下测试用例：

1. **testPathMatchingWithoutContextPath**: 测试没有 context-path 的情况
2. **testPathMatchingWithContextPath**: 测试有 context-path 的情况
3. **testPathMatchingWithEmptyContextPath**: 测试空 context-path 的情况
4. **testPathMatchingWithNullContextPath**: 测试 null context-path 的情况
5. **testPathMatchingWithDifferentContextPath**: 测试不同的 context-path 情况

**测试结果**: ✅ 所有 5 个测试用例全部通过

## 修复效果

修复后的代码能够正确处理以下场景：

1. **无 context-path**: `/admin/users` → 正常匹配 `/admin/**`
2. **有 context-path**: `/mall/admin/users` → 移除 `/mall` 前缀后匹配 `/admin/**`
3. **空 context-path**: 不会进行任何修改
4. **null context-path**: 不会进行任何修改
5. **不匹配的 context-path**: 不会进行修改，保持原路径

## 影响范围

- ✅ **向后兼容**: 不影响没有配置 context-path 的应用
- ✅ **功能完整**: 修复了 context-path 配置下的路径匹配问题
- ✅ **性能影响**: 最小，只是简单的字符串操作
- ✅ **安全性**: 不影响现有的权限控制逻辑

## 相关文件

- `mall-security/src/main/java/com/macro/mall/security/component/DynamicSecurityMetadataSource.java`
- `mall-security/src/main/java/com/macro/mall/security/component/DynamicSecurityFilter.java`
- `mall-security/src/test/java/com/macro/mall/security/component/PathMatchingTest.java`
- `mall-common/src/main/java/com/macro/mall/common/domain/SwaggerProperties.java`
- `mall-common/src/main/java/com/macro/mall/common/domain/WebLog.java`
- `mall-security/src/main/java/com/macro/mall/security/config/IgnoreUrlsConfig.java`

## 总结

这个修复解决了 mall 项目在配置 `server.servlet.context-path` 时动态权限管理器的路径匹配问题，确保了权限控制功能的正常工作。修复方案简单、安全、向后兼容，并且通过了完整的测试验证。