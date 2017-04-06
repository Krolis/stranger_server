package com.yeahbunny.stranger.server.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Zrobiłem to po to że dzięki temu to userId będzie można wyciągnąć z sesji jezeli użytkwnik się zaloguje i będzie zautoryzowany
 */
public class CustomUserDetails extends User {

    private Long userId;

    public CustomUserDetails(Long userId, String userLogin, String userPassword, List<GrantedAuthority> roles) {
        super(userLogin, userPassword, roles);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
