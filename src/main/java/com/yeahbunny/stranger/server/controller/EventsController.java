package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEventListItem;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.EventAttenderPK;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kroli on 02.06.2017.
 */
@Controller
public class EventsController {

    @Inject
    EventService eventService;

    @RequestMapping(value = "/user/events", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<StrangersEventListItem>> getMyEvents() {
        String username = AuthUtils.getAuthenticatedUserUsername();
        List<Event> myEvents = eventService.findEventsAttendedByUser(username);
        List<StrangersEventListItem> responseEvents = new ArrayList<>();

        for(Event myEvent : myEvents) {
            int unreadMessages = getUnreadMessages(myEvent);
            responseEvents.add(new StrangersEventListItem(myEvent,unreadMessages));
        }

        return ResponseEntity.ok(responseEvents);
    }

    /**
     *
     * chyba troche heheeh zero optymalnie ale w sumie lista eventów bedzie skonczona, attendersow raczej też
     *
     */
    private int getUnreadMessages(Event myEvent) {
        EventAttenderPK key = new EventAttenderPK();
        key.setIdUser(AuthUtils.getAuthenticatedUserId());
        key.setIdEvent(myEvent.getIdEvent());

        for(EventAttender eventAttender : myEvent.getEventAttenders()){
            if(eventAttender.getId().equals(key)){
                return eventAttender.getUnreadedMessages();
            }
        }

        return 0;
    }
}
