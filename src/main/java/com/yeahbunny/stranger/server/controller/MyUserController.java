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
    	
    	String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    	
    	User user = userService.findUserByUsername(username);
    	
        StrangerUser eventOwner = new StrangerUser(user);
        // TODO - obsługa photo
        eventOwner.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-0/s160x160/14117713_1040333702710708_7798235088864788788_n.jpg?oh=e309c504c7786a3f94e29b74da172ec5&oe=59B5945D");
        return eventOwner;
    }
    
    // TODO
    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    public StrangerUser editMyUser(){
    	
    	String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    	
    	User user = userService.findUserByUsername(username);
    	
        StrangerUser eventOwner = new StrangerUser(user);
         return eventOwner;
    }
    
}
