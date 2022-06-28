package beans;

import java.io.Serializable;

/*
 *@author Hubertus Seitz
 */
public class LeaderboardBean implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private double points;
	private String username;
	private long id;
	
	public LeaderboardBean() {
		
	}
	
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	

}
