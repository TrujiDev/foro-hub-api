package com.trujidev.forohub.foro_hub_api.domain.topic;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ResponseTopicData(
    Long id,
    String title,
    String course,
    String author,
    LocalDateTime creationDate,
    Status status,
    String message,
    List<ResponseTopicData> responses
) {

  public ResponseTopicData(Topic topic) {
    this(topic.getId(), topic.getTitle(), topic.getCourse().getName(), topic.getAuthor().getName(),
        topic.getCreationDate(), topic.getStatus(), topic.getMessage(), topic.getResponses().stream()
            .map(ResponseTopicData::new)
            .collect(Collectors.toList()));
  }

}
