package com.yeahbunny.stranger.server.security;

import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Inject
	UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(login);
        if (user == null)
            throw new UsernameNotFoundException("USER_NOT_FOUND");

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(AppRoles.USER));
        return new CustomUserDetails(user.getIdUser(), user.getLogin(), user.getHashedPw(), roles);
    }
}
