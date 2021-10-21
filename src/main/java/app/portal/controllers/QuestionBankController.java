package app.portal.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.portal.dto.QuestionDto;
import app.portal.dto.QuestionListDto;
import app.portal.service.QuestionBankService;
import lombok.Getter;

@RestController
@Getter
@CrossOrigin
@RequestMapping("rest/question")
public class QuestionBankController {

	@Autowired
	private QuestionBankService questionBankService;

	@GetMapping("/get-questions")
	public QuestionListDto getAllQuestions() {
		return getQuestionBankService().getAllQuestions();
	}

	@GetMapping("/get-question")
	public QuestionDto getQuestion(@RequestParam("questionId") String questionId) {
		if (!StringUtils.isNumeric(questionId))
			return null;

		return getQuestionBankService().getQuestionById(questionId);
	}

	@PostMapping("/add-question")
	public boolean addQuestion(@RequestBody QuestionDto questionDto) {
		return getQuestionBankService().addQuestionToDb(questionDto);
	}

	@DeleteMapping("/delete-question")
	public QuestionDto deleteQuestion(@RequestParam("questionId") String questionId) {
		if (!StringUtils.isNumeric(questionId))
			return null;

		return getQuestionBankService().deleteQuestionFromDB(questionId);
	}
}
