package com.yeahbunny.stranger.server.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("select e from Event e where dateEnd > CURRENT_TIMESTAMP")
	List<Event> findAllActive();
	
	@Query("select CURRENT_TIMESTAMP from Event e")
	Date dateTest();
	//@Query("Select u from User u where u.login = ?1")
	//public User findEvents(String username);
}
