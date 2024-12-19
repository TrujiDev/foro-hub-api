package com.trujidev.forohub.foro_hub_api.domain.response;

import com.trujidev.forohub.foro_hub_api.domain.topic.Topic;
import com.trujidev.forohub.foro_hub_api.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "response")
@Entity(name = "Response")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String message;

  @ManyToOne
  @JoinColumn(name = "topic_id")
  private Topic topic;
  private LocalDateTime creationDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;

  @Setter
  private Boolean solved;

  public Response(@Valid RegisterResponseData responseData, User author, Topic topic) {
    this.message = responseData.message();
    this.author = author;
    this.topic = topic;
    this.creationDate = LocalDateTime.now();
    this.solved = false;
  }

  public void solve() {
    this.solved = true;
  }

}
