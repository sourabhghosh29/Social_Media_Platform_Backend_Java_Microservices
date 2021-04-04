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
@Table(name = "contribution")
@Data

public class Contribution {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "contribution_amount")
	private Long contributionAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "contribution_date", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
	private Date contributionDate;

	@Column(length = 200)
	private String comment;

}
