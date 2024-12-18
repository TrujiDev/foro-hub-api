package com.trujidev.forohub.foro_hub_api.domain.topic;

import java.time.LocalDateTime;

public record ListTopicData(
    Long id,
    String title,
    String course,
    String author,
    LocalDateTime creationDate,
    Status status,
    Integer numberOfResponses
) {

  public ListTopicData(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getCourse().getName(), topic.getAuthor().getName(),
        topic.getCreationDate(), topic.getStatus(), topic.getResponses().size());
  }

}
