package com.yeahbunny.stranger.server.services;

import java.util.List;

import com.yeahbunny.stranger.server.model.User;

public interface UserService {
	public List<User> findAllUsers();
	
	public User findUserById(long id);
	
	public User findUserByUsername(String username);
}
