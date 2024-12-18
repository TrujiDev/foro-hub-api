package com.trujidev.forohub.foro_hub_api.domain.response;

import java.time.LocalDateTime;

public record ListResponseData(
    Long id,
    String title,
    String author,
    LocalDateTime creationDate
) {

  public ListResponseData (Response response) {
    this(response.getId(), response.getTopic().getTitle(), response.getAuthor().getName(), response.getCreationDate());
  }

}
