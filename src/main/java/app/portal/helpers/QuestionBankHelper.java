package app.portal.helpers;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import app.portal.dao.QuestionBank;
import app.portal.dao.QuestionCategory;
import app.portal.dao.QuestionOptions;
import app.portal.dto.QuestionCategoryDto;
import app.portal.dto.QuestionDto;
import app.portal.dto.QuestionOptionsDto;

@Component
public class QuestionBankHelper {

	public QuestionDto converyEntityToDto(QuestionBank question) {
		if (question == null)
			return new QuestionDto();

		var questionDto = new QuestionDto();
		questionDto.setQuestionId(question.getQuestionId());
		questionDto.setVisibilityType(question.getVisibilityType());
		questionDto.setQuestionText(question.getQuestionText());

		var categoryDto = new QuestionCategoryDto();
		categoryDto.setCategoryId(question.getQuestionCategory().getCategoryId());
		categoryDto.setCategoryName(question.getQuestionCategory().getCategoryName());
		questionDto.setQuestionCategory(categoryDto);

		var questionOptionsDto = new QuestionOptionsDto();
		questionOptionsDto.setId(question.getQuestionOptions().getId());
		questionOptionsDto.setOptionType(question.getQuestionOptions().getOptionType());
		questionOptionsDto.setOption1(question.getQuestionOptions().getOption1());
		questionOptionsDto.setOption2(question.getQuestionOptions().getOption2());
		questionOptionsDto.setOption3(question.getQuestionOptions().getOption3());
		questionOptionsDto.setOption4(question.getQuestionOptions().getOption4());
		questionOptionsDto.setOption5(question.getQuestionOptions().getOption5());
		questionDto.setQuestionOptions(questionOptionsDto);

		return questionDto;
	}

	public Optional<QuestionBank> convertDtoToEntity(QuestionDto questionDto) {
		if (questionDto == null)
			return Optional.empty();

		if (StringUtils.trimToNull(questionDto.getQuestionText()) == null)
			return Optional.empty();

		var questionBankDao = new QuestionBank();
		questionBankDao.setQuestionText(StringUtils.trim(questionDto.getQuestionText()));
		questionBankDao.setVisibilityType(questionDto.getVisibilityType());

		// set category
		if (questionDto.getQuestionCategory() != null) {
			var questionCategory = new QuestionCategory();
			questionCategory.setCategoryName(StringUtils.trim(questionDto.getQuestionCategory().getCategoryName()));
			questionBankDao.setQuestionCategory(questionCategory);
		}

		// set options
		if (questionDto.getQuestionOptions() != null) {
			var questionOptions = new QuestionOptions();
			questionOptions.setOptionType(questionDto.getQuestionOptions().getOptionType());
			questionOptions.setOption1(StringUtils.trim(questionDto.getQuestionOptions().getOption1()));
			questionOptions.setOption2(StringUtils.trim(questionDto.getQuestionOptions().getOption2()));
			questionOptions.setOption3(StringUtils.trim(questionDto.getQuestionOptions().getOption3()));
			questionOptions.setOption4(StringUtils.trim(questionDto.getQuestionOptions().getOption4()));
			questionOptions.setOption5(StringUtils.trim(questionDto.getQuestionOptions().getOption5()));
			questionBankDao.setQuestionOptions(questionOptions);
		}
		return Optional.of(questionBankDao);
	}
}
