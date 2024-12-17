package com.trujidev.forohub.foro_hub_api.web;

import com.trujidev.forohub.foro_hub_api.application.TopicService;
import com.trujidev.forohub.foro_hub_api.domain.entities.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@RestController
@RequestMapping("/api/topics")
public class TopicController {
  private final TopicService topicService;

  @GetMapping
  public ResponseEntity<List<Topic>> getAllTopics() {
    return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
    return new ResponseEntity<>(topicService.getTopicById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
    return new ResponseEntity<>(topicService.createTopic(topic), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
    return new ResponseEntity<>(topicService.updateTopic(id, topic), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
    topicService.deleteTopic(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
