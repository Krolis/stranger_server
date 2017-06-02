package com.yeahbunny.stranger.server.controller.dto.request;

import java.util.Calendar;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by kroli on 30.05.2017.
 */
public class RegistrationRequest {

	@ApiModelProperty(required = true)
	@NotNull
	@NotBlank
	private String login;

	@ApiModelProperty(required = true)
	@NotNull
	@NotBlank
	private String hashedPw;

	private Calendar birthdate;

	@NotNull
	private boolean female;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashedPw() {
		return hashedPw;
	}

	public void setHashedPw(String hashedPw) {
		this.hashedPw = hashedPw;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isFemale() {
		return female;
	}

	public void setFemale(boolean female) {
		this.female = female;
	}

	
	
}