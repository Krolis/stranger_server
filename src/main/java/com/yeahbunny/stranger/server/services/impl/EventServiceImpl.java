package com.yeahbunny.stranger.server.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.repositories.EventRepository;
import com.yeahbunny.stranger.server.services.EventService;

@Repository
@Transactional
public class EventServiceImpl implements EventService {
	
	@Inject
	EventRepository eventRepo;
	
	@Override
	public List<Event> findAllEventsLazy() {
		return eventRepo.findAll();
	}
	
	@Override
	public List<Event> findAllEventsEagerly() {
		List<Event> events = eventRepo.findAll();
		// events.get
	
		return eventRepo.findAll();
	}

	@Override
	public Event findEventById(long id) {
		return eventRepo.findOne(id);
	}
	
	
}
