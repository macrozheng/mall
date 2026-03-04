package com.macro.mall.security.component;

import cn.hutool.http.Method;
import com.macro.mall.security.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class YxDynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private YxDynamincSecurityMetadataSource yxDynamincSecurityMetadataSource;
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    public void setMyAccessDecisionManager(AccessDecisionManager dynamicAccessDecisionManager) {
        super.setAccessDecisionManager(dynamicAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest,servletResponse,filterChain);
        if (fi.getRequest().getMethod().equals(HttpMethod.OPTIONS.toString())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (String pattern : ignoreUrlsConfig.getUrls()){
            if (antPathMatcher.match(pattern,((HttpServletRequest)servletRequest).getRequestURI())){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return yxDynamincSecurityMetadataSource;
    }
}
