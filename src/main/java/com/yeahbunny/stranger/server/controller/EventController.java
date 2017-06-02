package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.services.EventService;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class EventController {
	
	@Inject
	EventService eventService;

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
