package com.yeahbunny.stranger.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by kroli on 02.06.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Photo store exception")
public class LoadPhotoException extends RuntimeException {
    public LoadPhotoException(String message) {
        super(message);
    }
}
