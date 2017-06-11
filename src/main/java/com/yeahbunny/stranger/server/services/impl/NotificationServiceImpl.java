package com.yeahbunny.stranger.server.services.impl;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.controller.dto.response.NotificationResponse;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.UserRepository;
import com.yeahbunny.stranger.server.services.NotificationService;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Inject
	UserRepository userRepo;
	
	@Override
	public NotificationResponse findNotificationsByUsername(String username) throws EntityNotFoundException {
		NotificationResponse notificationResponse = null;
		User user = userRepo.findByUsername(username);
		// TODO - pobranie moich eventów i eventów w których uczestniczę z obiektu usera i przekształcenie ich na odpowiednie obiekty:
		// user.getEvents();
		// user.getEventAttenders();
		// Na razie dodałem NotificationResponse, ale możesz tu dać cokolwiek
		return notificationResponse;
	}

}
