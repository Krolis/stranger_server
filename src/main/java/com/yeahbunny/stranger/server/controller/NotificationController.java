package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.model.notifications.NotificationType;
import com.yeahbunny.stranger.server.model.notifications.OneMsgNotificationContent;
import com.yeahbunny.stranger.server.model.notifications.StrangerNotification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NotificationController {

    @RequestMapping("/user/notifications")
    @ResponseBody
    public List<StrangerNotification<?>> getNotifications(){
        List<StrangerNotification<?>> result = new ArrayList<>();
        StrangerNotification oneMsgNotification = new StrangerNotification();
        oneMsgNotification.setNotificationType(NotificationType.ONE_EVENT_MSG);
        OneMsgNotificationContent content = new OneMsgNotificationContent();
        content.setEventId(new Long(3));
        content.setEventName("nazwa eventu");
        content.setMessageContent("wiadomosc sialalalallalalalal");
        oneMsgNotification.setNotificationContent(content);

        result.add(oneMsgNotification);

        return result;
    }
}
