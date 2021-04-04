package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmployeeDescriptionDto {

	private String employeeName;
	private String description;
	private String contact;

}