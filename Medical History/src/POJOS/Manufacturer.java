package POJOS;

import java.io.Serializable;
import java.util.Objects;

public class Manufacturer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087629004863498680L;
	private Integer manufacturerID;
	private String manufacturerName;
	public Manufacturer(Integer manufacturerID, String manufacturerName) {
		super();
		this.manufacturerID = manufacturerID;
		this.manufacturerName = manufacturerName;
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
	@Override
	public String toString() {
		return "Manufacturer [manufacturerID=" + manufacturerID + ", manufacturerName=" + manufacturerName + "]";
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
