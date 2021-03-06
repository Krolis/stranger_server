package com.yeahbunny.stranger.server.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.yeahbunny.stranger.server.controller.dto.request.LoginRequest;
import com.yeahbunny.stranger.server.controller.dto.response.LoginResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/user/session")
public class UserSessionController {

    private static final Logger LOG = LoggerFactory.getLogger(UserSessionController.class);

    @Autowired
    @Qualifier("userAuthenticationManager")
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "Logowanie")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "X-Auth-Token value"),
            @ApiResponse(code = 403, message = "niepoprawne dane")}
    )
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestBody @Valid LoginRequest userLoginRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLoginRequest.getLogin(), userLoginRequest.getPassword());
        Authentication a = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(a);
        LoginResponse response = new LoginResponse();
        response.setToken(RequestContextHolder.currentRequestAttributes().getSessionId());
        System.out.println("Date:" + new Date());
        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }


}
