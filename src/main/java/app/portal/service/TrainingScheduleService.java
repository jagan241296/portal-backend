package app.portal.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
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
public class TrainingScheduleService {

	@Autowired
	private TrainingPlanHelper trainingPlanHelper;
	@Autowired
	private TrainingPlanRepository trainingPlanRepository;

	public List<TrainingPlanDto> getTrainingSchedulePlan(String calendarYear) {
		if (StringUtils.isBlank(calendarYear) || !StringUtils.isNumeric(calendarYear)) {
			log.error("Input year is empty. Cannot get the Trainign plan");
			return Collections.emptyList();
		}

		var entries = getTrainingPlanRepository().findByCalendarYear(calendarYear);
		return entries.stream().filter(Objects::nonNull).map(i -> getTrainingPlanHelper().convertEntityToDto(i))
				.collect(Collectors.toList());
	}

	public boolean addTrainingPlanAsJson(List<TrainingPlanDto> trainingPlanDtoList) {
		try {
			trainingPlanDtoList.stream().filter(i -> StringUtils.isNotBlank(i.getCalendarYear())).findFirst()
					.ifPresent(i -> getTrainingPlanRepository().deleteByCalendarYear(i.getCalendarYear()));

			trainingPlanDtoList.stream().map(i -> getTrainingPlanHelper().convertDtoToEntity(i))
					.forEach(i -> getTrainingPlanRepository().save(i));
		} catch (RuntimeException e) {
			log.error("Error while uploading the Plan: " + e);
			return false;
		}
		return true;
	}
}
