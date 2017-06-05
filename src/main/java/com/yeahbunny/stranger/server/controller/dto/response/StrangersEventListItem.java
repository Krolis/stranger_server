package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.yeahbunny.stranger.server.model.Event;

/**
 * Created by kroli on 27.05.2017.
 */
public class StrangersEventListItem {

    private String title;
    private String where;
    private Calendar date;
    private long id;
    private int unreadMsg;

    public StrangersEventListItem() {
    	date = new GregorianCalendar();
    }
    
    public StrangersEventListItem(Event event, int unreadMsg) {
    	this();
    	this.id = event.getIdEvent();
    	this.title = event.getTitle();
    	this.where = event.getPlace();
    	this.date.setTime(event.getDateStart());
        this.unreadMsg = unreadMsg;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(int unreadMsg) {
        this.unreadMsg = unreadMsg;
    }
}
