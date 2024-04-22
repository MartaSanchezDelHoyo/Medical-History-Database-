package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Manufacturer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087629004863498680L;
	private Integer manufacturerID;
	private String manufacturerName;
	private List <Medication> medications;
	
	public Manufacturer(Integer manufacturerID, String manufacturerName, ArrayList medications) {
		super();
		this.manufacturerID = manufacturerID;
		this.manufacturerName = manufacturerName;
		this.medications= new ArrayList<>();
	}


	@Override
	public String toString() {
		return "Manufacturer [manufacturerID=" + manufacturerID + ", manufacturerName=" + manufacturerName + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(manufacturerID, manufacturerName);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(manufacturerID, other.manufacturerID)
				&& Objects.equals(manufacturerName, other.manufacturerName);
	}


	public Integer getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(Integer manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	
}
