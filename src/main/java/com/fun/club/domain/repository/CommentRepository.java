package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fun.club.domain.entity.Comment;
import com.querydsl.core.types.Predicate;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, QueryDslPredicateExecutor<Comment> {

  @Override
  List<Comment> findAll(Predicate predicate);

  List<Comment> findByFeedIdOrderByCommentTimeDesc(Long id);

  List<Comment> findTop3ByFeedIdOrderByCommentTimeDesc(Long feedId);
  
 // List<Comment> fetchAllByfeedIdOrderByCommetTimeDesc(Long feedId);

  @Query("SELECT f FROM Comment f" + " WHERE f.comment!=null AND f.feedId = (:feedId) order by commentTime desc")
  List<Comment> fetchAllcommentsByfeedId(@Param("feedId") Long feedId);
  
  @Modifying
  @Transactional
  @Query("DELETE FROM Comment " + " WHERE feedId = (:feedId)")
  void deleteAllCommentByfeedId(@Param("feedId") Long feedId);

}
