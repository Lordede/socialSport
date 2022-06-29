// Autor: Lukas Edmüller
package beans;

import java.io.Serializable;

public class ExerciseToTrainingBean implements Serializable
{
	private static final long serialVersionID = 1L;
	
	private long exerciseId;
	private long trainingId;
	public long getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}
	public long getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(long trainingId) {
		this.trainingId = trainingId;
	}
	

}
