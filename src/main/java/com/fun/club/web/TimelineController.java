package com.fun.club.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fun.club.domain.entity.Timeline;
import com.fun.club.dto.CommentGetDto;
import com.fun.club.dto.CommentPostDto;
import com.fun.club.dto.FeedResponseDto;
import com.fun.club.dto.TimelineDto;
import com.fun.club.dto.TimelinePostDto;
import com.fun.club.service.TimelineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = { "funclub" })
@RequestMapping(value = TimelineController.CONTROLLER_PATH_PREFIX, produces = MediaType.APPLICATION_JSON_VALUE)
public class TimelineController {
  public static final String CONTROLLER_PATH_PREFIX = "/timeline_feeds";
  private TimelineService timelineService;

  @Autowired
  public TimelineController(final TimelineService timelineService) {
    this.timelineService = timelineService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  @ApiOperation(value = "Get suggestions in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Timeline.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
//  public ResponseEntity<List<TimelineDto>> getTimelinefeeds(@RequestParam("flag") Long flag)
  public ResponseEntity<List<TimelineDto>> getTimelinefeeds()
        throws IOException, ParseException {
//    return new ResponseEntity<List<TimelineDto>>(timelineService.getTimelinefeeds(flag), HttpStatus.OK);
	  return new ResponseEntity<List<TimelineDto>>(timelineService.getTimelinefeeds(), HttpStatus.OK);
  }

  @RequestMapping(value = "/{feedId}", method = RequestMethod.GET)
  @ApiOperation(value = "Get timeline feed details by id in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Timeline.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<TimelineDto> getTimelinefeedById(
        @ApiParam(name = "feedId", required = true) @PathVariable Long feedId) throws IOException, ParseException {
    return new ResponseEntity<TimelineDto>(timelineService.getTimelinefeedById(feedId), HttpStatus.OK);
  }

//  @RequestMapping(value = "/comments/", method = RequestMethod.GET)
//  @ApiOperation(value = "Get comments based on feed Id in fun club")
//  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Timeline.class),
//        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
//  public ResponseEntity<List<CommentGetDto>> getCommentsByfeedId(@RequestParam("feedId") Long feedId) throws IOException, ParseException {
//    return new ResponseEntity<List<CommentGetDto>>(timelineService.getCommentsByfeedId(feedId),
//          HttpStatus.OK);
//  }

  @ApiOperation(value = "Submit a new comment for funclub events")
  @RequestMapping(value = "/comments/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully submitted a new comment"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addComment(
        @ApiParam(value = "suggestion to be submitted", required = true) @Valid @RequestBody final CommentPostDto commentDto)
        throws ParseException {
    timelineService.addComment(commentDto);
    return new ResponseEntity<String>("Comment successfully submitted.", HttpStatus.OK);
  }

  @ApiOperation(value = "Submit a new suggestion for funclub events")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully submitted a new suggestion"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addSuggestion(
        @ApiParam(value = "suggestion to be submitted", required = true) @Valid @RequestBody final TimelinePostDto timelinePostDto)
        throws ParseException {
    timelineService.addSuggestion(timelinePostDto);
    return new ResponseEntity<String>("Suggestion successfully submitted.", HttpStatus.OK);
  }

  @ApiOperation(value = "Submit a new feed response for funclub events")
  @RequestMapping(value = "/feed_response/", method = RequestMethod.POST)
  @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully submitted a new feed_response"),
        @ApiResponse(code = 401, message = "Unauthorized Access") })
  public ResponseEntity<String> addFeedResponse(
        @ApiParam(value = "suggestion to be submitted", required = true) @Valid @RequestBody final FeedResponseDto feedResponse)
        throws ParseException {
    timelineService.addFeedResponse(feedResponse);
    return new ResponseEntity<String>("Feed Response successfully submitted.", HttpStatus.OK);
  }
  
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ApiOperation(value = "Delete a suggestion based on id")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = String.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
  public void deleteSuggestion(
        @ApiParam(name = "id", required = true) @PathVariable Long id) {
	  timelineService.deleteSuggestion(id);
  }
  
  @RequestMapping(value = "/like/{feedId}", method = RequestMethod.GET)
  @ApiOperation(value = "Get like details by feed id in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Timeline.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<String>> getLikeDatafeedById(
        @ApiParam(name = "feedId", required = true) @PathVariable Long feedId) throws IOException, ParseException {
    return new ResponseEntity<List<String>>(timelineService.getLikeDatafeedById(feedId), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/dislike/{feedId}", method = RequestMethod.GET)
  @ApiOperation(value = "Get dislike details by feed id in fun club")
  @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Timeline.class),
        @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 404, message = "Not Found") })
  public ResponseEntity<List<String>> getDislikeDatafeedById(
        @ApiParam(name = "feedId", required = true) @PathVariable Long feedId) throws IOException, ParseException {
    return new ResponseEntity<List<String>>(timelineService.getDislikeDatafeedById(feedId), HttpStatus.OK);
  }
}