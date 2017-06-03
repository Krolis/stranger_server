package com.yeahbunny.stranger.server.services;

import com.yeahbunny.stranger.server.model.EventAttender;

public interface EventAttenderService {

	void save(Iterable<EventAttender> evAttenders);

}
