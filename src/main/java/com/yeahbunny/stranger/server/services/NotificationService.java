package com.yeahbunny.stranger.server.services;

import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.controller.dto.response.NotificationResponse;
import com.yeahbunny.stranger.server.model.notifications.StrangerNotification;

import java.util.List;

public interface NotificationService {

	List<StrangerNotification> findNotificationsByUsername(String username) throws EntityNotFoundException;

}
