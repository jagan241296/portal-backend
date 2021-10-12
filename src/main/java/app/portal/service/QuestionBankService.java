package app.portal.service;

import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.portal.dto.QuestionDto;
import app.portal.dto.QuestionListDto;
import app.portal.helpers.QuestionBankHelper;
import app.portal.repositories.QuestionBankRepository;
import lombok.Getter;

@Service
@Getter
@Transactional
public class QuestionBankService {

	@Autowired
	private QuestionBankRepository questionBankRepository;
	@Autowired
	private QuestionBankHelper questionBankHelper;

	public QuestionListDto getAllQuestions() {
		var questions = getQuestionBankRepository().findAll();
		var listOfQuestions = questions.stream().map(getQuestionBankHelper()::converyEntityToDto)
				.collect(Collectors.toList());
		var questionsListDto = new QuestionListDto();
		questionsListDto.setQuestions(listOfQuestions);
		return questionsListDto;
	}

	public QuestionDto getQuestionById(String questionIdStr) {
		long questionId = Long.parseLong(questionIdStr);

		if (questionId <= 0)
			return null;

		var existingQuestionOpt = getQuestionBankRepository().findById(questionId);
		if (existingQuestionOpt.isEmpty())
			return null;
		return getQuestionBankHelper().converyEntityToDto(existingQuestionOpt.get());
	}

	public boolean addQuestionToDb(QuestionDto questionDto) {
		if (questionDto == null)
			return false;

		var questionBankDaoOpt = getQuestionBankHelper().convertDtoToEntity(questionDto);
		if (questionBankDaoOpt.isEmpty())
			return false;

		// check if same question is already present
		var questionBankDao = questionBankDaoOpt.get();
		if (questionBankDao.getQuestionCategory() != null) {
			var existingQuestionOpt = getQuestionBankRepository().findByQuestionTextAndQuestionCategoryCategoryName(
					questionBankDao.getQuestionText(), questionBankDao.getQuestionCategory().getCategoryName());
			if (existingQuestionOpt.isPresent())
				return false;
		}

		getQuestionBankRepository().save(questionBankDao);
		return true;
	}

	public QuestionDto deleteQuestionFromDB(String questionIdStr) {
		long questionId = Long.parseLong(questionIdStr);

		if (questionId <= 0)
			return null;

		var existingQuestionOpt = getQuestionBankRepository().findById(questionId);
		if (existingQuestionOpt.isEmpty())
			return null;

		getQuestionBankRepository().deleteById(questionId);
		return getQuestionBankHelper().converyEntityToDto(existingQuestionOpt.get());
	}
}
