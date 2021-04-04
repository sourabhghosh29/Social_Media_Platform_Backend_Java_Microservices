package com.fun.club.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "month_wise_data")
@Data
public class MonthlyData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "view_id", unique = true, nullable = false)
	private Long view_id;
	
	@Column(name = "month_id")
	private Long month_id;;

	@Column(name = "month_name")
	private String month_name;

	@Column(name = "years",columnDefinition="Decimal(10)")
	private Double years;;

	@Column(name = "contribution_amount", columnDefinition = "NUMERIC(19,0)")
	private Long contribution_amount;

	@Column(name = "expense_amount", columnDefinition = "NUMERIC(19,0)")
	private Long expense_amount;
}
