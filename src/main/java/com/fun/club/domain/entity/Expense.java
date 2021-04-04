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
@Table(name = "expense")
@Data

public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "expense_id", unique = true, nullable = false)
	private Long expenseId;

	@Column(name = "expense_amount")
	private Long expenseAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expense_date", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
	private Date expenseDate;

	@Column(length = 200)
	private String reason;

}
