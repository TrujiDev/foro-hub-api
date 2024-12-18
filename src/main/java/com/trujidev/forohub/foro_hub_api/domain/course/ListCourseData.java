package com.trujidev.forohub.foro_hub_api.domain.course;

public record ListCourseData(
    Long id,
    String name,
    String category
) {

  public ListCourseData(Course course) {
    this(course.getId(), course.getName(), course.getCategory().toString());
  }

}
