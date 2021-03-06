package com.yeahbunny.stranger.server.services;

import java.util.List;

import com.yeahbunny.stranger.server.exception.UserExistsException;
import com.yeahbunny.stranger.server.model.User;

public interface UserService {
	public List<User> findAllUsers();
	
	public User findUserById(long id);
	
	public User findUserByUsername(String username);
	
	public User addNewUser(User newUser) throws UserExistsException;

	User findUserByUsernameEagerily(String username);

	User findUserByUsernameWithOwnEvents(String username);

	User updateUser(User user);
}
