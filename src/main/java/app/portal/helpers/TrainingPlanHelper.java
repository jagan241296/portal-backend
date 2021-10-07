package app.portal.helpers;

import org.springframework.stereotype.Component;

import app.portal.dao.TrainingPlan;
import app.portal.dto.TrainingPlanDto;
import lombok.Getter;

@Component
@Getter
public class TrainingPlanHelper {

	public TrainingPlanDto convertEntityToDto(TrainingPlan trainingPlan) {
		if (trainingPlan == null)
			return new TrainingPlanDto();

		var trainingPlanDto = new TrainingPlanDto();
		trainingPlanDto.setCalendarYear(trainingPlan.getCalendarYear());
		trainingPlanDto.setDate(trainingPlan.getDate());
		trainingPlanDto.setStartTime(trainingPlan.getStartTime());
		trainingPlanDto.setEndTime(trainingPlan.getEndTime());
		trainingPlanDto.setTrainingHead(trainingPlan.getTrainingHead());
		trainingPlanDto.setSession(trainingPlan.getSession());
		trainingPlanDto.setPresenter(trainingPlan.getPresenter());
		trainingPlanDto.setLocation(trainingPlan.getLocation());
		trainingPlanDto.setSessionMaterial(trainingPlan.getSessionMaterial());
		trainingPlanDto.setParticipants(trainingPlan.getParticipants());

		return trainingPlanDto;
	}

	public TrainingPlan convertDtoToEntity(TrainingPlanDto dto) {
		if (dto == null)
			return null;

		var trainingPlan = new TrainingPlan();
		trainingPlan.setCalendarYear(dto.getCalendarYear());
		trainingPlan.setDate(dto.getDate());
		trainingPlan.setStartTime(dto.getStartTime());
		trainingPlan.setEndTime(dto.getEndTime());
		trainingPlan.setTrainingHead(dto.getTrainingHead());
		trainingPlan.setSession(dto.getSession());
		trainingPlan.setPresenter(dto.getPresenter());
		trainingPlan.setLocation(dto.getLocation());
		trainingPlan.setSessionMaterial(dto.getSessionMaterial());
		trainingPlan.setParticipants(dto.getParticipants());

		return trainingPlan;
	}
}
