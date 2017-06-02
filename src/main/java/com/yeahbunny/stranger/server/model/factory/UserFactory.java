package com.yeahbunny.stranger.server.model.factory;

import com.yeahbunny.stranger.server.controller.dto.request.RegistrationRequest;
import com.yeahbunny.stranger.server.model.User;

/**
 * Created by kroli on 02.06.2017.
 */
public class UserFactory {
    public static User newUser(RegistrationRequest regRequest) {
    	User newUser = new User();
    	newUser.setLogin(regRequest.getLogin());
    	newUser.setHashedPw(regRequest.getHashedPw());
    	newUser.setGender(getGenderChar(regRequest.isFemale()));
    	newUser.setBirthdate(regRequest.getBirthdate().getTime());
        return newUser;
    }

    private static String getGenderChar(boolean isFemale) {
    	if (isFemale)
    		return "F";
    	else return "M";
    }
}
