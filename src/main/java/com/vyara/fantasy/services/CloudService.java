package com.vyara.fantasy.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudService {
    String uploadImage(MultipartFile multipartFile) throws IOException;
}
