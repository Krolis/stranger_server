package com.yeahbunny.stranger.server.model.notifications;

public class FewEventsMsgNotificationContent implements NotificationContent{

    private String title;
    private String[] events;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        if(events.length>=1){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(events[0]);

            for(int i = 1; i<events.length; i++){
                stringBuilder.append(", ");
                stringBuilder.append(events[i]);
            }

            return stringBuilder.toString();
        }else{
            return "";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEvents(String[] events) {
        this.events = events;
    }

    public String[] getEvents() {
        return events;
    }
}
