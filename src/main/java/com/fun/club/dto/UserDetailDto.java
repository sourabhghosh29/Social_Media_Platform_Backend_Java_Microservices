package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDetailDto {

	private String userName;
	private String employeeName;
	private String role;
	private boolean isDefaultPasswordReset;

}