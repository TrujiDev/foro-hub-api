package com.trujidev.forohub.foro_hub_api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicData(
    @NotNull Long id,
    @NotBlank String message
) {

  public UpdateTopicData(Topic topic) {
    this(topic.getId(), topic.getMessage());
  }

}
