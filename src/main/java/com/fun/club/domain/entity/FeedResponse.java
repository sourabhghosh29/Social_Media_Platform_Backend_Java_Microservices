package com.fun.club.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "feed_response")
@Data

public class FeedResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "feed_id")
	private Long feedId;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(name = "like_feed", length = 200)
	private boolean likeFeed;

	@Column(name = "dislike_feed", length = 1000)
	private boolean dislikeFeed;

}
