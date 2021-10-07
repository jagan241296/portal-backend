package app.portal.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.portal.dao.TrainingPlan;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {

	List<TrainingPlan> findByCalendarYearOrderByDateAscStartTimeAsc(Integer calendarYear);

	@Query("SELECT td FROM TrainingPlan td WHERE td.calendarYear = :year and td.date = :date and td.startTime = :start_time and td.endTime = :end_time")
	Optional<TrainingPlan> findByData(@Param("year") int year, @Param("date") LocalDate date,
			@Param("start_time") LocalTime startTime, @Param("end_time") LocalTime endTime);
}
