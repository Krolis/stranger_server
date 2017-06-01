package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.GregorianCalendar;

import com.yeahbunny.stranger.server.model.Event;

public class StrangersPlainEvent {
    private Long id;
    private EventType type;
    private LatLng position;
    private String title;
    private String details;
    private GregorianCalendar date;

    public StrangersPlainEvent() {
    	this.date = new GregorianCalendar();
    }
    
    public StrangersPlainEvent(Event event) {
    	this();
    	this.id = event.getIdEvent();
    	this.position = new LatLng(event.getLatitude(), event.getLongitude());
    	this.title = event.getTitle();
    	this.details = event.getDetails();
    	this.date.setTime(event.getDateStart());
    	this.type = event.checkEventType();
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
}
