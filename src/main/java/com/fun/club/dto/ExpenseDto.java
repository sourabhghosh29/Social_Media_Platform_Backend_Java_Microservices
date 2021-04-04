package com.fun.club.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExpenseDto {
  private Long expenseId;
  private Long expenseAmount;
  private String expenseDate;
  private String reason;

}
