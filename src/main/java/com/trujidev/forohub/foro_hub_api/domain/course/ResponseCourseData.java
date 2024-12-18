package com.trujidev.forohub.foro_hub_api.domain.course;

import jakarta.validation.constraints.NotBlank;

public record ResponseCourseData(
    Long id,
    @NotBlank String name,
    @NotBlank String category,
    @NotBlank String description
) {
}
