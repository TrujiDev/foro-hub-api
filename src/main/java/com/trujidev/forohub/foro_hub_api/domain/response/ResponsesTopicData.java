package com.trujidev.forohub.foro_hub_api.domain.response;

import java.time.LocalDateTime;

public record ResponsesTopicData(
    String author,
    LocalDateTime creationDate,
    String message
) {

  public ResponsesTopicData(Response response) {
    this(response.getAuthor().getName(), response.getCreationDate(), response.getMessage());
  }

}
