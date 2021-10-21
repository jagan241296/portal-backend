package app.portal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.portal.dao.QuestionBank;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Long> {

	Optional<QuestionBank> findByQuestionTextAndQuestionCategoryCategoryName(String questionText, String categoryName);

	List<QuestionBank> findByVisibilityType(int visibilityType);

}
