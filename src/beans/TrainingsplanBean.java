package beans;

import java.io.Serializable;
import java.util.List;

import trainingControl.Training_Bean;
//Erstellt von Lukas Edmüller
public class TrainingsplanBean implements Serializable {
	
	private Long id;
	private String name;
	private List<Training_Bean> trainingList;
	
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
	public List<Training_Bean> getTrainingList() {
		return trainingList;
	}
	public void setTrainingList(List<Training_Bean> trainingList) {
		this.trainingList = trainingList;
	}
	
	
}
