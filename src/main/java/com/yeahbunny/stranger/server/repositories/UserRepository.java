package com.yeahbunny.stranger.server.repositories;

import org.springframework.data.repository.CrudRepository;

import com.yeahbunny.stranger.server.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
}
