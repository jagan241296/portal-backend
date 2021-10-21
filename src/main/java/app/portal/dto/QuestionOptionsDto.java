package app.portal.dto;

import java.io.Serializable;

import app.portal.dao.QuestionOptions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class QuestionOptionsDto implements Serializable {

	private static final long serialVersionUID = -2088354406330565227L;

	private Long id;
	private int optionType;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;

	public QuestionOptionsDto(QuestionOptions dao) {
		if (dao != null) {
			this.id = dao.getId();
			this.optionType = dao.getOptionType();
			this.option1 = dao.getOption1();
			this.option2 = dao.getOption2();
			this.option3 = dao.getOption3();
			this.option4 = dao.getOption4();
			this.option5 = dao.getOption5();
		}
	}
}
