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
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerUser;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.services.UserService;

@Controller
public class UserController {
	
	@Inject
	UserService userService;
	
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StrangerUser> getMyUser(@PathVariable long userId){
    	User user = userService.findUserById(userId);
    	if (user != null)
    		return ResponseEntity.ok(new StrangerUser(user));
    	else 
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
