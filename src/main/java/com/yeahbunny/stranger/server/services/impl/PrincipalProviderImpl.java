package com.yeahbunny.stranger.server.services.impl;

import com.yeahbunny.stranger.server.security.CustomUserDetails;
import com.yeahbunny.stranger.server.services.PrincipalProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by kroli on 02.06.2017.
 */
@Service
public class PrincipalProviderImpl implements PrincipalProvider{

    @Override
    public Long getUserId() {
        return getCustomUserDetails().getUserId();
    }

    @Override
    public String getUsername() {
        return getCustomUserDetails().getUsername();
    }

    private CustomUserDetails getCustomUserDetails(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
