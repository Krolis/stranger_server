package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.controller.dto.response.StrangerUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kroli on 27.05.2017.
 */
@Controller
public class MyUserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public StrangerUser getMyUser(){
        StrangerUser eventOwner = new StrangerUser();
        eventOwner.setId(3);
        eventOwner.setNick("Mateusz69");
        eventOwner.setAge(52);
        eventOwner.setPhotoUrl("https://scontent.fwaw3-1.fna.fbcdn.net/v/t1.0-0/s160x160/14117713_1040333702710708_7798235088864788788_n.jpg?oh=e309c504c7786a3f94e29b74da172ec5&oe=59B5945D");
        eventOwner.setFemale(false);
        return eventOwner;
    }
}
