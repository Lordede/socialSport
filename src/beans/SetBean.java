package beans;

import java.io.Serializable;
import java.sql.Date;

public class SetBean implements Serializable {
	
	/**
	 * @author Hubertus Seitz
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private double kg;
	private int rep;
	private Long exerciseId;
	private Long trainingId;
	private Date creationDate;
	private Long trainingSessionId;
	
	public Long getTrainingSessionId() {
		return trainingSessionId;
	}
	public void setTrainingSessionId(Long trainingSessionId) {
		this.trainingSessionId = trainingSessionId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}
	public Long getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}
	public SetBean() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getKg() {
		return kg;
	}
	public void setKg(double kg) {
		this.kg = kg;
	}
	public int getRep() {
		return rep;
	}
	public void setRep(int rep) {
		this.rep = rep;
	}
	
	
	

}
