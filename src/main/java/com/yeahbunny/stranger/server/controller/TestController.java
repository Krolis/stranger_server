package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yeahbunny.stranger.server.controller.dto.response.MyTestResponse;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.repositories.UserRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController()
@RequestMapping(value = "/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value = "Metoda testowa")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful")}
    )
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public MyTestResponse testMethod(){

        LOG.debug("tiruriru log");
        return new MyTestResponse(69,"hello world", new ArrayList<String>() {{ add("asd"); add("zxc");}});
    }

    @ApiOperation(value = "Metoda testowa, wymaga autoryzacji")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 403, message = "blad autentyfikacji")}
    )
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    @ResponseBody
    public MyTestResponse test403Method(){

        LOG.debug("zautoryzowany uzytkownik");
        return new MyTestResponse(12,"hello world zautentyfikowany uzytkowniku");
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers(){
    	User user1 = new User();
    	user1.setName("user1");
    	userRepository.save(user1);
    	User user2 = new User();
    	user2.setName("user2");
    	userRepository.save(user2);
    	List<User> users = new ArrayList<>();
    	for(User dbUser : userRepository.findAll()) {
    		users.add(dbUser);
    	}
    	return users;
    }
}
