package com.yeahbunny.stranger.server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
/*
        tak będzieee
PersonEntity entity = userRepository.findByEmail(login);

        if(entity == null){
            throw new UsernameNotFoundException("USER_NOT_FOUND");
        }*/

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(AppRoles.USER));
        return new CustomUserDetails((long)1,login, "asd", roles);
    }
}
