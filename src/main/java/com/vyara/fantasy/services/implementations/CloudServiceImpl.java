package com.vyara.fantasy.services.implementations;

import com.cloudinary.Cloudinary;
import com.vyara.fantasy.services.CloudService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
@Getter
@AllArgsConstructor
public class CloudServiceImpl implements CloudService {
    private final Cloudinary cloudinary;


    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file= File.createTempFile("temp-file",multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return cloudinary.uploader().upload(file,new HashMap()).get("url").toString();
    }

}
