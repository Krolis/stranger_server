package com.yeahbunny.stranger.server.controller;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.NotificationResponse;
import com.yeahbunny.stranger.server.services.NotificationService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

@Controller
public class NotificationController {

	@Inject
	NotificationService notificationService;

	@RequestMapping(value = "/user/notifications", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<NotificationResponse> getMyEvents() {
		String username = AuthUtils.getAuthenticatedUserUsername();
		NotificationResponse notification = null;
		try {
			notification = notificationService.findNotificationsByUsername(username);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(notification);
	}
}
