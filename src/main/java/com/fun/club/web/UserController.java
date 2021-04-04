package com.fun.club.web;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fun.club.auth.domain.entity.UserDetails;
import com.fun.club.auth.service.FunClubUserDetailsService;
import com.fun.club.dto.EmployeeDto;
import com.fun.club.dto.UserDetailDto;
import com.fun.club.service.UserProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "funclub" })
@RequestMapping(value = UserController.CONTROLLER_PATH_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  public static final String CONTROLLER_PATH_PREFIX = "/user_details";
  private UserProfileService userDetailsService;

  @Autowired
  public UserController(final UserProfileService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get user details for login")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = UserDetailDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<UserDetailDto> getDetails() throws ParseException {
    return new ResponseEntity<UserDetailDto>(userDetailsService.getUserDetails(), HttpStatus.OK);
  }
  
  @ApiOperation(value = "Reset Password")
  @RequestMapping(value = "/forgot_password/", method = RequestMethod.PUT)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully reset password"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> resetPassword(
        @ApiParam(value = "Successfully reset password", required = true) @Valid @RequestBody final UserDetails userDetails)
        throws ParseException {
	  userDetailsService.resetPassword(userDetails);
    return new ResponseEntity<String>("Password successfully reset", HttpStatus.OK);
  }
}