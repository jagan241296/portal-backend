package app.portal.dao;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "training_plan")
public class TrainingPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	@Column(name = "calendar_year")
	private int calendarYear;
	@Column(name = "training_date")
	private LocalDate date;
	@Column(name = "start_time")
	private LocalTime startTime;
	@Column(name = "end_time")
	private LocalTime endTime;
	@Column(name = "training_head")
	private String trainingHead;
	@Column(name = "session")
	private String session;
	@Column(name = "presenter")
	private String presenter;
	@Column(name = "location")
	private String location;
	@Column(name = "session_material")
	private String sessionMaterial;
	@Column(name = "participants")
	private String participants;

}
