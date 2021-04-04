package com.fun.club.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.club.domain.entity.Comment;
import com.fun.club.domain.entity.FeedResponse;
import com.fun.club.domain.entity.Timeline;
import com.fun.club.domain.repository.CommentRepository;
import com.fun.club.domain.repository.EmployeeRepository;
import com.fun.club.domain.repository.FeedResponseRepository;
import com.fun.club.domain.repository.TimelineRepository;
import com.fun.club.dto.CommentGetDto;
import com.fun.club.dto.CommentPostDto;
import com.fun.club.dto.FeedResponseDto;
import com.fun.club.dto.TimelineDto;
import com.fun.club.dto.TimelinePostDto;
import com.fun.club.utils.DateUtils;
import com.fun.club.web.exception.ValidationException;

@Service
public class TimelineService {
	private TimelineRepository timelineRepository;
	private FeedResponseRepository feedResponseRepository;
	private EmployeeRepository employeeRepository;
	private CommentRepository commentRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(TimelineService.class);

	@Autowired
	public TimelineService(final EmployeeRepository employeeRepository, final TimelineRepository timelineRepository,
			final FeedResponseRepository feedResponseRepository, final CommentRepository commentRepository) {
		this.timelineRepository = timelineRepository;
		this.feedResponseRepository = feedResponseRepository;
		this.employeeRepository = employeeRepository;
		this.commentRepository = commentRepository;
	}

	/**
	 * To get timeline feeds
	 * 
	 * @return timelineDto
	 * @throws IOException
	 * @throws ParseException
	 */
	// public List<TimelineDto> getTimelinefeeds(Long flag) throws IOException,
	// ParseException {
	public List<TimelineDto> getTimelinefeeds() throws IOException, ParseException {
		List<TimelineDto> timelinesDto = new ArrayList<>();
		// if (flag == 0) {
		// for (Timeline timeline : timelineRepository.fetchTop5suggestion()) {
		// TimelineDto timelineDto = new TimelineDto();
		// timelineDto = convertIntoTimeLineDto(timeline);
		// timelinesDto.add(timelineDto);
		// }
		// } else {
		// for (Timeline timeline :
		// timelineRepository.findAllByOrderBySuggestionTimeDesc()) {
		// TimelineDto timelineDto = new TimelineDto();
		// timelineDto = convertIntoTimeLineDto(timeline);
		// timelinesDto.add(timelineDto);
		// }
		// }
		for (Timeline timeline : timelineRepository.findAllByOrderByLikeCountDesc()) {
			TimelineDto timelineDto = new TimelineDto();
			timelineDto = convertIntoTimeLineDto(timeline);
			timelinesDto.add(timelineDto);
		}

		return timelinesDto;
	}

	/**
	 * To get timeline feeds by Id
	 * 
	 * @return timeline Dto
	 * @throws IOException
	 * @throws ParseException
	 */
	public TimelineDto getTimelinefeedById(Long feedId) throws IOException, ParseException {
		TimelineDto timelineDto = new TimelineDto();
		timelineDto = convertIntoTimeLineDto(timelineRepository.findById(feedId));

		return timelineDto;
	}

	/**
	 * Creates a new suggestion
	 * 
	 * @param timeline
	 * @throws ParseException
	 */
	public void addSuggestion(TimelinePostDto timelinePostDto) throws ParseException {
		if (timelinePostDto != null && timelinePostDto.getSuggestion() != null && timelinePostDto.getSuggestion() != "") {
			timelineRepository.save(convertIntoTimeLine(timelinePostDto));
			LOGGER.info("Suggestion {} saved on {} by Id :{}", timelinePostDto.getSuggestion(),
					timelinePostDto.getSuggestionTime(), timelinePostDto.getId());
		} else
			throw new ValidationException("Suggestion must not be empty.");
	}

