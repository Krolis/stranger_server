package com.yeahbunny.stranger.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

	//@Query("Select u from User u where u.login = ?1")
	//public User findEvents(String username);
}
