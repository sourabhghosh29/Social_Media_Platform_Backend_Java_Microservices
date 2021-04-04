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

import com.fun.club.dto.ContributionDto;
import com.fun.club.dto.GraphDataDto;
import com.fun.club.service.ContributionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "funclub" })
@RequestMapping(value = ContributionController.CONTROLLER_PATH_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContributionController {
  public static final String CONTROLLER_PATH_PREFIX = "/contribution_details";
  private ContributionService contributionService;

  @Autowired
  public ContributionController(final ContributionService contributionService) {
    this.contributionService = contributionService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get contribution details of all fun club members")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ContributionDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<ContributionDto>> getAllContributionDetails() throws ParseException {
    return new ResponseEntity<List<ContributionDto>>(contributionService.getAllContributionDetails(),
          HttpStatus.OK);
  }

  @ApiOperation(value = "Get Contribution detail based on Id")
  @RequestMapping(value = "/{contributionId}", method = RequestMethod.GET)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 409, message = "Conflict") })
  public ResponseEntity<ContributionDto> getExpenseDetailById(
        @ApiParam(name = "contributionId", required = true) @PathVariable Long contributionId) throws ParseException {
    return new ResponseEntity<ContributionDto>(contributionService.getContributionDetailById(contributionId),
          HttpStatus.OK);
  }

  @ApiOperation(value = "Add new contribution details")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added contribution details"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addContributionDetail(
        @ApiParam(value = "Contribution Details Added", required = true) @Valid @RequestBody final ContributionDto contributionDto)
        throws ParseException {
    contributionService.addContributionDetail(contributionDto);
    return new ResponseEntity<String>("Contribution Details successfully added.", HttpStatus.OK);
  }

  @ApiOperation(value = "Modify an exsiting contribution details")
  @RequestMapping(value = "/", method = RequestMethod.PUT)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated contribution details"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> modifyContributionDetails(
        @ApiParam(value = "contribution details to be modified", required = true) @Valid @RequestBody final ContributionDto contributionDto)
        throws ParseException {
    contributionService.modifyContributionDetails(contributionDto);
    return new ResponseEntity<String>("Contribution Details successfully updated.", HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete a contribution deatail based on id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
  public void deleteContributionDetail(
        @ApiParam(name = "id", required = true) @PathVariable Long id) {
    contributionService.deleteContributionDetail(id);
  }

  @RequestMapping(value = "/graph_data/", method = RequestMethod.GET)
  @ApiOperation(value = "Get total contribution and expense month wise in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GraphDataDto.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<GraphDataDto>> getGraphData() {
    return new ResponseEntity<List<GraphDataDto>>(contributionService.getGraphData(),
          HttpStatus.OK);
  }
  
  @RequestMapping(value = "/saving_account/", method = RequestMethod.GET)
  @ApiOperation(value = "Get Remaining current balance in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Long.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<Long> getCurrentBalance() {
    return new ResponseEntity<Long>(contributionService.getCurrentBalance(),
          HttpStatus.OK);
  }
}