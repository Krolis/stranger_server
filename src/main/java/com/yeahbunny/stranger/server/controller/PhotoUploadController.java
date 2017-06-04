package com.yeahbunny.stranger.server.controller;

import com.yeahbunny.stranger.server.model.User;
import com.yeahbunny.stranger.server.services.PhotoService;
import com.yeahbunny.stranger.server.services.PrincipalProvider;
import com.yeahbunny.stranger.server.services.UserService;
import com.yeahbunny.stranger.server.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@Controller
public class PhotoUploadController {

    private static final Logger LOG = LoggerFactory.getLogger(PhotoUploadController.class);

    @Inject
    private PrincipalProvider principalProvider;

    @Inject
    private PhotoService photoService;

    @Inject
    private UserService userService;

    @RequestMapping(value = "/photo/upload",method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "photo") MultipartFile photo) {
        Long userId = AuthUtils.getAuthenticatedUserId();
        String photoUrl = photoService.saveFile(photo, userId);
        User user = userService.findUserById(userId);
        user.setPhotoUrl(photoUrl);
        return ResponseEntity.ok(photoUrl);
    }
}
