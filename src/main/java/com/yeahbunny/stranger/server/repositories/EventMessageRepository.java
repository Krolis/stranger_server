package com.yeahbunny.stranger.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.EventMessage;

@Transactional
public interface EventMessageRepository extends JpaRepository<EventMessage, Long> {
}
