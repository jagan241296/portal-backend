package app.portal.service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import app.portal.dto.QuestionDto;
import app.portal.dto.QuestionListDto;
import app.portal.dto.UserDto;
import app.portal.helpers.FeedbackHelper;
import app.portal.helpers.QuestionBankHelper;
import app.portal.helpers.UserHelper;
import app.portal.repositories.QuestionBankRepository;
import app.portal.repositories.UserFeedbackRepository;
import app.portal.repositories.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
public class UserService {

	@Autowired
	private UserHelper userHelper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserFeedbackRepository userFeedbackRepository;
	@Autowired
	private FeedbackHelper feedbackHelper;
	@Autowired
	private QuestionBankHelper questionBankHelper;
	@Autowired
	private QuestionBankRepository questionBankRepository;

	public UserDto validateLogin(UserDto userDto) {
		var responseDto = new UserDto();

		if (userDto == null || StringUtils.isBlank(userDto.getUserName())
				|| StringUtils.isBlank(userDto.getPassword())) {
			log.error("User Details are empty.");
			return responseDto;
		}

		// Check if credentials are correct
		var existingUserOpt = getUserRepository().findByUserNameAndPassword(userDto.getUserName(),
				userDto.getPassword());
		if (existingUserOpt.isEmpty()) {
			return responseDto;
		}

		var response = getUserHelper().convertUserEntityToDto(existingUserOpt.get());
		response.setLoginValid(true);
		return response;
	}

	public boolean updateProfileDetails(UserDto userDto) {
		if (userDto == null || StringUtils.isBlank(userDto.getUserId()))
			return false;

		var existingUser = getUserRepository().findById(userDto.getUserId());
		if (existingUser.isEmpty()) {
			log.error("Cannot find user with given ID. Unable to update the profile details");
			return false;
		}

		try {
			var userDao = existingUser.get();
			userDao.setFirstName(userDto.getFirstName());
			userDao.setLastName(userDto.getLastName());
			getUserRepository().save(userDao);
		} catch (RuntimeException e) {
			log.error("Error while updating the profile details: " + e);
			return false;
		}
		return true;
	}

	public UserDto getProfileDetails(String employeeId) {
		if (StringUtils.isBlank(employeeId))
			return null;

		var employeeDetailsOpt = getUserRepository().findById(employeeId);
		if (employeeDetailsOpt.isEmpty()) {
			log.error("Cannot find profile details for given ID: " + employeeId);
			return null;
		}
		return getUserHelper().convertUserEntityToDto(employeeDetailsOpt.get());
	}

	public QuestionListDto getFeedbackQuestionByuser(String userId) {
		if (StringUtils.isBlank(userId))
			return new QuestionListDto();

		/*
		 * Check if the user already has given the feedback, If yes, return the given
		 * feedback for editing
		 */
		var existingQuestionsForUser = getUserFeedbackRepository().findByUserId(userId);
		if (!existingQuestionsForUser.isEmpty()) {
			// return existing questions with answer
			var questionListDto = new QuestionListDto();
			questionListDto.setQuestions(existingQuestionsForUser.stream()
					.map(getFeedbackHelper()::convertFeedbackDaoToQuestionDto).collect(Collectors.toList()));
			return questionListDto;
		}

		// Get the questions based on user type and send the list for getting the
		// answers
		var userDetailsOpt = getUserRepository().findById(userId);
		if (userDetailsOpt.isEmpty())
			return new QuestionListDto();

		// Get questions for the user
		var questionsByType = getQuestionBankRepository()
				.findByVisibilityType(Integer.parseInt(userDetailsOpt.get().getUserType()));
		if (questionsByType.isEmpty())
			return new QuestionListDto();

		var questionListDto = new QuestionListDto();
		questionListDto.setQuestions(
				questionsByType.stream().map(getQuestionBankHelper()::converyEntityToDto).collect(Collectors.toList()));
		return questionListDto;
	}

	public boolean saveUserFeedback(List<QuestionDto> questionsDto) {
		if (CollectionUtils.isEmpty(questionsDto))
			return false;

		/*
		 * extract the questions and answers to save in DB - Even if one question is not
		 * valid, feedback is not persisted
		 */

		// find for any Invalid questions
		if (questionsDto.stream().anyMatch(isInvalidQuestion))
			return false;

		// save in DB
		var userFeedbacks = questionsDto.stream().map(getFeedbackHelper()::convertQuestionDtoToFeedbackDao)
				.filter(Objects::nonNull).collect(Collectors.toList());
		getUserFeedbackRepository().saveAll(userFeedbacks);
		return true;
	}

	Predicate<QuestionDto> isInvalidQuestion = dto -> {

		// check if the question is present in DB
		var questionOpt = getQuestionBankRepository().findById(dto.getQuestionId());
		return questionOpt.isEmpty();
	};
}
