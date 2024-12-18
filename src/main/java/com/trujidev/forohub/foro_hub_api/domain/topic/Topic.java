package com.trujidev.forohub.foro_hub_api.domain.topic;

import com.trujidev.forohub.foro_hub_api.domain.course.Course;
import com.trujidev.forohub.foro_hub_api.domain.response.Response;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topic")
@Entity(name = "Topic")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String message;

  @Setter
  private LocalDateTime creationDate;

  private Status status;
  private Boolean active;

  @ManyToOne
  @JoinColumn(name = "course_id")
  private Course course;

  @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Response> responses = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;

  public Topic (RegisterTopicData topicData, User user, Course course) {
    this.title = topicData.title();
    this.message = topicData.message();
    this.creationDate = LocalDateTime.now();
    this.status = Status.NOT_ANSWERED;
    this.active = true;
    this.author = user;
    this.course = course;
  }

  public void deactivateTopic() {
    this.active = false;
  }

  public void updateTopic(UpdateTopicData topicData) {
    if (topicData.message() != null) {
      this.message = topicData.message();
    }
    this.creationDate = LocalDateTime.now();
  }

}
