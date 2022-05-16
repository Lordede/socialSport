package beans;

import java.io.Serializable;

public class SetBean implements Serializable {
	
	private Long id;
	private double kg;
	private int rep;
	
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
