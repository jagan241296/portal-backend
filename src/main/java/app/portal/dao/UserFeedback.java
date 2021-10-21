package app.portal.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
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
@IdClass(FeedbackId.class)
@Table(name = "user_feedback")
public class UserFeedback implements Serializable {

	private static final long serialVersionUID = -4121118012886497661L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Id
	@Column(name = "question_id")
	private Long questionId;

	@Column
	private String answer;

	@Column
	private String comment;
}
