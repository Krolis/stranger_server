package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.response.EventMessage;
import com.yeahbunny.stranger.server.controller.dto.response.LatLng;
import com.yeahbunny.stranger.server.controller.dto.response.StrangerUser;
import com.yeahbunny.stranger.server.controller.dto.response.StrangersEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class EventController {

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    @ResponseBody
    public StrangersEvent getEvent(@RequestParam long id){
        StrangersEvent event= new StrangersEvent();
        event.setId(id);
        event.setPosition(new LatLng(50.064375, 19.939535));
        event.setTitle("piwko w pijalni");
        event.setWhere("Pijalnia");
        event.setDetails("Wieczorem na piwko ?? :>> mam wolny wieczór pomyślałem że warto byłoby poznac nowych ludzi, zapraszam! :D");
        event.setDate(new GregorianCalendar(2017, 3,11,20,30));

        //zalozyciel eventa
        StrangerUser eventOwner = new StrangerUser();
        eventOwner.setId(3);
        eventOwner.setNick("Mateusz69");
        eventOwner.setAge(52);
        eventOwner.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-0/s160x160/14117713_1040333702710708_7798235088864788788_n.jpg?oh=e309c504c7786a3f94e29b74da172ec5&oe=59B5945D");
        eventOwner.setFemale(false);
        event.setOwner(eventOwner);

        //fejkowi uzytkownicy, beda pobrani razem z eventem z serwera
        List<StrangerUser> attenders = new ArrayList<>();
        StrangerUser attender1 = new StrangerUser();
        attender1.setId(1);
        attender1.setNick("Dominik");
        attender1.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-9/10702177_748193598553243_7198944722218801004_n.jpg?oh=65c7acd8b3193acf7a55aa7ff005cf07&oe=59ABAFF6");
        attenders.add(attender1);
        StrangerUser attender3 = new StrangerUser();
        attender3.setId(2);
        attender3.setNick("Oldzi");
        attender3.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-9/13494810_1424239834269662_3094082329629943028_n.jpg?oh=d714cfbd86c4ea4ee672cd54f7b2187c&oe=59A94714");
        attenders.add(attender3);
        StrangerUser attender4 = new StrangerUser();
        attender4.setId(2);
        attender4.setNick("Linczi");
        attenders.add(attender4);
        StrangerUser attender5 = new StrangerUser();
        attender5.setId(2);
        attender5.setNick("KordiXXX");
        attenders.add(attender5);
        StrangerUser attender6 = new StrangerUser();
        attender6.setId(2);
        attender6.setNick("Kapeć");
        attenders.add(attender6);
        StrangerUser attender7 = new StrangerUser();
        attender7.setId(2);
        attender7.setNick("Macek");
        attenders.add(attender7);
        StrangerUser attender8 = new StrangerUser();
        attender8.setId(2);
        attender8.setNick("Kasia");
        attenders.add(attender8);
        StrangerUser attender2 = new StrangerUser();
        attender2.setId(2);
        attender2.setNick("Kaziu102");
        attenders.add(attender2);
        event.setAttenders(attenders);

        //fejkowe wiadomosci, tez beda pobrane z serwera razem z eventem
        List<EventMessage> messages = new ArrayList<>();
        EventMessage message = new EventMessage();
        message.setUser(attender1);
        message.setContent("PEWNIE ŻE WPADNĘ! później klabbing??");
        messages.add(message);
        event.setMessages(messages);

        return event;
    }
}
