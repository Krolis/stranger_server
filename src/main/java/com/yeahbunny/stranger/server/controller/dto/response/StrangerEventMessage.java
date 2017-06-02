package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.GregorianCalendar;

import com.yeahbunny.stranger.server.model.EventMessage;

public class StrangerEventMessage {
	private long id;
    private StrangerUser user;
    private String content;
    private GregorianCalendar date;

    public StrangerEventMessage() {
    	date = new GregorianCalendar();
    }
    
    public StrangerEventMessage(EventMessage eventMessage) {
    	this();
    	this.id = eventMessage.getIdMessage();
    	this.user = new StrangerUser(eventMessage.getUser());
    	this.content = eventMessage.getContent();
    	this.date.setTime(eventMessage.getDate());
    }
    
    
    public void setUser(StrangerUser user) {
        this.user = user;
    }

    public StrangerUser getUser() {
        return user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
    
}
