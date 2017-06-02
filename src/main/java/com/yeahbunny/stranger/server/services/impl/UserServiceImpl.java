package com.yeahbunny.stranger.server.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.UserService;

@Repository
@Transactional
public class UserServiceImpl implements UserService {
	
	@Inject UserRepository userRepo;
	
	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User findUserById(long id) {
		return userRepo.findOne(id);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	
	
}
