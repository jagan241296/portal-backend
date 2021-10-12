package app.portal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "question_category")
public class QuestionCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long categoryId;

	@Column
	private String categoryName;
}
