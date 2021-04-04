package com.fun.club.web;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fun.club.dto.ExpenseDto;
import com.fun.club.service.ExpenseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "funclub" })
@RequestMapping(value = ExpenseController.CONTROLLER_PATH_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {
  public static final String CONTROLLER_PATH_PREFIX = "/expense_details";
  private ExpenseService expenseService;

  @Autowired
  public ExpenseController(final ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get expenses details in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ExpenseDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<ExpenseDto>> getExpenseDetails() throws ParseException {
    return new ResponseEntity<List<ExpenseDto>>(expenseService.getExpenseDetails(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get Expense detail based on Id")
  @RequestMapping(value = "/{expenseId}", method = RequestMethod.GET)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 409, message = "Conflict") })
  public ResponseEntity<ExpenseDto> getExpenseDetailById(
        @ApiParam(name = "expenseId", required = true) @PathVariable Long expenseId) throws ParseException {
    return new ResponseEntity<ExpenseDto>(expenseService.getExpenseDetailById(expenseId), HttpStatus.OK);
  }

  @ApiOperation(value = "Add new expense details")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added expense details"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addExpenseDetail(
        @ApiParam(value = "Expense Details Added", required = true) @Valid @RequestBody final ExpenseDto expenseDto)
        throws ParseException {
    expenseService.addExpenseDetail(expenseDto);
    return new ResponseEntity<String>("Expense Details successfully added.", HttpStatus.OK);
  }

  @ApiOperation(value = "Modify an exsiting expense details")
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated expense details"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> modifyExpenseDetails(
        @ApiParam(value = "fun club member to be modified", required = true) @Valid @RequestBody final ExpenseDto expenseDto)
        throws ParseException {
    expenseService.modifyExpenseDetails(expenseDto);
    return new ResponseEntity<String>("Expense Details successfully updated.", HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete a expense deatail based on id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
  public void deleteExpenseDetail(
        @ApiParam(name = "id", required = true) @PathVariable Long id) {
    expenseService.deleteExpenseDetail(id);
  }
}