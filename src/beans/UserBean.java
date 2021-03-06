package beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;

import java.awt.Image;
/**
 * @author Cem Durmus
 * */

public class UserBean implements Serializable 
{
	private static final long serialVersionID = 1L;
	
	private Long id;
	private List<String> trainingsplan;
	private String username;
	private String firstName;
	private String lastName;
	private String eMail;
	private String password;
	private Image profilePicture;
	private boolean admin;
	private Date creationDate;
		
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<String> getTrainingsplan() {
		return trainingsplan;
	}
	public void setTrainingsplan(List<String> trainingsplan) {
		this.trainingsplan = trainingsplan;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Image getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(Image profilePicture) {
		this.profilePicture = profilePicture;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.admin = isAdmin;
	}

}	
	