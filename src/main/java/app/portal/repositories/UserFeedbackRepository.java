package app.portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.portal.dao.FeedbackId;
import app.portal.dao.UserFeedback;

public interface UserFeedbackRepository extends JpaRepository<UserFeedback, FeedbackId> {

	List<UserFeedback> findByUserId(String userId);

}
