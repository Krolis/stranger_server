package com.yeahbunny.stranger.server.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.Arrays;

@Configuration
public class AuthenticationManagerConfig {

        @Bean(name = "userAuthenticationManager")
        @Autowired
        AuthenticationManager userAuthenticationManager(CustomUserDetailsService customUserDetailsService){

            DaoAuthenticationProvider userAuthProvider = new DaoAuthenticationProvider();
            userAuthProvider.setUserDetailsService(customUserDetailsService);
            return new ProviderManager(Arrays.asList(userAuthProvider));
        }

/*
    tak będziee dla admina
   @Bean(name = "adminAuthenticationManager")
        @Autowired
        AuthenticationManager adminAuthenticationManager(AdminUserDetailsService adminUserDetailsService){

            DaoAuthenticationProvider adminAuthProvider = new DaoAuthenticationProvider();
            adminAuthProvider.setUserDetailsService(adminUserDetailsService);

            return new ProviderManager(Arrays.asList(adminAuthProvider));
        }*/
}
