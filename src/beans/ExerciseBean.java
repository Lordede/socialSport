package beans;
//Erstellt von Cem Durmus
import java.io.Serializable;
import java.sql.Blob;
import java.util.List;
import java.awt.Image;

public class ExerciseBean implements Serializable
{
	private static final long serialVersionID = 1L;
	
	private Long id;//long ggf
//	private List<SetBean> sets;
	private String name;
	//private double point;
	private String muscleGroup;
//	private enum MuscleGroup 
//	{
//		BEINMUSKULATUR,
//		ARMMUSKULATUR,
//		RUECKMUSKULATUR,
//		BRUSTMUSKULATUR,
//		BAUCHMUSKULATUR,
//		SCHULTERMUSKULATUR;
//	}

	private String exerciseImage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	public List<SetBean> getSets() {
//		return sets;
//	}
//	public void setSets(List<SetBean> sets) {
//		this.sets = sets;
//	}
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
