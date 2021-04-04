package com.fun.club.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.club.domain.entity.Expense;
import com.fun.club.domain.repository.ExpenseRepository;
import com.fun.club.dto.ExpenseDto;
import com.fun.club.utils.DateUtils;
import com.fun.club.web.exception.ValidationException;

@Service
public class ExpenseService {
  private ExpenseRepository expenseRepository;
  private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseService.class);

  @Autowired
  public ExpenseService(final ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  /**
   * To get expense details of funclub
   * 
   * @return expense List
   * @throws ParseException 
   */
  public List<ExpenseDto> getExpenseDetails() throws ParseException {
    List<ExpenseDto> expensesDto = new ArrayList<>();
    for (Expense expense : expenseRepository.findAllByOrderByExpenseDateDesc()) {
      ExpenseDto expenseDto = convertIntoExpenseDto(expense);
      expensesDto.add(expenseDto);
    }
    return expensesDto;
  }

  /**
   * To get expense detail based on id
   * 
   * @return ExpenseDto
   * @throws ParseException 
   */
  public ExpenseDto getExpenseDetailById(Long expenseId) throws ParseException {
    return convertIntoExpenseDto(expenseRepository.findByExpenseId(expenseId));
  }

  /**
   * Add a new expense detail
   * 
   * @param expenseDto
   * @throws ParseException
   */
  public void addExpenseDetail(ExpenseDto expenseDto) throws ParseException {
    if (expenseDto != null) {
      expenseRepository.save(convertExpenseDtoToEntity(new Expense(), expenseDto));
      LOGGER.info("Expense Details saved with Reason {} :",
            convertExpenseDtoToEntity(new Expense(), expenseDto).getReason());
    }
  }

  /**
   * Modifies expense details
   * 
   * @param expenseDto
   * @throws ParseException
   */
  public void modifyExpenseDetails(ExpenseDto expenseDto) throws ParseException {
    if (expenseDto != null) {
      Expense existingExpenseDetails = expenseRepository.findByExpenseId(expenseDto.getExpenseId());
      expenseRepository.save(convertExpenseDtoToEntity(existingExpenseDetails, expenseDto));
      LOGGER.info("Expense Details updated, ID: {}", existingExpenseDetails.getReason());
    }
  }

  /**
     * Delete the expense detail based on id
     * @param id - Id to by which expense detail to delete
     */
  public void deleteExpenseDetail(Long id) {

    Expense expense = expenseRepository.findByExpenseId(id);
    if (expense == null)
      throw new ValidationException("Sorry! Could not find the requested expense detail to be deleted.");

    expenseRepository.delete(expense);
    LOGGER.info("contribution detail deleted, ID: {}", expense.getExpenseId());
  }

  public static ExpenseDto convertIntoExpenseDto(Expense expenses) throws ParseException {
    ExpenseDto expenseDto = new ExpenseDto();
    expenseDto.setExpenseId(expenses.getExpenseId());
    expenseDto.setExpenseAmount(expenses.getExpenseAmount());
    expenseDto.setExpenseDate(DateUtils.changeDateToStringWithoutDateAndTime(expenses.getExpenseDate()));
    expenseDto.setReason(expenses.getReason());

    return expenseDto;
  }

  public static Expense convertExpenseDtoToEntity(Expense expense, ExpenseDto expenseDto) throws ParseException {
    expense.setExpenseId(expenseDto.getExpenseId());
    expense.setExpenseAmount(expenseDto.getExpenseAmount());
    expense.setExpenseDate(DateUtils.convertStringToDateWithoutDateAndTime(expenseDto.getExpenseDate()));
    expense.setReason(expenseDto.getReason());

    return expense;
  }
}