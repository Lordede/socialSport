package Training;

import java.io.Serializable;

public class SatzBean implements Serializable {
	
	private int kg;
	private int whd;
	
	public SatzBean() {
		super();
	}
	public int getKg() {
		return kg;
	}
	public void setKg(int kg) {
		this.kg = kg;
	}
	public int getWhd() {
		return whd;
	}
	public void setWhd(int whd) {
		this.whd = whd;
	}
	
	

}
