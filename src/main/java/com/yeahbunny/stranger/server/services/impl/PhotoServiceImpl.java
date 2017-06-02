package com.yeahbunny.stranger.server.services.impl;

import com.yeahbunny.stranger.server.controller.PhotoUploadController;
import com.yeahbunny.stranger.server.exception.LoadPhotoException;
import com.yeahbunny.stranger.server.exception.PhotoStoreException;
import com.yeahbunny.stranger.server.services.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;

@Service
public class PhotoServiceImpl implements PhotoService{

    private static final Logger LOG = LoggerFactory.getLogger(PhotoServiceImpl.class);

    @Value("${app.photo.root_directory_path}")
    private String rootDirectoryPath;
    @Value("${app.photo.directory_separator}")
    private String directorySeparator;
    @Value("${app.photo.image_format}")
    private String imageFormat;
    @Value("${app.photo.file_data_separator}")
    private String fileDataSeparator;

    @Value("${app.backend_host}")
    private String backendHost;
    @Value("${app.photo.endpoint_path}")
    private String photoEndpointPath;


    @Override
    public String saveFile(MultipartFile file, Long userId) {
        String fileName = createPhotoName(userId);
        String serverPath = storeFile(file, fileName);
        String externalPhotoUrl = createExternalUrl(fileName);
        LOG.debug("File {} stored {} url {}", fileName, serverPath, externalPhotoUrl);
        return externalPhotoUrl;
    }

    private String createPhotoName(Long userId) {
        ZonedDateTime storeFileDateTime = ZonedDateTime.now();
        StringBuilder sb = new StringBuilder();

        sb.append(userId);
        sb.append(fileDataSeparator);
        sb.append(storeFileDateTime.toInstant().toEpochMilli());
        sb.append(fileDataSeparator);
        sb.append("sep");
        sb.append(imageFormat);

        return sb.toString();
    }

    private String storeFile(MultipartFile file, String fileName) {
        File directory = new File(rootDirectoryPath);
        handleDirectoryOpenError(directory);
        String serverPath = createServerFilePath(fileName);
        LOG.info("Storing file: {}", serverPath);

        try(FileOutputStream stream = new FileOutputStream(serverPath)) {
            stream.write(file.getBytes());
        }catch (SecurityException e){
            e.printStackTrace();
            throw new PhotoStoreException("STORE_SECURITY_EXCEPTION");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new PhotoStoreException("FILE_NOT_FOUND_EXCEPTION");
        }catch (IOException e){
            e.printStackTrace();
            throw new PhotoStoreException("IO_EXCEPTION");
        }

        return serverPath;
    }

    private String createExternalUrl(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://");
        stringBuilder.append(backendHost);
        stringBuilder.append(photoEndpointPath);
        stringBuilder.append(fileName);

        return stringBuilder.toString();
    }

    private String createServerFilePath(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rootDirectoryPath);
        stringBuilder.append(directorySeparator);
        stringBuilder.append(fileName);
        return stringBuilder.toString();
    }

    private void handleDirectoryOpenError(File directory) {
        if (!directory.exists()){
            try{
                boolean result = directory.mkdirs();
                if(!result){
                    LOG.error("directory.mkdirs() == false");
                    throw new PhotoStoreException("DIRECTORIES_CREATE_EXCEPTION");
                }
                LOG.debug("directories {}", rootDirectoryPath);

            }catch (SecurityException e){
                e.printStackTrace();
                throw new PhotoStoreException("SECURITY_EXCEPTION");
            }

        }
    }

    @Override
    public byte[] load(String fileName) {
        String serverPath = createServerFilePath(fileName);
        Path path = Paths.get(serverPath);

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LoadPhotoException(fileName + "does not exist");
        }

    }
}
