package com.fun.club.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fun.club.auth.domain.entity.UserDetails;
import com.fun.club.auth.domain.repository.UserRepository;
import com.fun.club.domain.entity.Employee;
import com.fun.club.domain.repository.EmployeeRepository;
import com.fun.club.dto.EmployeeDto;
import com.fun.club.dto.EmployeeDescriptionDto;
import com.fun.club.utils.DateUtils;
import com.fun.club.web.exception.ValidationException;

@Service
public class EmployeeService {
	private EmployeeRepository employeeRepository;
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	public EmployeeService(final EmployeeRepository employeeRepository, final UserRepository userRepository) {
		this.employeeRepository = employeeRepository;
		this.userRepository = userRepository;
	}

	/**
	 * To get all details of funclub members
	 * 
	 * @return Employee List
	 * @throws ParseException
	 */
	public List<EmployeeDto> getDetails() throws ParseException {
		List<Employee> employees = new ArrayList<>();
		List<EmployeeDto> employeesDto = new ArrayList<>();
		employees = employeeRepository.findAllEmployees();
		for (Employee e : employees) {
			employeesDto.add(convertIntoEmployeeDto(e));
		}
		return employeesDto;
	}

	/**
	 * To get employee detail based on id
	 * 
	 * @return EmployeeDto
	 * @throws ParseException
	 */
	public EmployeeDto getEmployeeDetailById(String employeeId) throws ParseException {
		return convertIntoEmployeeDto(employeeRepository.findByEmployeeId(employeeId));
	}
	
	/**
	 * To get employee detail based on name
	 * 
	 * @return EmployeeDto
	 * @throws ParseException
	 */
	public List<EmployeeDescriptionDto> getEmployeeDetailByName(String employeeName) throws ParseException {
		List<EmployeeDescriptionDto> employeeDescriptionDto = new ArrayList<>();
		for (Employee e : employeeRepository.findByEmployeeNameIgnoreCaseContaining(employeeName)) {
			employeeDescriptionDto.add(convertIntoEmployeeDescription(e));
		}
		return employeeDescriptionDto;
	}

	/**
	 * Creates a new fun club member
	 * 
	 * @param employeeDto
	 * @throws ParseException
	 */
	public void addEmployee(EmployeeDto employeeDto) throws ParseException {
		if (employeeDto != null) {
			employeeRepository.save(convertEmployeeDtoToEntity(new Employee(), employeeDto));
			LOGGER.info("Fun club member saved with Name : {}",
					convertEmployeeDtoToEntity(new Employee(), employeeDto).getEmployeeName());
			UserDetails userDetails = new UserDetails();
			userDetails.setUsername(employeeDto.getEmployeeId().toString());
			userDetails.setPassword(employeeDto.getEmployeeId().toString());
			userDetails.setRole("user");
			userDetails.setIsDefaultPasswordReset(false);

			userRepository.save(userDetails);
			LOGGER.info("New User saved with Id : {} and role : {}", userDetails.getUsername(), userDetails.getRole());
		} else
			throw new ValidationException("Employee must not be empty.");
	}

	/**
	 * Modifies a fun club member
	 * 
	 * @param employeeDto
	 * @throws ParseException
	 */
	public void modifyEmployee(EmployeeDto employeeDto) throws ParseException {

		if (employeeDto != null && !StringUtils.isEmpty(employeeDto.getEmployeeId())) {
			Employee existingMember = employeeRepository.findById(employeeDto.getId());
			employeeRepository.save(convertEmployeeDtoToEntity(existingMember, employeeDto));
			LOGGER.info("Fun club member updated, ID: {}", existingMember.getEmployeeId());
		}
	}

	/**
	 * Delete the employee based on id
	 * 
	 * @param id
	 *            - Id to by which employee to delete
	 */
	public void deleteEmployee(Long id) {

		Employee employee = employeeRepository.findById(id);
		if (employee != null) {
			employeeRepository.delete(employee);
			LOGGER.info("Fun club member deleted, Employee ID: {}", employee.getEmployeeId());
		} else
			throw new ValidationException("Sorry! Could not find the requested employee to be deleted.");

	}

	public static EmployeeDto convertIntoEmployeeDto(Employee employee) throws ParseException {
		EmployeeDto employeedto = new EmployeeDto();
		if (employee != null) {
			employeedto.setId(employee.getId());
			employeedto.setEmployeeId(employee.getEmployeeId());
			employeedto.setEmployeeName(employee.getEmployeeName());
			employeedto.setDob(DateUtils.changeDateToStringWithoutTime(employee.getDob()));
			employeedto.setContact(employee.getContact());
			employeedto.setCakeFlavour(employee.getCakeFlavour());
			employeedto.setInitiatives(employee.getInitiatives());
			employeedto.setInterests(employee.getInterests());
		} else
			throw new ValidationException("Sorry! Employee details must not be empty.");

		return employeedto;
	}

	public static Employee convertEmployeeDtoToEntity(Employee employee, EmployeeDto employeeDto)
			throws ParseException {
		if (employeeDto != null) {
			employee.setId(employeeDto.getId());
			employee.setEmployeeId(employeeDto.getEmployeeId());
			employee.setEmployeeName(employeeDto.getEmployeeName());
			employee.setDob(DateUtils.convertStringToDateWithoutTime(employeeDto.getDob()));
			employee.setContact(employeeDto.getContact());
			employee.setCakeFlavour(employeeDto.getCakeFlavour());
			employee.setInitiatives(employeeDto.getInitiatives());
			employee.setInterests(employeeDto.getInterests());
		} else
			throw new ValidationException("Sorry! Employee details must not be empty.");

		return employee;
	}
	
	public static EmployeeDescriptionDto convertIntoEmployeeDescription(Employee employee) throws ParseException {
		EmployeeDescriptionDto employeeDescriptionDto = new EmployeeDescriptionDto();

		employeeDescriptionDto.setEmployeeName(employee.getEmployeeName());
		StringBuilder sb = new StringBuilder("");
		sb.append("Employee Id : " + employee.getEmployeeId());
		sb.append(System.lineSeparator());
		sb.append("Faviourate cake flavour : " + employee.getCakeFlavour());
		sb.append(System.lineSeparator());
		sb.append("Initiatives : " + employee.getInitiatives());
		sb.append(System.lineSeparator());
		sb.append("Interests : " + employee.getInterests());
		sb.append(System.lineSeparator());
		sb.append("Date Of Birth : " + DateUtils.changeDateToStringWithoutTime(employee.getDob()));
		sb.append(System.lineSeparator());
		System.out.println(sb.toString());
		employeeDescriptionDto.setDescription(sb.toString());

		// employeeDescriptionDto.setDescription("Employee Id : " +
		// employee.getEmployeeId() + ", \nFaviourate cake flavour : "
		// + employee.getCakeFlavour() + ", \nInitiatives : " +
		// employee.getInitiatives() + ", \nInterests : "
		// + employee.getInterests() + ", \nDate Of Birth : " + employee.getDob());
		employeeDescriptionDto.setContact("Mobile No. : " + employee.getContact());

		return employeeDescriptionDto;
	}
}