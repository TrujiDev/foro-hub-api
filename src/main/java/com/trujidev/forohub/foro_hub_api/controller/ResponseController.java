package com.trujidev.forohub.foro_hub_api.controller;

import com.trujidev.forohub.foro_hub_api.domain.response.*;
import com.trujidev.forohub.foro_hub_api.domain.topic.RepositoryTopic;
import com.trujidev.forohub.foro_hub_api.domain.topic.Status;
import com.trujidev.forohub.foro_hub_api.domain.topic.Topic;
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
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {

  @Autowired
  private RepositoryResponse responseRepository;

  @Autowired
  private RepositoryUser userRepository;

  @Autowired
  private RepositoryTopic topicRepository;

  @PostMapping("/register")
  public ResponseEntity<ResponseData> registerResponse(@RequestBody @Valid RegisterResponseData registerResponseData, UriComponentsBuilder uriComponentsBuilder){
    User author = userRepository.findById(registerResponseData.userId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    Topic topic = topicRepository.findById(registerResponseData.topicId()).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    Response response = responseRepository.save(new Response(registerResponseData, author, topic));
    ResponseData responseData = new ResponseData(response);
    URI url = uriComponentsBuilder.path("/response/{id}").buildAndExpand(response.getId()).toUri();
    return ResponseEntity.created(url).body(responseData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<ListResponseData>> listResponses(@PageableDefault(size = 5) Pageable pageable) {
    return ResponseEntity.ok(responseRepository.findAll(pageable).map(ListResponseData::new));
  }

  @GetMapping("/details/{id}")
  public ResponseEntity<ResponseData> showResponse(@PathVariable Long id) {
    Response response = responseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Response not found"));
    ResponseData responseData = new ResponseData(response);
    return ResponseEntity.ok(responseData);
  }

  @PutMapping("/solve-mark/{id}/{responseId}")
  @Transactional
  public ResponseEntity<ResponseData> markSolved(@PathVariable Long id, @PathVariable Long responseId) {
    Topic topic = topicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    if (topic.getStatus() == Status.ANSWERED) {
      return ResponseEntity.badRequest().body(null);
    }
    Response response = responseRepository.findById(responseId).orElseThrow(() -> new ResourceNotFoundException("Response not found"));
    response.setSolved(true);
    topic.setStatus(Status.ANSWERED);
    topicRepository.save(topic);
    responseRepository.save(response);
    return ResponseEntity.ok(new ResponseData(response));
  }

}
