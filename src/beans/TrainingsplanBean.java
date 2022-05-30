package beans;

import java.io.Serializable;
import java.util.List;
//Erstellt von Lukas Edmüller
public class TrainingsplanBean implements Serializable {
	
	private Long id;
	private String name;
	private Long userId;
	private List<TrainingBean> trainings;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TrainingBean> getTrainingList() {
		return trainings;
	}
	public void setTrainingList(List<TrainingBean> trainings) {
		this.trainings = trainings;
	}
	
	
}
