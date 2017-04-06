package com.yeahbunny.stranger.server.controller.dto.request;


import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @ApiModelProperty(required = true)
    @NotNull
    @NotBlank
    private String login;

    @ApiModelProperty(required = true)
    @NotNull
    @NotBlank
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
