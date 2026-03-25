package com.macro.mall.demo.config;

import com.macro.mall.demo.bo.AdminUserDetails;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsAdminExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * SpringSecurity相关配置
 * Created by macro on 2018/4/26.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll())
                .httpBasic(basic -> basic
                        .realmName("/"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true"))
                .logout(logout -> logout
                        .logoutSuccessUrl("/"))
                .csrf(csrf -> csrf.disable());
        return httpSecurity.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdminExample example = new UmsAdminExample();
            example.createCriteria().andUsernameEqualTo(username);
            List<UmsAdmin> adminList = umsAdminMapper.selectByExample(example);
            if (adminList != null && adminList.size() > 0) {
                return new AdminUserDetails(adminList.get(0));
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
