package com.syh.loginform.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@Slf4j
public class UserController {

    @GetMapping("/user")
    public void userInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("name = {}", name);
        log.info("authorities = {}", authorities);
    }

}
