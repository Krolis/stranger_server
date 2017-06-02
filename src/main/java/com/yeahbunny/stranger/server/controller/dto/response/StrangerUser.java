package com.yeahbunny.stranger.server.controller.dto.response;

import com.yeahbunny.stranger.server.model.User;

public class StrangerUser {
    private long id;
    private String nick;
    private int age;
    private boolean female;
    private String photoUrl;
    
    public StrangerUser() {};

    public StrangerUser(User user) {
    	if (user != null) {
	    	this.id = user.getIdUser();
	    	this.nick = user.getLogin();
	    	this.age = user.calculateAge();
	    	if ("F".equals(user.getGender()))
	    		female = true;
	    	else female = false;
	    	photoUrl = user.getPhotoUrl();
    	}
    }
    
	public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public boolean isFemale() {
        return female;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