	/**
	 * To get comments by feed Id
	 * 
	 * @return List of comments
	 * @throws IOException
	 * @throws ParseException
	 */
	// public List<CommentGetDto> getCommentsByfeedId(Long feedId) throws
	// IOException, ParseException {
	// List<CommentGetDto> commentsDto = new ArrayList<>();
	// if (flag == 0) {
	// CommentGetDto commentGetDto = new CommentGetDto();
	// for (Comment comment :
	// commentRepository.findTop3ByFeedIdOrderByCommentTimeDesc(feedId)) {
	// commentGetDto = convertIntoCommentGetDto(comment);
	// commentsDto.add(commentGetDto);
	// }
	// } else {
	// CommentGetDto commentGetDto = new CommentGetDto();
	// for (Comment comment : commentRepository.fetchAllcommentsByfeedId(feedId)) {
	// commentGetDto = convertIntoCommentGetDto(comment);
	// commentsDto.add(commentGetDto);
	// }
	// }
	// CommentGetDto commentGetDto = new CommentGetDto();
	// for (Comment comment : commentRepository.fetchAllcommentsByfeedId(feedId)) {
	// commentGetDto = convertIntoCommentGetDto(comment);
	// commentsDto.add(commentGetDto);
	// }
	// return commentsDto;
	// }

	/**
	 * Add a new comment on any suggestion
	 * 
	 * @param CommentPostDto
	 * @throws ParseException
	 */
	public void addComment(CommentPostDto commentDto) throws ParseException {

		if (commentDto != null && commentDto.getComment() != null) {
			Comment comment = new Comment();
			comment.setId(commentDto.getId());
			comment.setFeedId(commentDto.getFeedId());
			comment.setEmployeeId(commentDto.getEmployeeId());
			comment.setComment(commentDto.getComment());
			comment.setCommentTime(DateUtils.convertStringToDate(commentDto.getCommentTime()));
			commentRepository.save(comment);

			Timeline timeline = new Timeline();
			timeline = timelineRepository.findById(commentDto.getFeedId());
			timeline.setCommentCount(timeline.getCommentCount() + 1);

			timelineRepository.save(timeline);
			LOGGER.info("Comment {} saved on {} by Id :{}", comment.getComment(), comment.getCommentTime(),
					commentDto.getId());
		} else
			throw new ValidationException("Comment must not be empty.");
	}

	/**
	 * Add a new Feed Response
	 * 
	 * @param feedResponse
	 * @throws ParseException
	 */
	public void addFeedResponse(FeedResponseDto feedResponseDto) throws ParseException {

		// if (feedResponseDto != null &&
		// feedResponseRepository.findByFeedIdAndEmployeeId(feedResponseDto.getFeedId(),
		// feedResponseDto.getEmployeeId()) == null) {
		// FeedResponse feed = new FeedResponse();
		// feed.setId(feedResponseDto.getId());
		// feed.setFeedId(feedResponseDto.getFeedId());
		// feed.setEmployeeId(feedResponseDto.getEmployeeId());
		// feed.setLikeFeed(feedResponseDto.isLikeFeed());
		// feed.setDislikeFeed(feedResponseDto.isDislikeFeed());
		// feedResponseRepository.save(feed);
		//
		// Timeline timeline = new Timeline();
		// timeline = timelineRepository.findById(feedResponseDto.getFeedId());
		// if (feedResponseDto.isLikeFeed())
		// timeline.setLikeCount(timeline.getLikeCount() + 1);
		// else if (feedResponseDto.isDislikeFeed())
		// timeline.setDislikeCount(timeline.getDislikeCount() + 1);
		//
		// timelineRepository.save(timeline);
		// LOGGER.info("Feed Response {} saved {} by Id :{}", feed.getId(),
		// feed.getId(), feed.getId());
		// }
		if (feedResponseDto != null) {
			FeedResponse existingFeedResponse = feedResponseRepository
					.findByFeedIdAndEmployeeId(feedResponseDto.getFeedId(), feedResponseDto.getEmployeeId());
			Timeline timeline = timelineRepository.findById(feedResponseDto.getFeedId());
			if (existingFeedResponse != null) {
				if (feedResponseDto.isLikeFeed() == true && existingFeedResponse.isLikeFeed() == false) {

					feedResponseRepository.delete(existingFeedResponse.getId());
					feedResponseRepository.save(convertFeedResponseDtoToFeedResponse(feedResponseDto));
					timeline.setLikeCount(timeline.getLikeCount() + 1);
					timeline.setDislikeCount(timeline.getDislikeCount() - 1);
					timelineRepository.save(timeline);
					LOGGER.info("Feed Response like with {} saved for suggestion id {} by Id :{}",
							feedResponseDto.isLikeFeed(), feedResponseDto.getFeedId(), feedResponseDto.getId());
					System.out.println("Feed Response Submitted with Like");
				} else if (feedResponseDto.isDislikeFeed() == true && existingFeedResponse.isDislikeFeed() == false) {

					feedResponseRepository.delete(existingFeedResponse.getId());
					feedResponseRepository.save(convertFeedResponseDtoToFeedResponse(feedResponseDto));
					timeline.setLikeCount(timeline.getLikeCount() - 1);
					timeline.setDislikeCount(timeline.getDislikeCount() + 1);
					timelineRepository.save(timeline);
					LOGGER.info("Feed Response dislike with {} saved for suggestion id {} by Id :{}",
							feedResponseDto.isDislikeFeed(), feedResponseDto.getFeedId(), feedResponseDto.getId());
					System.out.println("Feed Response Submitted with DisLike");
				}
			} else {
				feedResponseRepository.save(convertFeedResponseDtoToFeedResponse(feedResponseDto));
				if (feedResponseDto.isLikeFeed())
					timeline.setLikeCount(timeline.getLikeCount() + 1);
				else if (feedResponseDto.isDislikeFeed())
					timeline.setDislikeCount(timeline.getDislikeCount() + 1);

				timelineRepository.save(timeline);
				LOGGER.info("Feed Response saved for suggestion id {} by Id :{}", feedResponseDto.getFeedId(),
						feedResponseDto.getId());
				System.out.println("Feed Response Submitted First Time");
			}
		} else
			throw new ValidationException(
					"Feed Response must not be empty or you have liked/disliked to this post before");
	}

