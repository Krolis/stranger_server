package com.yeahbunny.stranger.server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;

@Transactional
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("Select e from Event e where e.latitude > ?3 and e.latitude < ?1 and e.longitude > ?4 and e.longitude < ?2")
	public List<Event> findEventsInRange(double northeast_lat, double northeast_lng, double southwest_lat,
			double southwest_lng);
}
