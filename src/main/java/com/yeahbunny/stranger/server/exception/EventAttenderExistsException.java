package com.yeahbunny.stranger.server.exception;

public class EventAttenderExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	
	public EventAttenderExistsException() {
		super();
	}


	public EventAttenderExistsException(String msg) {
		super(msg);
	}

}
