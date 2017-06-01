package com.yeahbunny.stranger.server.services;

import java.util.List;

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
	
	public Event findEventById(long id);
}
