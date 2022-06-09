package beans;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

//Erstellt von Cem Durmus
public class TrainingBean implements Serializable 
{
	private static final long serialVersionID = 1L;
	
	private Long id;
	private String name;
	private double points;
	private Long userId;
	private Date creationDate;
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	
}
