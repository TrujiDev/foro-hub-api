package com.trujidev.forohub.foro_hub_api.domain.response;

import java.time.LocalDateTime;

public record ResponseData(
    Long id,
    String author,
    String title,
    LocalDateTime creationDate,
    String message
) {

  public ResponseData (Response response) {
    this(response.getId(), response.getAuthor().getName(), response.getTopic().getTitle(), response.getCreationDate(), response.getMessage());
  }

}
