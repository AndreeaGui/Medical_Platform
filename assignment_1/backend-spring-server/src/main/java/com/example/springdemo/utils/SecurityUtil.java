package com.example.springdemo.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityUtil {

    public String getSecurityLoggedUserUsername() {

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<String> getSecurityLoggedUserAuthorities() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

    }

}
