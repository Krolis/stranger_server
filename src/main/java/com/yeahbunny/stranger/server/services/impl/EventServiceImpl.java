package com.yeahbunny.stranger.server.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEvent;
import com.yeahbunny.stranger.server.controller.dto.response.UserEventRelation;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.EventRepository;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.EventMessageService;
import com.yeahbunny.stranger.server.services.EventService;

@Repository
@Transactional
public class EventServiceImpl implements EventService {
	
	@Inject
	EventRepository eventRepo;
	
	@Inject
	UserRepository userRepo;
	
	@Inject
	EventMessageService eventMessageService;
	
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

	private UserEventRelation getUserEventRelation(User user, Event event) {
		UserEventRelation usEvRelation;
		if (user.getEvents().contains(event))
			usEvRelation = UserEventRelation.OWNER;
		else if(user.getEventAttenders().stream().anyMatch(evAt -> evAt.getEvent().equals(event)))
			usEvRelation = UserEventRelation.ATTENDER;
		else
			usEvRelation = UserEventRelation.STRANGER;
		return usEvRelation;
	}

	@Override
	public StrangersEvent findUserStrangerEventAndRefreshTimestamp(long id, String username)
			throws EntityNotFoundException {
		Event event = null;
		User user = null;
		if((event = findEventByIdEagerly(id)) == null || (user = userRepo.findByUsername(username)) == null)
			throw new EntityNotFoundException();
		eventMessageService.refreshUnreadCommentsTimestamp(user, event);
		
		UserEventRelation usEvRelation = getUserEventRelation(user, event);
		return new StrangersEvent(event, usEvRelation);
	}

}
