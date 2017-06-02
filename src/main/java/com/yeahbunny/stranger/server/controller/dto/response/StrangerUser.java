package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
	    	this.age = calculateAge(user.getBirthdate());
	    	if ("F".equals(user.getGender()))
	    		female = true;
	    	else female = false;
	    	photoUrl = user.getPhotoUrl();
    	}
    }
    
    private int calculateAge(Date birthdate) {
    	int age = 0;
    	try {
    		age = getDiffYears(new Date(), birthdate);
    	} catch (Exception e) {
    		
    	}
		return age;
	}

    private int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.DAY_OF_YEAR) > b.get(Calendar.DAY_OF_YEAR)) {
            diff--;
        }
        return diff;
    }
    
    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(date);
        return cal;
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
