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

import com.fun.club.dto.EmployeeDescriptionDto;
import com.fun.club.dto.EmployeeDto;
import com.fun.club.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "funclub" })
@RequestMapping(value = EmployeeController.CONTROLLER_PATH_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
  public static final String CONTROLLER_PATH_PREFIX = "/team_details";
  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(final EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get details of all fun club members")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = EmployeeDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<EmployeeDto>> getDetails() throws ParseException {
    return new ResponseEntity<List<EmployeeDto>>(employeeService.getDetails(), HttpStatus.OK);
  }

  @ApiOperation(value = "Get Employee detail based on Name")
  @RequestMapping(value = "/employee_desc/{employeeName}", method = RequestMethod.GET)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 409, message = "Conflict") })
  public ResponseEntity<List<EmployeeDescriptionDto>> getEmployeeDetailByName(
        @ApiParam(name = "employeeName", required = true) @PathVariable String employeeName) throws ParseException {
    return new ResponseEntity<List<EmployeeDescriptionDto>>(employeeService.getEmployeeDetailByName(employeeName), HttpStatus.OK);
  }
  
  @ApiOperation(value = "Get Employee detail based on Id")
  @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 409, message = "Conflict") })
  public ResponseEntity<EmployeeDto> getEmployeeDetailById(
        @ApiParam(name = "employeeId", required = true) @PathVariable String employeeId) throws ParseException {
    return new ResponseEntity<EmployeeDto>(employeeService.getEmployeeDetailById(employeeId), HttpStatus.OK);
  }

  @ApiOperation(value = "Create new fun club member")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added fun club member"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addEmployee(
        @ApiParam(value = "fun club member to be created", required = true) @Valid @RequestBody final EmployeeDto employeeDto)
        throws ParseException {
    employeeService.addEmployee(employeeDto);
    return new ResponseEntity<String>("Fun club member successfully added.", HttpStatus.OK);
  }

  @ApiOperation(value = "Modify an exsiting member")
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated fun club member"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> modifyEmployee(
        @ApiParam(value = "fun club member to be modified", required = true) @Valid @RequestBody final EmployeeDto employeeDto)
        throws ParseException {
    employeeService.modifyEmployee(employeeDto);
    return new ResponseEntity<String>("Fun club member successfully updated.", HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete a employee based on id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
  public void deleteEmployee(
        @ApiParam(name = "id", required = true) @PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }
}