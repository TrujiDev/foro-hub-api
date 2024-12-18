package com.trujidev.forohub.foro_hub_api.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "course")
@Entity(name = "Course")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @Enumerated(EnumType.STRING)
  private Category category;
  private String description;

  public Course(RegisterCourseData registerCourseData) {
    this.name = registerCourseData.name();
    this.category = registerCourseData.category();
    this.description = registerCourseData.description();
  }

}
