package dev.sss.image.application;

import dev.sss.image.infra.S3Adapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final S3Adapter s3Adapter;

    public String upload(MultipartFile file) {
        return s3Adapter.upload(file);
    }

}