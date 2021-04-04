package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GraphDataDto {
	
	private String month_year;
	private Long contribution_amount;
	private Long expense_amount;
}