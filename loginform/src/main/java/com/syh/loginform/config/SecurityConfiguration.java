package com.syh.loginform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests() //开启权限配置 返回 ExpressionInterceptUrlRegistry
                .anyRequest() //所有请求
                .authenticated() //都要认证
                .and() //开启新一轮配置
                .formLogin() //表单登录配置
                .loginPage("/login.html") //登录页
                .loginProcessingUrl("/doLogin") //登录接口
                .defaultSuccessUrl("/index.html") //成功后跳转
                .failureUrl("/login.html") //失败后跳转
                .usernameParameter("uname") //用户名参数
                .passwordParameter("passwd") //密码参数
                .permitAll() //表示登录页不做拦截
                .and()
                .csrf().disable(); //禁用CSRF防御
        return http.build();
    }
}
