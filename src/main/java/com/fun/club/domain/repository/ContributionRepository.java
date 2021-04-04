package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.Contribution;
import com.querydsl.core.types.Predicate;

@Repository
public interface ContributionRepository
      extends JpaRepository<Contribution, String>, QueryDslPredicateExecutor<Contribution> {

  @Override
  List<Contribution> findAll(Predicate predicate);

  List<Contribution> findAllByOrderByContributionDateDesc();

  Contribution findById(Long contributionId);

  //	@Query("SELECT (c.contribution_date,'Mon')," + "  (year from c.contribution_date),"
  //			+ "    sum(\"c.contribution_amount\")," + "    sum(\"e.expense_amount\")FROM contribution c, expense e "
  //			+ "group by 1,2 " + "order by 2 DESC")
  //	List<GraphDataDto> fetchGraphData();

  @Query(value = "select sum(contribution_amount) from contribution;", nativeQuery = true)
  Long fetchTotalContribution();
}
