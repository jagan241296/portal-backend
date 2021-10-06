package app.portal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.portal.dao.TrainingPlan;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, String> {

	List<TrainingPlan> deleteByCalendarYear(String calendarYear);

	List<TrainingPlan> findByCalendarYear(String calendarYear);
}
