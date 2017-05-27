package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.response.EventType;
import com.yeahbunny.stranger.server.controller.dto.response.LatLng;
import com.yeahbunny.stranger.server.controller.dto.response.StrangersEventMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class MarkerController {

    private static final Logger LOG = LoggerFactory.getLogger(MarkerController.class);

    @RequestMapping(value = "markers", method = RequestMethod.GET)
    @ResponseBody
    public List<StrangersEventMarker> getEvents(@RequestParam double northeast_lat,
                                                @RequestParam double northeast_lng,
                                                @RequestParam double southwest_lat,
                                                @RequestParam double southwest_lng){

        List<StrangersEventMarker> mockEvents = new ArrayList<>();

        StrangersEventMarker strangersEventMarker1 = new StrangersEventMarker();
        strangersEventMarker1.setPosition(new LatLng((northeast_lat + southwest_lat) /2, (northeast_lng + southwest_lng)/2));
        strangersEventMarker1.setTitle("Spotkanie zespołu Strangers");
        strangersEventMarker1.setDetails("Stwórz z nami społeczność!");
        strangersEventMarker1.setDate(new GregorianCalendar(2017, 3,11,7,30));
        strangersEventMarker1.setType(EventType.NOW);
        mockEvents.add(strangersEventMarker1);

        StrangersEventMarker strangersEventMarker2 = new StrangersEventMarker();
        strangersEventMarker2.setPosition(new LatLng(0.0033 + (northeast_lat + southwest_lat) /2, 0.005 + (northeast_lng + southwest_lng)/2));
        strangersEventMarker2.setTitle("SZOPING");
        strangersEventMarker2.setDetails("zapraszam do galerii na małe zakupy albo kawe i ciastko :D");
        strangersEventMarker2.setDate(new GregorianCalendar(2017, 3,11,7,30));
        strangersEventMarker2.setType(EventType.NOW);
        mockEvents.add(strangersEventMarker2);


        StrangersEventMarker strangersEventMarker3 = new StrangersEventMarker();
        strangersEventMarker3.setPosition(new LatLng(50.064375, 19.939535));//rynek
        strangersEventMarker3.setTitle("piwko w pijalni");
        strangersEventMarker3.setDetails("Wieczorem na piwko ?? :>>");
        strangersEventMarker3.setDate(new GregorianCalendar(2017, 3,11,20,30));
        strangersEventMarker3.setType(EventType.FUTURE);
        mockEvents.add(strangersEventMarker3);


        return mockEvents;
    }


}
