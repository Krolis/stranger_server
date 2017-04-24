package com.yeahbunny.stranger.server.controller.dto.response;

import java.util.List;

public class MyTestResponse {

    private int id;
    private String content;
    private List<String> list;

    public MyTestResponse(int id, String content) {
        this.content = content;
        this.id = id;
    }

    public MyTestResponse(int id, String content, List<String> list) {
        this.content = content;
        this.id = id;
        this.list = list;
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
}
