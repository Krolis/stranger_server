package com.yeahbunny.stranger.server.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.EventMessage;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.model.notifications.NotificationType;
import com.yeahbunny.stranger.server.model.notifications.StrangerNotification;
import com.yeahbunny.stranger.server.repositories.EventAttenderRepository;
import com.yeahbunny.stranger.server.repositories.EventRepository;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Inject
	UserRepository userRepo;

	@Inject
	EventRepository eventRepo;

	@Inject
	EventAttenderRepository eventAtRepo;

	@Override
	public List<StrangerNotification> findNotificationsByUsername(String username) throws EntityNotFoundException {
		List<StrangerNotification> resultNotifications = new ArrayList<>();
		User user = userRepo.findByUsername(username);

		List<Event> myEventsWithNewMsgs = filterMyEventsWithNewMsgs(user.getEvents());
		List<EventAttender> eventAttendersWithNewMsgs = filterAttendedEventsWithNewMsgs(user.getEventAttenders());
		List<Event> attendEventsWithNewMsgs = eventAttendersWithNewMsgs.stream().map(evAt -> evAt.getEvent())
				.collect(Collectors.toList());
		Set<EventAttender> newAttendersOnMyEvent = filterMyEventsAttenders(user.getEvents());

		myEventsMsgsNotification(resultNotifications, myEventsWithNewMsgs);
		attendEventsMsgsNotification(resultNotifications, attendEventsWithNewMsgs);
		newAttendersInMyEventsNotification(resultNotifications, newAttendersOnMyEvent);

		refreshEventsTimestamp(myEventsWithNewMsgs);
		refreshEventAttendersTimestamps(newAttendersOnMyEvent);
		refreshEventAttendersConsumed(eventAttendersWithNewMsgs);
		
		return resultNotifications;
	}

	private void refreshEventAttendersConsumed(List<EventAttender> eventAttendersWithNewMsgs) {
		Date curDate = new Date();
		eventAttendersWithNewMsgs.stream().forEach(evAt -> evAt.setReadMessageTimestamp(curDate));
	}

	private void refreshEventAttendersTimestamps(Set<EventAttender> newAttendersOnMyEvent) {
		newAttendersOnMyEvent.stream().forEach(evAt -> evAt.setConsumed("T"));
	}

	private void refreshEventsTimestamp(List<Event> myEventsWithNewMsgs) {
		Date curDate = new Date();
		myEventsWithNewMsgs.stream().forEach(ev -> ev.setReadMessageTimestamp(curDate));
	}

	private Set<EventAttender> filterMyEventsAttenders(List<Event> events) {
		Set<EventAttender> eventsAttenders = new HashSet<>();
		events.stream().forEach(ev -> ev.getEventAttenders().stream().filter(evAt -> "F".equals(evAt.getConsumed()))
				.forEach(evAt -> eventsAttenders.add(evAt)));
		return eventsAttenders;
	}

	private List<EventAttender> filterAttendedEventsWithNewMsgs(Set<EventAttender> eventAttenders) {
		return eventAttenders.stream()
				.filter(evAt -> getLastEventMessage(evAt.getEvent().getEventMessages()) != null
						&& getLastEventMessage(evAt.getEvent().getEventMessages()).getDate()
								.after(evAt.getReadMessageTimestamp()))
				.collect(Collectors.toList());
	}

	private List<Event> filterMyEventsWithNewMsgs(List<Event> events) {
		return events.stream()
				.filter(ev -> getLastEventMessage(ev.getEventMessages()) != null
						&& getLastEventMessage(ev.getEventMessages()).getDate().after(ev.getReadMessageTimestamp()))
				.collect(Collectors.toList());
	}

	private EventMessage getLastEventMessage(List<EventMessage> eventMessages) {
		if (eventMessages != null && !eventMessages.isEmpty())
			return eventMessages.get(eventMessages.size() - 1);
		return null;
	}

	private void newAttendersInMyEventsNotification(List<StrangerNotification> resultNotifications,
			Set<EventAttender> events) {
	}

	private void attendEventsMsgsNotification(List<StrangerNotification> resultNotifications, List<Event> events) {

		if (events.size() == 0) {
			return;
		} else if (events.size() == 1) {
			Event event = events.get(0);
			resultNotifications.add(oneEventMsgNotification(event));
		} else {
			resultNotifications.add(fewAttendEventsMsgNotification(events));
		}
	}

	private void myEventsMsgsNotification(List<StrangerNotification> resultNotifications, List<Event> events) {
		if (events.size() == 0) {
			return;
		} else if (events.size() == 1) {
			Event event = events.get(0);
			resultNotifications.add(oneEventMsgNotification(event));
		} else {
			resultNotifications.add(fewMyEventsMsgNotification(events));
		}
	}

	private StrangerNotification oneEventMsgNotification(Event event) {
		StrangerNotification notification = new StrangerNotification();
		notification.setNotificationType(NotificationType.ONE_EVENT_MSG);
		notification.setItemId(event.getIdEvent());
		notification.setContent(event.getTitle());
		return notification;
	}

	private StrangerNotification fewMyEventsMsgNotification(List<Event> events) {
		StrangerNotification notification = new StrangerNotification();
		notification.setNotificationType(NotificationType.FEW_MY_EVENTS_MSG);
		notification.setContent(contentByEventsNames(events));
		return notification;
	}

	private StrangerNotification fewAttendEventsMsgNotification(List<Event> events) {
		StrangerNotification notification = new StrangerNotification();
		notification.setNotificationType(NotificationType.FEW_ATTEND_EVENTS_MSG);
		notification.setContent(contentByEventsNames(events));
		return notification;
	}

	private String contentByEventsNames(List<Event> events) {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<Event> iterator = events.iterator();
		stringBuilder.append(iterator.next().getTitle());
		while (iterator.hasNext()) {
			stringBuilder.append(", ");
			stringBuilder.append(iterator.next().getTitle());
		}

		return stringBuilder.toString();
	}

}
