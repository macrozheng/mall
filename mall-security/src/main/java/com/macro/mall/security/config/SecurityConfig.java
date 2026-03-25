package com.macro.mall.security.config;

import com.macro.mall.security.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * SpringSecurity相关配置，仅用于配置SecurityFilterChain
 * Created by macro on 2019/11/5.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;
    @Autowired(required = false)
    private DynamicSecurityFilter dynamicSecurityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //关闭跨站请求防护
                .csrf(csrf -> csrf.disable())
                //不使用session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //自定义权限拒绝处理类
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(restfulAccessDeniedHandler)
                        .authenticationEntryPoint(restAuthenticationEntryPoint))
                //自定义权限拦截器JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig.getUrls()) {
            httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers(url).permitAll());
        }
        //允许跨域请求的OPTIONS请求
        httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers(HttpMethod.OPTIONS).permitAll());
        //任何请求都需要身份认证
        httpSecurity.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
        
        //有动态权限配置时添加动态权限校验过滤器
        if(dynamicSecurityService!=null){
            httpSecurity.addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
        }
        
        return httpSecurity.build();
    }

}
