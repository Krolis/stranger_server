package com.yeahbunny.stranger.server.controller;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.model.notifications.StrangerNotification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.NotificationResponse;
import com.yeahbunny.stranger.server.services.NotificationService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

import java.util.List;

@Controller
public class NotificationController {

	@Inject
	NotificationService notificationService;

	@RequestMapping(value = "/user/notifications", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<StrangerNotification>> getMyEvents() {
		String username = AuthUtils.getAuthenticatedUserUsername();
        List<StrangerNotification> notifications = null;
		try {
			notifications = notificationService.findNotificationsByUsername(username);
		} catch (EntityNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(notifications);
	}
}