	/**
	 * Delete the suggestion based on id
	 * 
	 * @param id
	 *            - Id to by which suggestion to delete
	 */
	public void deleteSuggestion(Long id) {

		Timeline timeline = timelineRepository.findById(id);
		// UserProfileService userProfileService = new UserProfileService(null);
		// Employee employee =
		// employeeRepository.findByEmployeeName(userProfileService.getUserDetails().getUserName());
		// timeline.getEmployeeId()!= employee.getEmployeeId()
		if (timeline != null) {
			feedResponseRepository.deleteAllFeedsByfeedId(id);
			commentRepository.deleteAllCommentByfeedId(id);
			timelineRepository.delete(timeline);
			LOGGER.info("Suggestionr deleted, suggeation ID: {}", timeline.getId());
		} else
			throw new ValidationException("Sorry! Could not find the requested suggestion to be deleted.");
	}

	public byte[] getDocumentFromDB(Timeline timeline) {
		byte[] buf = null;
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream(timeline.getDocument().toString());
			// buf = IOUtils.toByteArray(inputStream);
			inputStream.close();
		} catch (IOException e) {
			LOGGER.error("Error in converting image:", e);
			throw new ValidationException("Error in converting image.");
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.error("Error while closing stream:", e);
				}
		}
		return buf;
	}

	public TimelineDto convertIntoTimeLineDto(Timeline timeline) throws ParseException, IOException {
		TimelineDto timelineDto = new TimelineDto();
		if (timeline != null) {
			timelineDto.setId(timeline.getId());
			timelineDto.setEmployeeId(timeline.getEmployeeId());
			timelineDto
					.setEmployeeName((employeeRepository.findByEmployeeId(timeline.getEmployeeId())).getEmployeeName());
			timelineDto.setSuggestion(timeline.getSuggestion());
			timelineDto.setLikeCount(timeline.getLikeCount());
			timelineDto.setLikeDetails(getLikeDatafeedById(timeline.getId()));
			timelineDto.setDislikeCount(timeline.getDislikeCount());
			timelineDto.setDislikeDetails(getDislikeDatafeedById(timeline.getId()));
			timelineDto.setCommentCount(timeline.getCommentCount());
			timelineDto.setComments(
					convertIntoCommentGetDto(commentRepository.findByFeedIdOrderByCommentTimeDesc(timeline.getId())));
			timelineDto.setDocument(timeline.getDocument());
			timelineDto.setSuggestionTime(DateUtils.changeDateToString(timeline.getSuggestionTime()));
		} else
			throw new ValidationException("Sorry! Could not find the requested suggestion.");

		return timelineDto;
	}

	public Timeline convertIntoTimeLine(TimelinePostDto timelinePostdto) throws ParseException {
		Timeline timeline = new Timeline();
		if (timelinePostdto != null) {
			timeline.setId(timelinePostdto.getId());
			timeline.setEmployeeId(timelinePostdto.getEmployeeId());
			timeline.setSuggestion(timelinePostdto.getSuggestion());
			timeline.setLikeCount(0L);
			timeline.setDislikeCount(0L);
			timeline.setCommentCount(0L);
			timeline.setDocument(timelinePostdto.getDocument());
			timeline.setSuggestionTime(DateUtils.convertStringToDate(timelinePostdto.getSuggestionTime()));
		} else
			throw new ValidationException("Sorry! Could not save the submitted suggestion...NULL");
		return timeline;
	}

	// public List<String> mergeCommentWithName(List<Comment> commentList) {
	// List<String> comments = new ArrayList<>();
	// for (Comment feed : commentList) {
	// String comment = feed.getComment() + " - "
	// +
	// employeeRepository.findByEmployeeId(feed.getEmployeeId()).getEmployeeName();
	// comments.add(comment);
	// }
	// return comments;
	// }

	// public CommentGetDto convertIntoCommentGetDto(Comment comment) throws
	// ParseException {
	// CommentGetDto commentGetDto = new CommentGetDto();
	// commentGetDto.setId(comment.getId());
	// commentGetDto.setFeedId(comment.getFeedId());
	// commentGetDto.setEmployeeName(employeeRepository.findByEmployeeId(comment.getEmployeeId()).getEmployeeName());
	// commentGetDto.setComment(comment.getComment());
	// commentGetDto.setCommentTime(DateUtils.changeDateToString(comment.getCommentTime()));
	//
	// return commentGetDto;
	// }
	//
	public List<CommentGetDto> convertIntoCommentGetDto(List<Comment> comments) throws ParseException {

		List<CommentGetDto> commentGetDtos = new ArrayList<>();
		for (Comment comment : comments) {
			CommentGetDto commentGetDto = new CommentGetDto();
			commentGetDto.setId(comment.getId());
			commentGetDto.setFeedId(comment.getFeedId());
			commentGetDto
					.setEmployeeName(employeeRepository.findByEmployeeId(comment.getEmployeeId()).getEmployeeName());
			commentGetDto.setComment(comment.getComment());
			commentGetDto.setCommentTime(DateUtils.changeDateToString(comment.getCommentTime()));

			commentGetDtos.add(commentGetDto);

		}
		return commentGetDtos;
	}

	public FeedResponse convertFeedResponseDtoToFeedResponse(FeedResponseDto feedResponseDto) throws ParseException {

		FeedResponse feed = new FeedResponse();
		feed.setId(feedResponseDto.getId());
		feed.setFeedId(feedResponseDto.getFeedId());
		feed.setEmployeeId(feedResponseDto.getEmployeeId());
		feed.setLikeFeed(feedResponseDto.isLikeFeed());
		feed.setDislikeFeed(feedResponseDto.isDislikeFeed());

		return feed;
	}

	/**
	 * To get like details by feed Id
	 * 
	 * @return likelist
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<String> getLikeDatafeedById(Long feedId) throws IOException, ParseException {
		List<String> likelist = new ArrayList<>();
		for (String id : feedResponseRepository.fetchlikedataByfeedId(feedId))
			likelist.add(employeeRepository.findByEmployeeId(id).getEmployeeName());

		return likelist;
	}

	/**
	 * To get dislike details by feed Id
	 * 
	 * @return dislikelist
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<String> getDislikeDatafeedById(Long feedId) throws IOException, ParseException {
		List<String> dislikelist = new ArrayList<>();
		for (String id : feedResponseRepository.fetchdislikedataByfeedId(feedId))
			dislikelist.add(employeeRepository.findByEmployeeId(id).getEmployeeName());

		return dislikelist;
	}
}
