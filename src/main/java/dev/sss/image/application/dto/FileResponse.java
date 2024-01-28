package dev.sss.image.application.dto;

public record FileResponse(
        Long id,
        String url
) {
    public static FileResponse of(Long id, String url) {
        return new FileResponse(id, url);
    }
}