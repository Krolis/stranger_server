package com.yeahbunny.stranger.server.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
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
		List<StrangersEventListItem> responseEvents = null;
		try {
			// TODO - mapowanie w service
			List<Event> myEvents = eventService.findEventsCreatedByUser(username);
			responseEvents = myEvents.stream().map(ev -> new StrangersEventListItem(ev, ev.getUnreadedMessages()))
					.collect(Collectors.toList());
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(responseEvents);
	}
}
