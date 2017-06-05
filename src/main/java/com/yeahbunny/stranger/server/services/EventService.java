package com.yeahbunny.stranger.server.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.RequestParam;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEvent;
import com.yeahbunny.stranger.server.controller.dto.response.StrangersPlainEvent;
import com.yeahbunny.stranger.server.model.Event;

public interface EventService {
	/**
	 * 
	 * @return All services with all children entities
	 */
	public List<Event> findAllEventsEagerly();

	/**
	 * 
	 * @return All services without children entities
	 */
	public List<Event> findAllEventsLazy();

	/**
	 * 
	 * @return Plain events objects within range
	 */
	public List<StrangersPlainEvent> findPlainEvents(double northeast_lat, double northeast_lng, double southwest_lat,
			double southwest_lng);

	/**
	 * 
	 * @param eventId
	 * @return
	 */
	public Event findEventById(long eventId);

	/**
	 * s
	 * 
	 * @param id
	 * @return Event with eagerly loaded children entities
	 */
	Event findEventByIdEagerly(long id);

	/**
	 * 
	 * @param id
	 * @param username
	 * @return
	 * @throws EntityNotFoundException
	 */
	StrangersEvent findUserStrangerEventAndRefreshTimestamp(long id, String username) throws EntityNotFoundException;

	/**
	 * 
	 * @param username
	 * @return
	 */
	List<Event> findEventsCreatedByUser(String username);

	public Long save(Event event);

	List<Event> findEventsAttendedByUser(String username);

}
