package com.yeahbunny.stranger.server.model.factory;

import com.yeahbunny.stranger.server.controller.dto.request.AddEventRequest;
import com.yeahbunny.stranger.server.model.Event;
import com.yeahbunny.stranger.server.model.User;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Created by kroli on 02.06.2017.
 */
public class EventFactory {
    public static Event newEvent(AddEventRequest addEventRequest, User user) {
        Event resultEvent = new Event();
        resultEvent.setLongitude(addEventRequest.getLongitude());
        resultEvent.setLatitude(addEventRequest.getLatitude());
        resultEvent.setTitle(addEventRequest.getTitle());
        resultEvent.setDetails(addEventRequest.getDescription());
        resultEvent.setDetails(addEventRequest.getPlace());
        resultEvent.setDateStart(getDateStart(addEventRequest));
        resultEvent.setDateEnd(calculateDateEnd(addEventRequest.getDateStart(), addEventRequest.getDurationHours()));
        resultEvent.setEventMessages(Collections.emptyList());
        resultEvent.setMaxAttenders(addEventRequest.getMaxAttenders());
        resultEvent.setCreator(user);
        resultEvent.setReports(Collections.emptySet());

        return resultEvent;
    }

    private static Date getDateStart(AddEventRequest addEventRequest) {
        return addEventRequest.getDateStart().getTime();
    }

    private static Date calculateDateEnd(Calendar dateStart, Integer durationHours) {
        dateStart.add(Calendar.HOUR,durationHours);
        return dateStart.getTime();
    }
}
