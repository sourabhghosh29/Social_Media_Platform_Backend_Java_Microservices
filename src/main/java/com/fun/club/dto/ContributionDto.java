package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ContributionDto {

	private Long id;
	private String employeeId;
	private String employeeName;
	private Long contributionAmount;
	private String contributionDate;
	private String comment;

}
