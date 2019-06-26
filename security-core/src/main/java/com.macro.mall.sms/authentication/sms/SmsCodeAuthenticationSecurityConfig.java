package com.macro.mall.sms.authentication.sms;

import com.macro.mall.sms.validate.GoAuthenticationFailureHandler;
import com.macro.mall.sms.validate.GoAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/24 15:31
 * @Description:    sms短信登录配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//    @Autowired
    private GoAuthenticationSuccessHandler myAuthenticationSuccessHandler;

//    @Autowired
    private GoAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        myAuthenticationSuccessHandler = new GoAuthenticationSuccessHandler();
        myAuthenticationFailureHandler = new GoAuthenticationFailureHandler();

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

        // 获取验证码提供者
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
