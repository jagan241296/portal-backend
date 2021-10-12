package app.portal.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "question_bank")
public class QuestionBank {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long questionId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "categoryId")
	private QuestionCategory questionCategory;

	/*
	 * Type: 0 - Fresher / 1 - Evaluator
	 */
	@Column(name = "visibility_type")
	private int visibilityType;

	@Column(name = "question_text")
	private String questionText;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "option_id", referencedColumnName = "id")
	private QuestionOptions questionOptions;
}
