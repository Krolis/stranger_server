package com.yeahbunny.stranger.server.services;

import javax.persistence.EntityNotFoundException;

import com.yeahbunny.stranger.server.controller.dto.response.NotificationResponse;

public interface NotificationService {

	NotificationResponse findNotificationsByUsername(String username) throws EntityNotFoundException;

}
