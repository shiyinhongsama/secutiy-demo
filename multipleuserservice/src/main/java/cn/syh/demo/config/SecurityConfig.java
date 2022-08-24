package cn.syh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider provider1 = new DaoAuthenticationProvider();
        provider1.setUserDetailsService(new InMemoryUserDetailsManager(User.builder().username("xiaoming").password("{noop}123456").roles("admin").build()));
        DaoAuthenticationProvider provider2 = new DaoAuthenticationProvider();
        provider2.setUserDetailsService(new InMemoryUserDetailsManager(User.builder().username("xiaohong").password("{noop}123456").roles("admin").build()));
        return new ProviderManager(provider1, provider2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

}
