package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.EventType;
import com.yeahbunny.stranger.server.controller.dto.response.LatLng;
import com.yeahbunny.stranger.server.controller.dto.response.StrangersPlainEvent;

@Controller
public class MarkerController {

    private static final Logger LOG = LoggerFactory.getLogger(MarkerController.class);

    @RequestMapping(value = "markers", method = RequestMethod.GET)
    @ResponseBody
    public List<StrangersPlainEvent> getEvents(@RequestParam double northeast_lat,
                                                @RequestParam double northeast_lng,
                                                @RequestParam double southwest_lat,
                                                @RequestParam double southwest_lng){

        List<StrangersPlainEvent> mockEvents = new ArrayList<>();

        StrangersPlainEvent StrangersPlainEvent1 = new StrangersPlainEvent();
        StrangersPlainEvent1.setPosition(new LatLng((northeast_lat + southwest_lat) /2, (northeast_lng + southwest_lng)/2));
        StrangersPlainEvent1.setTitle("Spotkanie zespołu Strangers");
        StrangersPlainEvent1.setDetails("Stwórz z nami społeczność!");
        StrangersPlainEvent1.setDate(new GregorianCalendar(2017, 3,11,7,30));
        StrangersPlainEvent1.setType(EventType.NOW);
        mockEvents.add(StrangersPlainEvent1);

        StrangersPlainEvent StrangersPlainEvent2 = new StrangersPlainEvent();
        StrangersPlainEvent2.setPosition(new LatLng(0.0033 + (northeast_lat + southwest_lat) /2, 0.005 + (northeast_lng + southwest_lng)/2));
        StrangersPlainEvent2.setTitle("SZOPING");
        StrangersPlainEvent2.setDetails("zapraszam do galerii na małe zakupy albo kawe i ciastko :D");
        StrangersPlainEvent2.setDate(new GregorianCalendar(2017, 3,11,7,30));
        StrangersPlainEvent2.setType(EventType.NOW);
        mockEvents.add(StrangersPlainEvent2);


        StrangersPlainEvent StrangersPlainEvent3 = new StrangersPlainEvent();
        StrangersPlainEvent3.setPosition(new LatLng(50.064375, 19.939535));//rynek
        StrangersPlainEvent3.setTitle("piwko w pijalni");
        StrangersPlainEvent3.setDetails("Wieczorem na piwko ?? :>>");
        StrangersPlainEvent3.setDate(new GregorianCalendar(2017, 3,11,20,30));
        StrangersPlainEvent3.setType(EventType.FUTURE);
        mockEvents.add(StrangersPlainEvent3);


        return mockEvents;
    }


}
