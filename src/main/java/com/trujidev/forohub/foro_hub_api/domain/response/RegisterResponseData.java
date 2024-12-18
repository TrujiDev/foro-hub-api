package com.trujidev.forohub.foro_hub_api.domain.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterResponseData(
    @NotBlank String message,
    @NotNull Long topicId,
    @NotNull Long userId
) {
}
