package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FeedResponseDto {

	private Long id;
	private Long feedId;
	private String employeeId;
	private boolean likeFeed;
	private boolean dislikeFeed;
}
