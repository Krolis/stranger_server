package com.yeahbunny.stranger.server.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by kroli on 02.06.2017.
 */
public interface PhotoService {
    String saveFile(MultipartFile file, Long userId);

    byte[] load(String fileName);
}
