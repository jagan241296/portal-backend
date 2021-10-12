package app.portal.dto;

import java.io.Serializable;

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
}
