package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fun.club.domain.entity.FeedResponse;
import com.querydsl.core.types.Predicate;

@Repository
public interface FeedResponseRepository
      extends JpaRepository<FeedResponse, Long>, QueryDslPredicateExecutor<FeedResponse> {

  @Override
  List<FeedResponse> findAll(Predicate predicate);
  
  List<FeedResponse> findByFeedId(Long feedId);
  
  FeedResponse findByFeedIdAndEmployeeId(Long feedId, String EmployeeId);

  // List<String>findFirst3OrderByCommentDateByFeedId(Long feedId);

  // List<FeedResponse> findTop3ByFeedId(Long feedId);

  // List<String> findCommentByFeedId(Long id);
  
  @Query("SELECT employeeId FROM FeedResponse " + " WHERE likeFeed='true' AND feedId = (:feedId)")
  List<String> fetchlikedataByfeedId(@Param("feedId") Long feedId);
  
  @Query("SELECT employeeId FROM FeedResponse " + " WHERE dislikeFeed='true' AND feedId = (:feedId)")
  List<String> fetchdislikedataByfeedId(@Param("feedId") Long feedId);

  @Query("SELECT count(*) FROM FeedResponse " + " WHERE likeFeed='true' AND feedId = (:feedId)")
  Long fetchlikecountByfeedId(@Param("feedId") Long feedId);

  @Query("SELECT count(*) FROM FeedResponse " + " WHERE dislikeFeed='true' AND feedId = (:feedId)")
  Long fetchdislikecountByfeedId(@Param("feedId") Long feedId);
  
  @Modifying
  @Transactional
  @Query("DELETE FROM FeedResponse " + " WHERE feedId = (:feedId)")
  void deleteAllFeedsByfeedId(@Param("feedId") Long feedId);

  // @Query("SELECT f FROM FeedResponse f"
  // + " WHERE f.comment!=null AND f.feedId = (:feedId) order by commentTime")
  // List<FeedResponse> fetchAllcommentsByfeedId(@Param("feedId") Long feedId);

  // @Query(nativeQuery = true, value="SELECT TOP 3 f FROM FeedResponse f"
  // + " WHERE f.comment!=null AND f.feedId = (:feedId) order by commentTime")
  // List<FeedResponse> fetchFirst3CommentsByfeedId(@Param("feedId") Long feedId);
  //

}