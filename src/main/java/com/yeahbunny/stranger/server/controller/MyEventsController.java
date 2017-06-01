package com.yeahbunny.stranger.server.controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeahbunny.stranger.server.controller.dto.response.StrangersEventListItem;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class MyEventsController {

    @RequestMapping(value = "/user/myEvents", method = RequestMethod.GET)
    @ResponseBody
    public List<StrangersEventListItem> getMyEvents(){
        List<StrangersEventListItem> mockEvents = new ArrayList<>();

        StrangersEventListItem strangersEventMarker1 = new StrangersEventListItem();
        strangersEventMarker1.setId(1);
        strangersEventMarker1.setTitle("Spotkanie zespołu Strangers");
        strangersEventMarker1.setWhere("PK");
        strangersEventMarker1.setDate(new GregorianCalendar(2017, 3,11,7,30));
        mockEvents.add(strangersEventMarker1);

        StrangersEventListItem strangersEventMarker2  = new StrangersEventListItem();
        strangersEventMarker2.setId(2);
        strangersEventMarker2.setTitle("SZOPING");
        strangersEventMarker2.setWhere("galeria krakowska");
        strangersEventMarker2.setDate(new GregorianCalendar(2017, 3,11,7,30));
        mockEvents.add(strangersEventMarker2);


        StrangersEventListItem strangersEventMarker3  = new StrangersEventListItem();
        strangersEventMarker3.setId(2);
        strangersEventMarker3.setTitle("piwko w pijalni");
        strangersEventMarker3.setWhere("pijalnia wódki i piwa");
        strangersEventMarker3.setDate(new GregorianCalendar(2017, 3,11,20,30));
        mockEvents.add(strangersEventMarker3);

        return mockEvents;
    }
}
