package com.trujidev.forohub.foro_hub_api.controller;

import com.trujidev.forohub.foro_hub_api.domain.course.Course;
import com.trujidev.forohub.foro_hub_api.domain.course.RepositoryCourse;
import com.trujidev.forohub.foro_hub_api.domain.response.RepositoryResponse;
import com.trujidev.forohub.foro_hub_api.domain.response.Response;
import com.trujidev.forohub.foro_hub_api.domain.topic.*;
import com.trujidev.forohub.foro_hub_api.domain.user.RepositoryUser;
import com.trujidev.forohub.foro_hub_api.domain.user.User;
import com.trujidev.forohub.foro_hub_api.infrastructure.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

  @Autowired
  private RepositoryTopic topicRepository;

  @Autowired
  private RepositoryUser userRepository;

  @Autowired
  private RepositoryCourse courseRepository;

  @Autowired
  private RepositoryResponse responseRepository;

  @PostMapping("/register")
  public ResponseEntity<ResponseTopicData> registerTopic(@RequestBody @Valid RegisterTopicData registerTopicData, UriComponentsBuilder uriComponentsBuilder){
    if (topicRepository.existsByTitleAndMessage(registerTopicData.title(), registerTopicData.message())) {
      return ResponseEntity.badRequest().body(null);
    }
    User author = userRepository.findById(registerTopicData.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Course course = courseRepository.findById(registerTopicData.courseId()).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    Topic topic = new Topic(registerTopicData, author, course);
    topic = topicRepository.save(topic);
    ResponseTopicData responseTopicData = new ResponseTopicData(topic);
    URI url = uriComponentsBuilder.path("topics/{id}").buildAndExpand(topic.getId()).toUri();
    return ResponseEntity.created(url).body(responseTopicData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<ListTopicData>> listTopics(@PageableDefault(size = 10) Pageable pageable){
    return ResponseEntity.ok(topicRepository.findByActiveTrue(pageable).map(ListTopicData::new));
  }

  @GetMapping("/list/courses")
  public ResponseEntity<Page<ListTopicData>> listCourseTopics(@PageableDefault(size = 10) Pageable pageable) {
    Page<Topic> topic = topicRepository.findByCourseAsc(pageable);
    Page<ListTopicData> listTopicData = topic.map(ListTopicData::new);
    return ResponseEntity.ok(listTopicData);
  }

  @GetMapping("/list/date")
  public ResponseEntity<Page<ListTopicData>> listTopicDate(@PageableDefault(size = 10) Pageable pageable) {
    return ResponseEntity.ok(topicRepository.findAllOrderByCreationDate(pageable).map(ListTopicData::new));
  }

  @GetMapping("/detalles/{id}")
  public ResponseEntity<ResponseTopicData> returnTopic(@PathVariable Long id) {
    Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    ResponseTopicData responseTopicData = new ResponseTopicData(topic);
    return ResponseEntity.ok(responseTopicData);
  }

  @DeleteMapping("/delete/{id}")
  @Transactional
  public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
    Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    topic.deactivateTopic();
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/update/{id}")
  @Transactional
  public ResponseEntity<ResponseTopicData> updateTopic(@PathVariable Long id, @RequestBody @Valid UpdateTopicData updateTopicData) {
    Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    topic.updateTopic(updateTopicData);
    return ResponseEntity.ok(new ResponseTopicData(topic));
  }

  @PutMapping("/solved-response/{id}/{responseId}")
  @Transactional
  public ResponseEntity<ResponseTopicData> markSolved(@PathVariable Long id, @PathVariable Long responseId) {
    Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    Response response = responseRepository.findById(responseId).orElseThrow(() -> new ResourceNotFoundException("Response not found"));
    if (topic.getStatus() == Status.ANSWERED) {
      return ResponseEntity.badRequest().body(null);
    }
    response.setSolved(true);
    topic.setStatus(Status.ANSWERED);
    topicRepository.save(topic);
    responseRepository.save(response);
    ResponseTopicData responseTopicData = new ResponseTopicData(topic);
    return ResponseEntity.ok(responseTopicData);
  }

}
