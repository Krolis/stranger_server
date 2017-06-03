package com.yeahbunny.stranger.server.services;

import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.exception.EventAttenderExistsException;
import com.yeahbunny.stranger.server.model.EventAttender;

public interface EventAttenderService {

	void save(Iterable<EventAttender> evAttenders);

	void joinToEvent(long eventId, String username)  throws EntityNotFoundException, EventAttenderExistsException;
	
	void quitFromEvent(long eventId, String username) throws EntityNotFoundException;

}
