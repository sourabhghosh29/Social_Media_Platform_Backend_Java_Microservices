package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class EmployeeDto {

	private Long id;
	private String employeeId;
	private String employeeName;
	private String dob;
	private Long contact;
	private String cakeFlavour;
	private String initiatives;
	private String interests;
	private String suggestions;

}
