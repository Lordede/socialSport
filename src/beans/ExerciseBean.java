package beans;
//Erstellt von Cem Durmus
import java.io.Serializable;
import java.util.Date;
import java.sql.Blob;
import java.util.List;
import java.awt.Image;

public class ExerciseBean implements Serializable
{
	private static final long serialVersionID = 1L;
	
	private Long id;

	private String name;

	private String muscleGroup;



	private String exerciseImage;
	
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
	public String getExerciseImage() {
		return exerciseImage;
	}
	public void setExerciseImage(String exerciseImage) {
		this.exerciseImage = exerciseImage;
	}
	
//	public double getPoint() {
//		return point;
//	}
//	public void setPoint(double point) {
//		this.point = point;
//	}
	public String getMuscleGroup() {
		return muscleGroup;
	}
	public void setMuscleGroup(String muscleGroup) {
		this.muscleGroup = muscleGroup;
	}
	
}
