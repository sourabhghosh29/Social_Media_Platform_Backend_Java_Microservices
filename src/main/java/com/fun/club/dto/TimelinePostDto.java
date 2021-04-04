package com.fun.club.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TimelinePostDto {

  private Long id;
  private String employeeId;
  private String employeeName;
  private String suggestion;
  private byte[] document;
  private String suggestionTime;

}