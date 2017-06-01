package com.yeahbunny.stranger.server.controller.dto.response;

/**
 * Created by kroli on 30.05.2017.
 */
public class AddEventResponse {

    private Long eventId;

    public AddEventResponse(long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
