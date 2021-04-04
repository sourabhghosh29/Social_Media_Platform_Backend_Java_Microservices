package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.Event;
import com.querydsl.core.types.Predicate;

@Repository
public interface EventRepository
      extends JpaRepository<Event, Long>, QueryDslPredicateExecutor<Event> {

  @Override
  List<Event> findAll(Predicate predicate);

}