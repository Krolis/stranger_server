package com.yeahbunny.stranger.server.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerEventMessage;
import com.yeahbunny.stranger.server.controller.dto.response.UserEventRelation;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.EventMessage;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.EventAttenderRepository;
import com.yeahbunny.stranger.server.repositories.EventMessageRepository;
import com.yeahbunny.stranger.server.repositories.EventRepository;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.EventMessageService;

@Service
@Transactional
public class EventMessageServiceImpl implements EventMessageService {

	@Inject
	EventMessageRepository eventMessageRepo;

	@Inject
	UserRepository userRepo;

	@Inject
	EventRepository eventRepo;

	@Inject
	EventAttenderRepository eventAttenderRepo;

	@Override
	public List<StrangerEventMessage> addNewMessageAndReturnNotReaded(Long eventId, String username, String content)
			throws EntityNotFoundException {
		List<StrangerEventMessage> unreadedComments = null;
		Event event = null;
		User user = null;
		if ((event = eventRepo.findOne(eventId)) == null || (user = userRepo.findByUsername(username)) == null)
			throw new EntityNotFoundException();

		EventMessage message = new EventMessage(event, user, content);
		eventMessageRepo.save(message);

		unreadedComments = findUserUnreadedComments(user, event);
		
		refreshUnreadCommentsTimestamp(user, event, getUserEventRelation(user, event));
		return unreadedComments;
	}

	private List<StrangerEventMessage> findUserUnreadedComments(User user, Event event) {
		Date lastReadDate = user.getEvents() != null && event.getEventAttenders() != null
				&& user.getEvents().contains(event) ? event.getReadMessageTimestamp()
						: event.getEventAttender(user).getReadMessageTimestamp();
		if (lastReadDate != null)
			return event.getEventMessages().stream().filter(msg -> msg.getDate().after(lastReadDate))
					.map(msg -> new StrangerEventMessage(msg)).collect(Collectors.toList());
		else
			return event.getEventMessages().stream().map(msg -> new StrangerEventMessage(msg))
					.collect(Collectors.toList());
	}

	@Override
	public void refreshUnreadCommentsTimestamp(User user, Event event, UserEventRelation usEvRelation) {
		if (usEvRelation == UserEventRelation.OWNER) {
			event.setReadMessageTimestamp(new Date());
			eventRepo.save(event);
		} else if (usEvRelation == UserEventRelation.ATTENDER) {
			EventAttender evAtt = event.getEventAttender(user);
			if(evAtt!=null){
				evAtt.setReadMessageTimestamp(new Date());
				eventAttenderRepo.save(evAtt);
			}
		}
	}
	
	// TODO - nadmiarowy kod - to samo w eventService - wydzielić w inne miejsce
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

}
