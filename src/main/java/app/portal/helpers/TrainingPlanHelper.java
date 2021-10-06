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
		trainingPlan.setCalendarYear(trainingPlan.getCalendarYear());
		trainingPlan.setDate(trainingPlan.getDate());
		trainingPlan.setTime(trainingPlan.getTime());
		trainingPlan.setTrainingHead(trainingPlan.getTrainingHead());
		trainingPlan.setSession(trainingPlan.getSession());
		trainingPlan.setPresenter(trainingPlan.getPresenter());
		trainingPlan.setLocation(trainingPlan.getLocation());
		trainingPlan.setSessionMaterial(trainingPlan.getSessionMaterial());
		trainingPlan.setParticipants(trainingPlan.getParticipants());

		return trainingPlanDto;
	}

	public TrainingPlan convertDtoToEntity(TrainingPlanDto dto) {
		if (dto == null)
			return null;

		var trainingPlan = new TrainingPlan();
		trainingPlan.setCalendarYear(dto.getCalendarYear());
		trainingPlan.setDate(dto.getDate());
		trainingPlan.setTime(dto.getTime());
		trainingPlan.setTrainingHead(dto.getTrainingHead());
		trainingPlan.setSession(dto.getSession());
		trainingPlan.setPresenter(dto.getPresenter());
		trainingPlan.setLocation(dto.getLocation());
		trainingPlan.setSessionMaterial(dto.getSessionMaterial());
		trainingPlan.setParticipants(dto.getParticipants());

		return trainingPlan;
	}
}
