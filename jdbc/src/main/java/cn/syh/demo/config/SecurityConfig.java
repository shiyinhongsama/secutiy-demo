package cn.syh.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        UserDetails xiaoming = User.builder()
                .username("xiaoming")
                .password("{noop}123456")
                .roles("USER")
                .build();
        UserDetails xiaozhang = User.builder()
                .username("xiaozhang")
                .password("{noop}654321")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(xiaoming);
        users.createUser(xiaozhang);
        return users;
    }

}
