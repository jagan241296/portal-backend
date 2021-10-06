package app.portal.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TrainingPlanDto {

	private String calendarYear;
	private String date;
	private String time;
	private String trainingHead;
	private String session;
	private String presenter;
	private String location;
	private String sessionMaterial;
	private String participants;
}
