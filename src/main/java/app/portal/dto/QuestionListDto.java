package app.portal.dto;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QuestionListDto {

	private List<QuestionDto> questions;

	public QuestionListDto() {
		this.questions = Collections.emptyList();
	}
}
