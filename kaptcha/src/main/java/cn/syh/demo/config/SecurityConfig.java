package cn.syh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(){
        KapthaAuthenticationProvider kapthaAuthenticationProvider = new KapthaAuthenticationProvider();
        kapthaAuthenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(
                User.builder().username("xiaoming").password("{noop}123456").roles("admin").build()
        ));
        return new ProviderManager(kapthaAuthenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests() //开启权限配置 返回 ExpressionInterceptUrlRegistry
                .antMatchers("/vc.jpg").permitAll()
                .anyRequest() //所有请求
                .authenticated() //都要认证
                .and() //开启新一轮配置
                .formLogin() //表单登录配置
                .loginPage("/login.html") //登录页
                .loginProcessingUrl("/doLogin") //登录接口
                .defaultSuccessUrl("/index.html")
                .failureUrl("/login.html") //失败后跳转
                .usernameParameter("uname") //用户名参数
                .passwordParameter("passwd") //密码参数
                .permitAll() //表示登录页不做拦截
                .and()
                .logout()
                .invalidateHttpSession(true) //是否使session失效 默认为true
                .clearAuthentication(true)  //是否清除认证信息，默认为true
                .and()
                .csrf().disable().build();
    }

    static class KapthaAuthenticationProvider extends DaoAuthenticationProvider{
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String kaptha = request.getParameter("kaptcha");
            String sessionKaptcha = (String) request.getSession().getAttribute("kaptcha");
            if (kaptha!=null && sessionKaptcha!=null && kaptha.equalsIgnoreCase(sessionKaptcha)){
                return super.authenticate(authentication);
            }
            throw new AuthenticationServiceException("验证码错误");
        }
    }

}
