package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEventListItem;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.services.EventAttenderService;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.services.UserService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

/**
 * Created by kroli on 02.06.2017.
 */
@Controller
public class EventsController {

	@Inject
	EventService eventService;

	@Inject
	UserService userService;
	
	@Inject
	EventAttenderService eventAttenderService;

	@RequestMapping(value = "/user/events", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<StrangersEventListItem>> getMyEvents() {
		String username = AuthUtils.getAuthenticatedUserUsername();
		User user = userService.findUserByUsernameEagerily(username);
		List<StrangersEventListItem> responseEvents = new ArrayList<>();
		Date curTimestamp = new Date();
		for (EventAttender evAttender : user.getEventAttenders()) {
			int unreadMessages = getUnreadMessages(evAttender);
			responseEvents.add(new StrangersEventListItem(evAttender.getEvent(), unreadMessages));
			evAttender.setReadMessageTimestamp(curTimestamp);
		}
		eventAttenderService.save(user.getEventAttenders());

		return ResponseEntity.ok(responseEvents);
	}

	/**
	 *
	 * chyba troche heheeh zero optymalnie ale w sumie lista eventów bedzie
	 * skonczona, attendersow raczej też
	 *
	 */
	private int getUnreadMessages(EventAttender evAttender) {
		int unreadMessages = 0;
		Date msgTimestamp = evAttender.getReadMessageTimestamp();
		if (msgTimestamp != null)
			unreadMessages = (int) evAttender.getEvent().getEventMessages().stream()
					.filter(msg -> msgTimestamp.before(msg.getDate())).count();
		else 
			unreadMessages = evAttender.getEvent().getEventMessages().size();
		return unreadMessages;
	}

}
