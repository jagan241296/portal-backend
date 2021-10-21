package app.portal.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.portal.dao.UserFeedback;
import app.portal.dto.QuestionCategoryDto;
import app.portal.dto.QuestionDto;
import app.portal.dto.QuestionOptionsDto;
import app.portal.repositories.QuestionBankRepository;
import lombok.Getter;

@Component
@Getter
public class FeedbackHelper {

	@Autowired
	private QuestionBankRepository questionBankRepository;

	public QuestionDto convertFeedbackDaoToQuestionDto(UserFeedback userFeedback) {
		if (userFeedback == null)
			return new QuestionDto();

		var questionDto = new QuestionDto();
		var questionDetailsOpt = getQuestionBankRepository().findById(userFeedback.getQuestionId());
		if (questionDetailsOpt.isEmpty())
			return new QuestionDto();

		var questionDetails = questionDetailsOpt.get();
		questionDto.setQuestionId(questionDetails.getQuestionId());
		questionDto.setQuestionText(questionDetails.getQuestionText());
		questionDto.setVisibilityType(questionDetails.getVisibilityType());
		questionDto.setQuestionCategory(new QuestionCategoryDto(questionDetails.getQuestionCategory()));
		questionDto.setQuestionOptions(new QuestionOptionsDto(questionDetails.getQuestionOptions()));
		questionDto.setUserChoice(userFeedback.getAnswer());
		questionDto.setComment(userFeedback.getComment());
		return questionDto;
	}

	public UserFeedback convertQuestionDtoToFeedbackDao(QuestionDto questionDto) {
		if (questionDto == null)
			return null;

		var feedbackDao = new UserFeedback();
		feedbackDao.setUserId(questionDto.getUserId());
		feedbackDao.setQuestionId(questionDto.getQuestionId());
		feedbackDao.setAnswer(questionDto.getUserChoice());
		feedbackDao.setComment(questionDto.getComment());
		return feedbackDao;
	}
}
