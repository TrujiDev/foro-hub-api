package com.trujidev.forohub.foro_hub_api.domain.response;

import java.time.LocalDateTime;

public record ResponseTopicData(
    String author,
    LocalDateTime creationDate,
    String message
) {

  public ResponseTopicData (Response response) {
    this(response.getAuthor().getName(), response.getCreationDate(), response.getMessage());
  }

}
