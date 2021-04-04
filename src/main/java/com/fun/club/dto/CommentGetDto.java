package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CommentGetDto {
	
	private Long id;
	private Long feedId;
	private String employeeName;
	private String comment;
	private String commentTime;

}