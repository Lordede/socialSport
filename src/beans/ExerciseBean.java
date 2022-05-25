package beans;
//Erstellt von Cem Durmus
import java.io.Serializable;
import java.util.List;
import java.awt.Image;

public class ExerciseBean implements Serializable
{
	private static final long serialVersionID = 1L;
	
	private Long id;
	private List<SetBean> sets;
	private String name; 
	private enum MuscleGroup 
	{
		BEINMUSKULATUR,
		ARMMUSKULATUR,
		RUECKMUSKULATUR,
		BRUSTMUSKULATUR,
		BAUCHMUSKULATUR,
		SCHULTERMUSKULATUR;
	}
	private Image exerciseImage;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<SetBean> getSets() {
		return sets;
	}
	public void setSets(List<SetBean> sets) {
		this.sets = sets;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getExerciseImage() {
		return exerciseImage;
	}
	public void setExerciseImage(Image exerciseImage) {
		this.exerciseImage = exerciseImage;
	}
	
	
}