package trainingControl;

import java.io.Serializable;
import java.util.List;

//Erstellt von Cem Durmus
public class Training_Bean implements Serializable 
{
	private static final long serialVersionID = 1L;
	
	private Integer id;
	private String name;
	private List<Exercise_Bean> exercise;
	private Integer points;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Exercise_Bean> getExercise() {
		return exercise;
	}
	public void setExercise(List<Exercise_Bean> exercise) {
		this.exercise = exercise;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
