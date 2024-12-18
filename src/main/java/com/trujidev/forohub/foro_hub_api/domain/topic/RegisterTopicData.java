package com.trujidev.forohub.foro_hub_api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterTopicData(
    @NotBlank String title,
    @NotBlank String message,
    @NotNull Long courseId,
    @NotNull Long userId
) {
}
