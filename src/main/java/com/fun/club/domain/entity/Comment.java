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
@Table(name = "comment")
@Data

public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "feed_id")
	private Long feedId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(length = 500)
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_time", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
	private Date commentTime;

}

