package dev.sss.auth.application.dto;

public record LoginResponse(String accessToken, java.util.concurrent.atomic.AtomicBoolean isFirstLogin) {}