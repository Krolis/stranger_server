package com.yeahbunny.stranger.server.controller.dto.response;

public class RegistrationResponse {
    private long newUserId;
    private String info;
    
    public RegistrationResponse() {
    }
	public long getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(long newUserId) {
		this.newUserId = newUserId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    
}
