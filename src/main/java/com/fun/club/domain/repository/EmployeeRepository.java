package com.fun.club.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fun.club.domain.entity.Employee;
import com.querydsl.core.types.Predicate;

@Repository
public interface EmployeeRepository
      extends JpaRepository<Employee, String>, QueryDslPredicateExecutor<com.fun.club.domain.entity.Employee> {

  @Override
  List<Employee> findAll(Predicate predicate);

  Employee findByEmployeeId(String employeeId);
  
  Employee findByEmployeeName(String employeeName);
  
  @Query(value= "SELECT * FROM employee " + " WHERE employee_name like %:employeeName%",nativeQuery = true)
  Employee fetchEmployeeDetailByName(@Param("employeeName") String employeeName);

  Employee findById(Long id);
  
  List<Employee> findByEmployeeNameIgnoreCaseContaining(String employeeName);
  
  @Query(value= "SELECT * FROM employee " + " WHERE employee_name not like 'Admin'",nativeQuery = true)
  List<Employee> findAllEmployees();
}
