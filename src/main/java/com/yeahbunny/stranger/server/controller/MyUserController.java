package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerUser;
import com.yeahbunny.stranger.server.model.User;
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
    public StrangerUser getMyUser(){
        StrangerUser eventOwner = new StrangerUser();
        eventOwner.setId(3);
        eventOwner.setNick("Mateusz69");
        eventOwner.setAge(52);
        eventOwner.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-0/s160x160/14117713_1040333702710708_7798235088864788788_n.jpg?oh=e309c504c7786a3f94e29b74da172ec5&oe=59B5945D");
        eventOwner.setFemale(false);
        return eventOwner;
    }
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public StrangerUser getMyUser(@PathVariable long userId){
        return new StrangerUser(userService.findUserById(userId));
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<StrangerUser> getAllUsers(){
    	List<User> modelUsers = userService.findAllUsers();
        List<StrangerUser> responseUsers = new ArrayList<>();
        for (User modelUser : modelUsers) {
        	StrangerUser responseUser = new StrangerUser(modelUser);
        	responseUsers.add(responseUser);
        }
        return responseUsers;
    }
}
