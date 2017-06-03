package com.yeahbunny.stranger.server.services.impl;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.exception.EventAttenderExistsException;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.repositories.EventAttenderRepository;
import com.yeahbunny.stranger.server.services.EventAttenderService;

@Repository
@Transactional
public class EventAttenderServiceImpl implements EventAttenderService {

	@Inject
	EventAttenderRepository eventAttenderRepo;

	@Override
	public void save(Iterable<EventAttender> evAttenders) {
		eventAttenderRepo.save(evAttenders);
	}

	@Override
	public void addNewEventAttender(EventAttender evAttender) throws EventAttenderExistsException {
		if (evAttenderExists(evAttender) || evAttenderIsCreator(evAttender))
			throw new EventAttenderExistsException();

		eventAttenderRepo.save(evAttender);
	}

	@Override
	public void quitFromEvent(EventAttender evAttender) throws EntityNotFoundException {
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
