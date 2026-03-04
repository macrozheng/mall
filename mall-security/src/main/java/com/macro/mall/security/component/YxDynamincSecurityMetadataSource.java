package com.macro.mall.security.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;


public class YxDynamincSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static Map<String ,ConfigAttribute> configAttributeMap = null;

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (configAttributeMap == null) loadDataSource();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<ConfigAttribute> mylist = new ArrayList<ConfigAttribute>();
        for (String i : configAttributeMap.keySet()) {
            if (pathMatcher.match(i,requestUrl)){
                mylist.add(configAttributeMap.get(i));
            }
        }
        return mylist;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
