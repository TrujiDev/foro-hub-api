package com.trujidev.forohub.foro_hub_api.controller;

import com.trujidev.forohub.foro_hub_api.domain.user.*;
import com.trujidev.forohub.foro_hub_api.infrastructure.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class UserController {

  @Autowired
  private RepositoryUser userRepository;

  @GetMapping("/signup")
  public ResponseEntity<UserResponseData> signUp(@RequestBody @Valid RegisterUserData registerUserData, UriComponentsBuilder uriComponentsBuilder) {
    User user = userRepository.save(new User(registerUserData));
    UserResponseData userResponseData = new UserResponseData(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getProfile().toString()
    );
    URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
    return ResponseEntity.created(url).body(userResponseData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<ListUserData>> list(@PageableDefault(size = 10)Pageable pageable) {
    return ResponseEntity.ok(userRepository.findAll(pageable).map(ListUserData::new));
  }

  @GetMapping("/details/{id}")
  public ResponseEntity<UserResponseData> details(@PathVariable Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    UserResponseData userResponseData = new UserResponseData(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getProfile().toString()
    );
    return ResponseEntity.ok(userResponseData);
  }
}
