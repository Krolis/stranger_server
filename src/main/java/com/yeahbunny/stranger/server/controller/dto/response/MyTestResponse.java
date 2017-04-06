package com.yeahbunny.stranger.server.controller.dto.response;


public class MyTestResponse {

    private int id;
    private String content;

    public MyTestResponse(int id, String content) {
        this.content = content;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
