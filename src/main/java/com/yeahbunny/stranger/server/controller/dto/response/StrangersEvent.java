package com.yeahbunny.stranger.server.controller.dto.response;



import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.EventAttender;
import com.yeahbunny.stranger.server.model.EventMessage;

public class StrangersEvent {
    private Long id;
    private EventType type;
    private LatLng position;
    private String title;
    private String details;
    private GregorianCalendar date;
    private List<StrangerUser> attenders;
    private List<StrangerEventMessage> messages;
    private String where;
    private StrangerUser owner;
    private UserEventRelation usEvRelation;

    public StrangersEvent() {
    	date = new GregorianCalendar();
    	attenders = new ArrayList<>();
    	messages = new ArrayList<>();
    }
    
    public StrangersEvent(Event event, UserEventRelation usEvRelation) {
    	this();
    	this.id = event.getIdEvent();
    	this.position = new LatLng(event.getLatitude(), event.getLongitude());
    	this.title = event.getTitle();
    	this.details = event.getDetails();
    	this.date.setTime(event.getDateStart());
    	this.type = event.checkEventType();
    	for (EventAttender evAttender : event.getEventAttenders()) {
    		this.attenders.add(new StrangerUser(evAttender.getUser()));
    	}
    	for(EventMessage evMessage : event.getEventMessages()) {
    		this.messages.add(new StrangerEventMessage(evMessage));
    	}
    	this.owner = new StrangerUser(event.getCreator());
    	this.usEvRelation = usEvRelation;
    	// TODO - where
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public List<StrangerUser> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<StrangerUser> attenders) {
        this.attenders = attenders;
    }

    public List<StrangerEventMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<StrangerEventMessage> messages) {
        this.messages = messages;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getWhere() {
        return where;
    }

    public void setOwner(StrangerUser owner) {
        this.owner = owner;
    }

    public StrangerUser getOwner() {
        return owner;
    }

	public UserEventRelation getUsEvRelation() {
		return usEvRelation;
	}

	public void setUsEvRelation(UserEventRelation usEvRelation) {
		this.usEvRelation = usEvRelation;
	}
    
}
