package com.yeahbunny.stranger.server.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.request.AddCommentRequest;
import com.yeahbunny.stranger.server.controller.dto.response.StrangerEventMessage;
import com.yeahbunny.stranger.server.services.EventMessageService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class MessageController {
	
	@Inject
	EventMessageService eventMessageService;
	
    @RequestMapping(value = "/event/{eventId}/message", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<StrangerEventMessage>> addMessage(@PathVariable long eventId, @RequestBody AddCommentRequest message){
		String username = AuthUtils.getAuthenticatedUserUsername();
		List<StrangerEventMessage> notReadedMessages = null;
    	try {
    		notReadedMessages = eventMessageService.addNewMessageAndReturnNotReaded(eventId, username, message.getContent());
    	} catch (EntityNotFoundException ex) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}
    	
        return ResponseEntity.ok(notReadedMessages);
    }    
}
