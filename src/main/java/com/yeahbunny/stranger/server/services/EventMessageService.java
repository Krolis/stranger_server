package com.yeahbunny.stranger.server.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerEventMessage;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;

public interface EventMessageService {
	List<StrangerEventMessage> addNewMessageAndReturnNotReaded(Long eventId, String username, String content)
			throws EntityNotFoundException;
	
	void refreshUnreadCommentsTimestamp(User user, Event event);
}
