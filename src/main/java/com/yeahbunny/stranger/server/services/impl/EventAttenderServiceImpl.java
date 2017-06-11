package com.yeahbunny.stranger.server.services.impl;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.exception.EventAttenderExistsException;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.EventAttenderRepository;
import com.yeahbunny.stranger.server.services.EventAttenderService;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.services.UserService;

@Service
@Transactional
public class EventAttenderServiceImpl implements EventAttenderService {

	@Inject
	EventAttenderRepository eventAttenderRepo;
	
	@Inject
	UserService userService;
	
	@Inject
	EventService eventService;

	@Override
	public void save(Iterable<EventAttender> evAttenders) {
		eventAttenderRepo.save(evAttenders);
	}
	
	@Override
	public void joinToEvent(long eventId, String username) throws EntityNotFoundException, EventAttenderExistsException {
    	Event event = eventService.findEventById(eventId);
    	User user = userService.findUserByUsernameWithOwnEvents(username);
    	if (event == null || user == null) 
    		throw new EntityNotFoundException();
    	
    	EventAttender evAttender = new EventAttender(event, user);
    	addNewEventAttender(evAttender); 	
	}

	@Override
	public void quitFromEvent(long eventId, String username) throws EntityNotFoundException {
    	Event event = eventService.findEventById(eventId);
    	User user = userService.findUserByUsername(username);
    	if (event == null || user == null) 
    		throw new EntityNotFoundException();
    	
    	EventAttender evAttender = new EventAttender(event, user);
    	deleteEventAttender(evAttender);
	}
	

	private void addNewEventAttender(EventAttender evAttender) throws EventAttenderExistsException {
		if (evAttenderExists(evAttender) || evAttenderIsCreator(evAttender))
			throw new EventAttenderExistsException();

		eventAttenderRepo.save(evAttender);
	}
	
	private void deleteEventAttender(EventAttender evAttender) throws EntityNotFoundException {
		if (!evAttenderExists(evAttender))
			throw new EntityNotFoundException();
		eventAttenderRepo.delete(evAttender);
	}


	
	private boolean evAttenderIsCreator(EventAttender evAttender) {
		if (evAttender.getUser().getEvents() != null && evAttender.getUser().getEvents().contains(evAttender.getEvent()))
			return true;
		else return false;
	}

	private boolean evAttenderExists(EventAttender evAttender) {
		if (eventAttenderRepo.findOne(evAttender.getId()) != null)
			return true;
		else
			return false;
	}

}
