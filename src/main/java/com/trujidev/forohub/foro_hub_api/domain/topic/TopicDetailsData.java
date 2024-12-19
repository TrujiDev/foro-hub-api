package com.trujidev.forohub.foro_hub_api.domain.topic;

import com.trujidev.forohub.foro_hub_api.domain.course.ResponseCourseData;
import com.trujidev.forohub.foro_hub_api.domain.response.RegisterResponseData;
import com.trujidev.forohub.foro_hub_api.domain.user.UserResponseData;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDetailsData(
    Long id,
    String title,
    ResponseCourseData course,
    Status status,
    LocalDateTime creationDate,
    UserResponseData author,
    String message,
    List<RegisterResponseData> responses
) {
}
