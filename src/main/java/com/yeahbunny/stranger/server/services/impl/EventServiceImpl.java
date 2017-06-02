package com.yeahbunny.stranger.server.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.EventRepository;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.EventService;

@Repository
@Transactional
public class EventServiceImpl implements EventService {
	
	@Inject
	EventRepository eventRepo;
	
	@Inject
	UserRepository userRepo;
	
	@Override
	public List<Event> findAllEventsLazy() {
		return eventRepo.findAll();
	}
	
	@Override
	public List<Event> findAllEventsEagerly() {
		List<Event> events = eventRepo.findAll();
		// Eager fetching children entities
		for (Event event : events) {
			event.getEventAttenders().size();
			event.getEventMessages().size();
		}
		return events;
	}

	@Override
	public Event findEventById(long id) {
		return eventRepo.findOne(id);
	}
	
	@Override
	public Event findEventByIdEagerly(long id) {
		Event event = eventRepo.findOne(id);
		if (event != null) {
			event.getEventAttenders().size();
			event.getEventMessages().size();
		}
		return event;
	}
	
	@Override
	public List<Event> findEventsCreatedByUser(String username) {
		User creator = userRepo.findByUsername(username);
		List<Event> events = new ArrayList<>();
		events.addAll(creator.getEvents());
		return events;
	}

	@Override
	public List<Event> findEventsAttendedByUser(String username) {
		User creator = userRepo.findByUsername(username);
		List<Event> events = creator.getEventAttenders()
				.stream()
				.map(eventAttender -> {
					return eventAttender.getEvent();
				})
				.collect(Collectors.toList());

		return events;
	}

	@Override
	public Long save(Event event) {
		Event saved = eventRepo.save(event);
		return saved.getIdEvent();
	}
}
