package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEvent;
import com.yeahbunny.stranger.server.controller.dto.response.StrangersPlainEvent;
import com.yeahbunny.stranger.server.exception.EventAttenderExistsException;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.services.EventAttenderService;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.services.UserService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class EventController {
	
	@Inject
	EventService eventService;
	
	@Inject
	UserService userService;
	
	@Inject 
	EventAttenderService eventAttenderService;

	// TODO - to remove
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StrangersEvent> getEventOld(@RequestParam long id){
    	return getEvent(id);
    }
    
    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StrangersEvent> getEvent(@PathVariable long eventId){
    	Event event = eventService.findEventByIdEagerly(eventId);
    	if (event == null) 
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        StrangersEvent strangerEvent = new StrangersEvent(event);
        return ResponseEntity.ok(strangerEvent);
    }
    
    @RequestMapping(value = "/event/{eventId}/attend", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> joinToEvent(@PathVariable long eventId){
		String username = AuthUtils.getAuthenticatedUserUsername();
		long start_time = System.nanoTime();
    	
    	try {
    		eventAttenderService.joinToEvent(eventId, username);
    	} catch(EventAttenderExistsException ex) {
    		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    	} catch (EntityNotFoundException ex) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	finally {
    		long end_time = System.nanoTime();
    		double difference = (end_time - start_time)/1e6;
    		System.out.println("Time: " + difference);
    	}
    	
        return ResponseEntity.ok(null);
    }
    
    @RequestMapping(value = "/event/{eventId}/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> quitFromEvent(@PathVariable long eventId){
    	String username = AuthUtils.getAuthenticatedUserUsername();
		long start_time = System.nanoTime();
    	
    	try {
    		eventAttenderService.quitFromEvent(eventId, username);
    	} catch (EntityNotFoundException ex) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	finally {
    		long end_time = System.nanoTime();
    		double difference = (end_time - start_time)/1e6;
    		System.out.println("Time: " + difference);
    	}
    	
        return ResponseEntity.ok(null);
    }
    

    
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<StrangersPlainEvent>> getEvents(@RequestParam double northeast_lat,
                                                @RequestParam double northeast_lng,
                                                @RequestParam double southwest_lat,
                                                @RequestParam double southwest_lng){
    	// TODO - obsługa parametrów
        List<StrangersPlainEvent> plainEvents = new ArrayList<>();

        List<Event> modelEvents = eventService.findAllEventsLazy();
        
        for (Event event : modelEvents) {
        	plainEvents.add(new StrangersPlainEvent(event));
        }

        return ResponseEntity.ok(plainEvents);
    }
    
}
