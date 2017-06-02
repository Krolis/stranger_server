package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.EventAttenderPK;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEventListItem;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class MyEventsController {
	
	@Inject
	EventService eventService;

	@RequestMapping(value = "/user/myEvents", method = RequestMethod.GET)
	@ResponseBody
    @PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<StrangersEventListItem>> getMyEvents() {
		String username = AuthUtils.getAuthenticatedUserUsername();
		List<Event> myEvents = eventService.findEventsCreatedByUser(username);
		List<StrangersEventListItem> responseEvents = new ArrayList<>();

		for(Event myEvent : myEvents) {
			responseEvents.add(new StrangersEventListItem(myEvent,myEvent.getUnreadedMessages()));
		}

		return ResponseEntity.ok(responseEvents);
	}
}
