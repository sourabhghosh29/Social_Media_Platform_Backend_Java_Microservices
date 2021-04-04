package com.fun.club.service;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fun.club.auth.domain.entity.UserDetails;
import com.fun.club.auth.domain.repository.UserRepository;
import com.fun.club.domain.repository.EmployeeRepository;
import com.fun.club.dto.UserDetailDto;
import com.fun.club.web.exception.ValidationException;

@Service
public class UserProfileService {
	private UserRepository userRepository;
	private EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(UserProfileService.class);

	@Autowired
	public UserProfileService(final UserRepository userRepository, final EmployeeRepository employeeRepository) {
		this.userRepository = userRepository;
		this.employeeRepository = employeeRepository;
	}

	/**
	 * get user details based on login
	 * 
	 * @return userDetailDto
	 */
	public UserDetailDto getUserDetails() {
		UserDetailDto userDetailDto = new UserDetailDto();
		UserDetails userDetails = userRepository.findByUsername(getLoggedInUser()).get();
		if (userDetails.getUsername().equalsIgnoreCase("Admin"))
			userDetailDto.setEmployeeName("Admin");
		else
			userDetailDto.setEmployeeName(
					employeeRepository.findByEmployeeId(userDetails.getUsername()).getEmployeeName());

		userDetailDto.setUserName(userDetails.getUsername());
		userDetailDto.setRole(userDetails.getRole());
		userDetailDto.setDefaultPasswordReset(userDetails.getIsDefaultPasswordReset());
		return userDetailDto;
	}

	/**
	 * reset password
	 * 
	 * @param userDetails
	 */
	public void resetPassword(UserDetails userDetails) throws ParseException {
		System.out.println("Reset method called for user" + userDetails.getUsername());
		if (userDetails != null && !StringUtils.isEmpty(userDetails.getUsername())) {
			System.out.println("Reset method called");
			UserDetails existingUser = userRepository.findByUsername(userDetails.getUsername()).get();
			existingUser.setPassword(userDetails.getPassword());
			userRepository.save(existingUser);
			LOGGER.info("Password reset for ID :{}", userDetails.getUsername());
		} else
			throw new ValidationException("User Details must not be empty.");
	}

	private final String getLoggedInUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
