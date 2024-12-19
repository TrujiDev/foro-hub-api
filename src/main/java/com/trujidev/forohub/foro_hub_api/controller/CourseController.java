package com.trujidev.forohub.foro_hub_api.controller;

import com.trujidev.forohub.foro_hub_api.domain.course.*;
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
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

  @Autowired
  private RepositoryCourse courseRepository;

  @PostMapping("/register")
  public ResponseEntity<ResponseCourseData> registerCourse(@RequestBody @Valid RegisterCourseData registerCourseData, UriComponentsBuilder uriComponentsBuilder) {
    Course course = courseRepository.save(new Course(registerCourseData));
    ResponseCourseData responseCourseData = new ResponseCourseData(
        course.getId(),
        course.getName(),
        course.getCategory().toString(),
        course.getDescription()
    );
    URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
    return ResponseEntity.created(url).body(responseCourseData);
  }

  @GetMapping("/list")
  public ResponseEntity<Page<ListCourseData>> listCourses(@PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.ok(courseRepository.findAll(pageable).map(ListCourseData::new));
  }

  @GetMapping("/details/{id}")
  public ResponseEntity<ResponseCourseData> courseDetails(@PathVariable Long id) {
    Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    ResponseCourseData courseData = new ResponseCourseData(
        course.getId(),
        course.getName(),
        course.getCategory().toString(),
        course.getDescription()
    );
    return ResponseEntity.ok(courseData);
  }

}
