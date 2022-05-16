package beans;

import java.io.Serializable;
import java.util.List;
//Erstellt von Lukas Edmüller
public class TrainingsplanBean implements Serializable {
	
	private Long id;
	private String name;
	private List<TrainingBean> trainingList;
	
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
		return trainingList;
	}
	public void setTrainingList(List<TrainingBean> trainingList) {
		this.trainingList = trainingList;
	}
	
	
}
