package app.portal.dto;

import java.io.Serializable;

import app.portal.dao.QuestionCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuestionCategoryDto implements Serializable {

	private static final long serialVersionUID = -7355098171521600824L;

	private Long categoryId;
	private String categoryName;

	public QuestionCategoryDto(QuestionCategory categoryDao) {
		if (categoryDao != null) {
			this.categoryId = categoryDao.getCategoryId();
			this.categoryName = categoryDao.getCategoryName();
		}
	}
}
