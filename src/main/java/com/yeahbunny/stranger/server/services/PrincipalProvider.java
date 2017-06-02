package com.yeahbunny.stranger.server.services;

/**
 * Created by kroli on 02.06.2017.
 */
public interface PrincipalProvider {
    Long getUserId();

    String getUsername();
}
