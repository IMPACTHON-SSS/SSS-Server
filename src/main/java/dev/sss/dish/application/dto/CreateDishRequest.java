package dev.sss.dish.application.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateDishRequest(@NotBlank String url, String dairy) {}