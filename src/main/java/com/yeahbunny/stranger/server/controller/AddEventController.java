package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.request.AddEventRequest;
import com.yeahbunny.stranger.server.controller.dto.response.AddEventResponse;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.model.factory.EventFactory;
import com.yeahbunny.stranger.server.security.CustomUserDetails;
import com.yeahbunny.stranger.server.services.EventService;
import com.yeahbunny.stranger.server.services.PrincipalProvider;
import com.yeahbunny.stranger.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by kroli on 30.05.2017.
 */
@Controller
public class AddEventController {

    @Inject
    private PrincipalProvider principalProvider;

    @Inject
    private UserService userService;

    @Inject
    private EventService eventService;

    @RequestMapping(value = "/user/addEvent", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public AddEventResponse addEvent(@RequestBody @Valid AddEventRequest addEventRequest){
        Long userId = principalProvider.getUserId();
        User user = userService.findUserById(userId);

        Event event = EventFactory.newEvent(addEventRequest,user);

        Long eventId = eventService.save(event);

        System.out.println(addEventRequest.toString());
        return new AddEventResponse(eventId);
    }
}
