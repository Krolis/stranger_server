package com.yeahbunny.stranger.server.controller;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerUser;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.security.CustomUserDetails;
import com.yeahbunny.stranger.server.services.UserService;
import com.yeahbunny.stranger.server.utils.AuthUtils;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class MyUserController {

	@Inject
	UserService userService;
	
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public StrangerUser getMyUser(){
    	
    	String username = AuthUtils.getAuthenticatedUserUsername();
    	
    	User user = userService.findUserByUsername(username);
    	
        StrangerUser eventOwner = new StrangerUser(user);
        return eventOwner;
    }
    
    // TODO
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public StrangerUser editMyUser(){
    	
    	String username = AuthUtils.getAuthenticatedUserUsername();
    	
    	User user = userService.findUserByUsername(username);
    	
        StrangerUser eventOwner = new StrangerUser(user);
        return eventOwner;
    }
    
}
