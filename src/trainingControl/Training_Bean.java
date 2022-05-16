package trainingControl;

import java.io.Serializable;
import java.util.List;

//Erstellt von Cem Durmus
public class Training_Bean implements Serializable 
{
	private static final long serialVersionID = 1L;
	
	private Long id;
	private String name;
	private List<Exercise_Bean> exercise;
	private double points;
	
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
	public List<Exercise_Bean> getExercise() {
		return exercise;
	}
	public void setExercise(List<Exercise_Bean> exercise) {
		this.exercise = exercise;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	
}
