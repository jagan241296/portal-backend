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
public class QuestionCategoryDto implements Serializable {

	private static final long serialVersionUID = -7355098171521600824L;

	private Long categoryId;
	private String categoryName;
}
