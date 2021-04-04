package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.MonthlyData;

@Repository
public interface MonthWiseDataRepository
      extends JpaRepository<MonthlyData, Long>, QueryDslPredicateExecutor<MonthlyData> {

  @Query(value = "SELECT view_id,month_id,month_name,years,contribution_amount,expense_amount FROM month_wise_data", nativeQuery = true)
  List<MonthlyData> fetchGraphData();

}