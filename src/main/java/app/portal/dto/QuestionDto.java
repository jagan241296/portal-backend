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
public class QuestionDto implements Serializable {

	private static final long serialVersionUID = 6709979188051983408L;

	private Long questionId;
	private QuestionCategoryDto questionCategory;
	private int visibilityType;
	private String questionText;
	private QuestionOptionsDto questionOptions;
	private String userChoice;
	private String comment;

	private String userId;
}
