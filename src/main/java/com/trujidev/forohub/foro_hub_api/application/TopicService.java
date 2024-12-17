package com.trujidev.forohub.foro_hub_api.application;

import com.trujidev.forohub.foro_hub_api.domain.entities.Topic;
import com.trujidev.forohub.foro_hub_api.infrastructure.database.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Service
public class TopicService {
  private final TopicRepository topicRepository;

  public List<Topic> getAllTopics() {
    return topicRepository.findAll();
  }

  public Topic getTopicById(Long id) {
    return topicRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Topic not found"));
  }

  public Topic createTopic(Topic topic) {
    return topicRepository.save(topic);
  }

  public Topic updateTopic(Long id, Topic updatedTopic) {
    Topic topic = getTopicById(id);
    topic.setTitle(updatedTopic.getTitle());
    topic.setMessage(updatedTopic.getMessage());
    topic.setStatus(updatedTopic.getStatus());
    return topicRepository.save(topic);
  }

  public void deleteTopic(Long id) {
    topicRepository.deleteById(id);
  }
}
