package com.trujidev.forohub.foro_hub_api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryTopic extends JpaRepository<Topic, Long> {
  Page<Topic> findByActiveTrue(Pageable pageable);

  @Query("SELECT COUNT(t) > 0 FROM Topic t WHERE t.title = :title AND t.message = :message")
  boolean existsByTitleAndMessage(@Param("title") String title, @Param("message") String message);

  @Query("SELECT t FROM Topic t ORDER BY t.course.name ASC")
  Page<Topic> findByCourseAsc(Pageable pageable);

  @Query("SELECT t FROM Topic t ORDER BY t.creationDate DESC")
  Page<Topic> findAllOrderByCreationDate(Pageable pageable);
}
