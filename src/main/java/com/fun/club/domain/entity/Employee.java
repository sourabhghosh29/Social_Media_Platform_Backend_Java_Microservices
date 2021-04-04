package com.fun.club.domain.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "employee_id")
  private String employeeId;

  @Column(name = "employee_name", length = 100)
  private String employeeName;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
  private Date dob;

  @Column
  private Long contact;

  @Column(name = "cake_flavour", length = 100)
  private String cakeFlavour;

  @Column(length = 500)
  private String initiatives;

  @Column(length = 500)
  private String interests;

}
