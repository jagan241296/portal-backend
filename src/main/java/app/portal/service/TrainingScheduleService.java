package app.portal.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.portal.dto.TrainingPlanDto;
import app.portal.helpers.TrainingPlanHelper;
import app.portal.repositories.TrainingPlanRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
@Transactional
public class TrainingScheduleService {

	@Autowired
	private TrainingPlanHelper trainingPlanHelper;
	@Autowired
	private TrainingPlanRepository trainingPlanRepository;

	public List<TrainingPlanDto> getTrainingSchedulePlan(Integer calendarYear) {
		if (calendarYear == null || calendarYear <= 0) {
			log.error("Input year is invalid Cannot get the Trainign plan");
			return Collections.emptyList();
		}

		var entries = getTrainingPlanRepository().findByCalendarYearOrderByDateAscStartTimeAsc(calendarYear);
		return entries.stream().filter(Objects::nonNull).map(i -> getTrainingPlanHelper().convertEntityToDto(i))
				.collect(Collectors.toList());
	}

	public boolean addTrainingPlanAsJson(TrainingPlanDto trainingPlanDto) {
		try {
			if (trainingPlanDto == null)
				return false;

			// check if the entry is already present
			var existingEntry = getTrainingPlanRepository().findByData(trainingPlanDto.getCalendarYear(),
					trainingPlanDto.getDate(), trainingPlanDto.getStartTime(), trainingPlanDto.getEndTime());

			if (existingEntry.isPresent()) {
				log.error("Record already present in DB for given Data: " + trainingPlanDto);
				return false;
			}

			// Else add new entry in DB
			var planEntity = getTrainingPlanHelper().convertDtoToEntity(trainingPlanDto);
			getTrainingPlanRepository().save(planEntity);
		} catch (RuntimeException e) {
			log.error("Error while uploading the Plan: " + e);
			return false;
		}
		return true;
	}
}
