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
@Table(name = "timeline_feed")
@Data

public class Timeline {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name= "employee_id")
	private String employeeId;

	@Column(length = 1000)
	private String suggestion;

	@Column
	private byte[] document;

	@Column(name = "like_count")
	private Long likeCount;

	@Column(name = "dislike_count")
	private Long dislikeCount;

	@Column(name = "comment_count")
	private Long commentCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "suggestion_time", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
	private Date suggestionTime;
}
