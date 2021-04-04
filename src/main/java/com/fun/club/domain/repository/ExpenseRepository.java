package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.Expense;
import com.fun.club.domain.entity.Timeline;
import com.querydsl.core.types.Predicate;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String>, QueryDslPredicateExecutor<Expense> {

  @Override
  List<Expense> findAll(Predicate predicate);

  List<Expense> findAllByOrderByExpenseDateDesc();

  Expense findByExpenseId(Long expenseId);
  
  @Query(value = "select sum(expense_amount) from Expense;", nativeQuery = true)
  Long fetchTotalExpense();
}