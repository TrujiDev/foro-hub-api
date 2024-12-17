package com.trujidev.forohub.foro_hub_api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "Response")
@Table(name = "responses")
public class Response {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;
  private LocalDateTime creationDate = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User author;

  @ManyToOne
  @JoinColumn(name = "topic_id")
  private Topic topic;

}
