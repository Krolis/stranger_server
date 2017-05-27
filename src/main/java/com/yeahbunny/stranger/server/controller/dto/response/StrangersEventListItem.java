package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.Calendar;

/**
 * Created by kroli on 27.05.2017.
 */
public class StrangersEventListItem {

    private String title;
    private String where;
    private Calendar date;
    private long id;

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
}
