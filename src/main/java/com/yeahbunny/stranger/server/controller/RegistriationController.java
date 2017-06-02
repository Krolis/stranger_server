package com.yeahbunny.stranger.server.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.request.RegistrationRequest;
import com.yeahbunny.stranger.server.controller.dto.response.RegistrationResponse;
import com.yeahbunny.stranger.server.exception.UserExistsException;
import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.model.factory.UserFactory;
import com.yeahbunny.stranger.server.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class RegistriationController {
	
	@Inject
	UserService userService;
	
	@ApiOperation(value = "Rejestracja")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Niepoprawne dane")}
    )
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest regRequest){
    	// TODO - walidacja
		RegistrationResponse regResponse = new RegistrationResponse();
    	User newUser = UserFactory.newUser(regRequest);
    	try {
    		newUser = userService.addNewUser(newUser);
    	} catch (UserExistsException e) {
    		regResponse.setInfo("User with that login already exists");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(regResponse);
    	}
    	regResponse.setNewUserId(newUser.getIdUser());
    	return ResponseEntity.ok(regResponse);
    }
}
