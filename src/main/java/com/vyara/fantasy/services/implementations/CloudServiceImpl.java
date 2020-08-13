package com.vyara.fantasy.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vyara.fantasy.services.CloudService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

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

    @Override
    public void deleteImageByUrl(String url) {
        try {
            URL imageUrl = new URL(url);
            getClodinaryId(imageUrl).ifPresent(this::deleteImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<String> getClodinaryId(URL url) {
        String host = url.getHost();
        String path = url.getPath();
        if (!host.equals("res.cloudinary.com")) return Optional.empty();
        String id = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        return Optional.of(id);
    }

    private void deleteImage(String publicId) {
        try {
            cloudinary.api().deleteResources(Arrays.asList(publicId), ObjectUtils.emptyMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
