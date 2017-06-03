package com.yeahbunny.stranger.server.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
