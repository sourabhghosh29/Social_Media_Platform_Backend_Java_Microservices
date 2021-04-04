package com.fun.club.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fun.club.domain.entity.Contribution;
import com.fun.club.domain.entity.MonthlyData;
import com.fun.club.domain.repository.ContributionRepository;
import com.fun.club.domain.repository.EmployeeRepository;
import com.fun.club.domain.repository.ExpenseRepository;
import com.fun.club.domain.repository.MonthWiseDataRepository;
import com.fun.club.dto.ContributionDto;
import com.fun.club.dto.GraphDataDto;
import com.fun.club.utils.DateUtils;
import com.fun.club.web.exception.ValidationException;

@Service
public class ContributionService {
	private ContributionRepository contributionRepository;
	private MonthWiseDataRepository monthWiseDataRepository;
	private static EmployeeRepository employeeRepository;
	private ExpenseRepository expenseRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ContributionService.class);

	@Autowired
	public ContributionService(final ContributionRepository contributionRepository,
			final MonthWiseDataRepository monthWiseDataRepository, final EmployeeRepository employeeRepository, final ExpenseRepository expenseRepository) {
		this.contributionRepository = contributionRepository;
		this.monthWiseDataRepository = monthWiseDataRepository;
		this.employeeRepository = employeeRepository;
		this.expenseRepository = expenseRepository;
	}

	/**
	 * To get contribution details of funclub members
	 * 
	 * @return Contribution List
	 * @throws ParseException
	 */
	public List<ContributionDto> getAllContributionDetails() throws ParseException {
		List<ContributionDto> contributionsDto = new ArrayList<>();
		for (Contribution contribution : contributionRepository.findAllByOrderByContributionDateDesc()) {
			ContributionDto contributionDto = convertIntoContributionDto(contribution);
			contributionsDto.add(contributionDto);
		}
		return contributionsDto;
	}

	/**
	 * To get contribution detail based on id
	 * 
	 * @return ContributionDto
	 * @throws ParseException
	 */
	public ContributionDto getContributionDetailById(Long contributionId) throws ParseException {
		return convertIntoContributionDto(contributionRepository.findById(contributionId));
	}

	/**
	 * Add a new contribution detail
	 * 
	 * @param ContributionDto
	 * @throws ParseException
	 */
	public void addContributionDetail(ContributionDto contributionDto) throws ParseException {
		if (contributionDto != null) {
			contributionRepository.save(convertContributionDtoToEntity(new Contribution(), contributionDto));
			LOGGER.info("Contribution Details saved with Comment {} :",
					convertContributionDtoToEntity(new Contribution(), contributionDto).getComment());
		}
	}

	/**
	 * Modifies Contribution details
	 * 
	 * @param contributionDto
	 * @throws ParseException
	 */
	public void modifyContributionDetails(ContributionDto contributionDto) throws ParseException {

		if (contributionDto != null) {
			Contribution existingContributionDetails = contributionRepository.findById(contributionDto.getId());
			contributionRepository.save(convertContributionDtoToEntity(existingContributionDetails, contributionDto));
			LOGGER.info("Contribution Details updated, ID: {}", existingContributionDetails.getId());
		}
	}

	/**
	 * Delete the contribution detail based on id
	 * 
	 * @param id
	 *            - Id to by which contribution detail to delete
	 */
	public void deleteContributionDetail(Long id) {

		Contribution contribution = contributionRepository.findById(id);
		if (contribution == null)
			throw new ValidationException("Sorry! Could not find the requested contribution to be deleted.");

		contributionRepository.delete(contribution);
		LOGGER.info("contribution detail deleted, ID: {}", contribution.getId());
	}

	/**
	 * To get total contribution and expenses
	 * 
	 * @return SumDto List
	 * @throws ParseException
	 */
	public List<GraphDataDto> getGraphData() {
		// List<MonthlyData> SumDtos = monthWiseDataRepository.fetchGraphData();
		List<GraphDataDto> graphDatas = new ArrayList<GraphDataDto>();
		for (MonthlyData m : monthWiseDataRepository.fetchGraphData()) {
			GraphDataDto graphData = new GraphDataDto();
			graphData.setMonth_year(mergeMonthAndYear(m.getMonth_name(), m.getYears()));
			if (m.getContribution_amount() != null)
				graphData.setContribution_amount(m.getContribution_amount());
			else
				graphData.setContribution_amount(0L);
			if (m.getExpense_amount() != null)
				graphData.setExpense_amount(m.getExpense_amount());
			else
				graphData.setExpense_amount(0L);

			graphDatas.add(graphData);
		}
		return graphDatas;
	}

	public static ContributionDto convertIntoContributionDto(Contribution contribution) throws ParseException {
    ContributionDto contributionDto = new ContributionDto();
    contributionDto.setId(contribution.getId());
    contributionDto.setEmployeeId(contribution.getEmployeeId());
    contributionDto.setEmployeeName(employeeRepository.findByEmployeeId(contribution.getEmployeeId()).getEmployeeName());
    contributionDto.setContributionAmount(contribution.getContributionAmount());
    contributionDto.setContributionDate(
          DateUtils.changeDateToStringWithoutDateAndTime(contribution.getContributionDate()));
    contributionDto.setComment(contribution.getComment());

    return contributionDto;
  }

	public static Contribution convertContributionDtoToEntity(Contribution contribution,
			ContributionDto contributionDto) throws ParseException {
		contribution.setId(contributionDto.getId());
		contribution.setEmployeeId(contributionDto.getEmployeeId());
		contribution.setContributionAmount(contributionDto.getContributionAmount());
		contribution.setContributionDate(
				DateUtils.convertStringToDateWithoutDateAndTime(contributionDto.getContributionDate()));
		contribution.setComment(contributionDto.getComment());

		return contribution;
	}

	public static String mergeMonthAndYear(String month, Double year) {
		return month + "-" + year;
	}
	
	/**
	 * To get current balance in fun club account
	 * 
	 * @return Long
	 */
	public Long getCurrentBalance() {
		Long totalContribution,totalExpense;
		totalContribution = (contributionRepository.fetchTotalContribution()==null)? 0L :contributionRepository.fetchTotalContribution();
		totalExpense = (expenseRepository.fetchTotalExpense()==null)? 0L :expenseRepository.fetchTotalExpense();
		
		return totalContribution - totalExpense;
	}
}
