package app.portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.portal.dto.TrainingPlanDto;
import app.portal.service.TrainingScheduleService;
import lombok.Getter;

@RestController
@CrossOrigin
@Getter
@RequestMapping("rest/training")
public class TrainingScheduleController {

	@Autowired
	private TrainingScheduleService trainingScheduleService;

	@GetMapping("/get-training-plan")
	public List<TrainingPlanDto> getTrainingSchedulePlan(@RequestParam("calendarYear") Integer calendarYear) {
		return getTrainingScheduleService().getTrainingSchedulePlan(calendarYear);
	}

	@PostMapping("/add-training-plan")
	public boolean addTrainingPlanAsJson(@RequestBody TrainingPlanDto trainingPlanDtoList) {
		return getTrainingScheduleService().addTrainingPlanAsJson(trainingPlanDtoList);
	}
}
