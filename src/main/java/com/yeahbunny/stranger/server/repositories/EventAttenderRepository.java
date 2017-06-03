package com.yeahbunny.stranger.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yeahbunny.stranger.server.model.EventAttender;

@Transactional
public interface EventAttenderRepository extends JpaRepository<EventAttender, Long> {

}
