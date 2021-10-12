package app.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.portal.dao.QuestionCategory;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, String> {

}
