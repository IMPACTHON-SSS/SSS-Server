package dev.sss.auth.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank String token) {}