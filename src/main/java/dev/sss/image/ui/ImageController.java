package dev.sss.image.ui;

import dev.sss.image.application.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Image API")
@RestController
@RequestMapping(value = "/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(description = "이미지 업로드")
    @PostMapping
    public String upload( @RequestPart("file")MultipartFile file) {
        return imageService.upload(file);
    }

}