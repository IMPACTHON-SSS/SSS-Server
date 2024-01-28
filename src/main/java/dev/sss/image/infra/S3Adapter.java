package dev.sss.image.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class S3Adapter {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public String upload(final MultipartFile multipartFile) {
        final String fileName = getFileName(multipartFile.getName());

        PutObjectRequest request = getRequest(multipartFile, fileName);

        amazonS3.putObject(request);

        return getUrl(fileName);
    }

    private String getFileName(final String fileName) {
        return s3Properties.getBucket() + "/" + UUID.randomUUID() + fileName;
    }

    private String getUrl(final String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private PutObjectRequest getRequest(final MultipartFile multipartFile, final String fileName) {
        try {
            return new PutObjectRequest(s3Properties.getBucket(), fileName, multipartFile.getInputStream(),
                    getMetadata(multipartFile)).withCannedAcl(CannedAccessControlList.PublicRead);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    private ObjectMetadata getMetadata(final MultipartFile multipartFile) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        return objectMetadata;
    }

}