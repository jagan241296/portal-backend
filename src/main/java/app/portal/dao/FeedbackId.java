package app.portal.dao;

import java.io.Serializable;

import lombok.Data;

@Data
public class FeedbackId implements Serializable {

	private static final long serialVersionUID = 3873155065276652745L;

	private String userId;
	private Long questionId;

}
