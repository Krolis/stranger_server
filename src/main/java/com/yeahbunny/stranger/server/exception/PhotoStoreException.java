package com.yeahbunny.stranger.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Photo store exception")
public class PhotoStoreException extends RuntimeException {
    public PhotoStoreException(String message) {
        super(message);
    }
}
