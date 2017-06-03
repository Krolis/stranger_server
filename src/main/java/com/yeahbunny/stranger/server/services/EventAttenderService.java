package com.yeahbunny.stranger.server.services;

import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.exception.EventAttenderExistsException;
import com.yeahbunny.stranger.server.model.EventAttender;

public interface EventAttenderService {

	void save(Iterable<EventAttender> evAttenders);

	void addNewEventAttender(EventAttender evAttender) throws EventAttenderExistsException;

	void quitFromEvent(EventAttender evAttender) throws EntityNotFoundException;

}
