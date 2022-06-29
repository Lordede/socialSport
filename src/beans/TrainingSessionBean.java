// Autor: Lukas Edmüller
package beans;

import java.io.Serializable;
import java.sql.Date;

public class TrainingSessionBean implements Serializable 
{
	private static final long serialVersionID = 1L;
	
	private Long id;
	private Date creationDate;
	private Long trainingId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}
}
