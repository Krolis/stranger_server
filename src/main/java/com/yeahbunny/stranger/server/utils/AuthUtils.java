package com.yeahbunny.stranger.server.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.yeahbunny.stranger.server.security.CustomUserDetails;

public class AuthUtils {
	public static String getAuthenticatedUserUsername() {
		return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
	}
}
