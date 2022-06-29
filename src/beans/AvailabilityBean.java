/*
 * @author Hubertus Seitz
 */

package beans;

import java.io.Serializable;

public class AvailabilityBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean email;
	private boolean username;
	
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public boolean isUsername() {
		return username;
	}
	public void setUsername(boolean username) {
		this.username = username;
	}
	
	

}
