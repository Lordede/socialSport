package trainingControl;
//Erstellt von Cem Durmus
import java.io.Serializable;
import java.util.List;
import java.awt.Image;

public class Exercise_Bean 
{
	private static final long serialVersionID = 1L;
	
	private Integer id;//long ggf
	private List<String> set;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getSet() {
		return set;
	}
	public void setSet(List<String> set) {
		this.set = set;
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
