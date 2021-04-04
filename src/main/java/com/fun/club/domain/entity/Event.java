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
@Table(name = "events")
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "event_name", length = 200)
	private String eventName;

	@Column(name = "employee_id")
	private String employeeId;

	@Column(length = 1000)
	private String description;

	@Column(name = "organizer_id")
	private Long organizerId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "event_date", columnDefinition = "TIMESTAMP WITHOUT TIMEZONE")
	private Date eventDate;

	@Column(name = "event_document")
	private byte[] eventDocument;

}
