package com.macro.mall.config;

import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * SpringSecurity的配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService adminService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置权限
//                .antMatchers("/").access("hasRole('TEST')")//该路径需要TEST角色
                .antMatchers("/").authenticated()//该路径需要登录认证
//                .antMatchers("/brand/getList").hasAuthority("TEST")//该路径需要TEST权限
                .antMatchers("/**").permitAll()
                .and()//启用基于http的认证
                .httpBasic()
                .realmName("/")
                .and()//配置登录页面
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .and()//配置退出路径
                .logout()
                .logoutSuccessUrl("/")
//                .and()//记住密码功能
//                .rememberMe()
//                .tokenValiditySeconds(60*60*24)
//                .key("rememberMeKey")
                .and()//关闭跨域伪造
                .csrf()
                .disable()
                .headers()//去除X-Frame-Options
                .frameOptions()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UmsAdmin admin = adminService.getAdminByUsername(username);
                if(admin!=null){
                    return new AdminUserDetails(admin);
                }
                throw new UsernameNotFoundException("用户名或密码错误");
            }
        };
    }
}
