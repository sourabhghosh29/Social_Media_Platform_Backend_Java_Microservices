package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.Timeline;
import com.querydsl.core.types.Predicate;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long>, QueryDslPredicateExecutor<Timeline> {

  @Override
  List<Timeline> findAll(Predicate predicate);

  List<Timeline> findAllByOrderByLikeCountDesc();

  Timeline findById(Long id);

  // List<Timeline> findTOP5OrderById();

  @Query(value = "select * from timeline_feed order by suggestion_time desc Limit 5;", nativeQuery = true)
  List<Timeline> fetchTop5suggestion();

}