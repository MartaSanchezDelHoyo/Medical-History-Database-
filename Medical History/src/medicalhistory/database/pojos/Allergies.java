package medicalhistory.database.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table (name= "allergies")
@XmlAccessorType(XmlAccessType.FIELD)
public class Allergies implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 309157577865656134L;
	@XmlElement
	private Integer allergiesID;
	@XmlElement
	private String allergiesName;
	@XmlTransient
	private List <Patient> patients;
	
	public Allergies( String allergiesName) {
		this.allergiesName= allergiesName;
		this.patients= new ArrayList<>();
	}
	
	public Allergies(Integer allergiesID, String allergiesName) {
		this.allergiesID= allergiesID;
		this.allergiesName= allergiesName;
		this.patients= new ArrayList<>();
	}
	
	public Allergies(Integer allergiesID, String allergiesName,List <Patient> patients) {
		this.allergiesID= allergiesID;
		this.allergiesName= allergiesName;
		this.patients= patients;
	}
	
    public Integer getAllergiesID() {
        return allergiesID;
    }

    public void setAllergiesID(Integer allergiesID) {
        this.allergiesID = allergiesID;
    }

    public String getAllergiesName() {
        return allergiesName;
    }

    public void setAllergiesName(String allergiesName) {
        this.allergiesName = allergiesName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(allergiesID, allergiesName);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Allergies other = (Allergies) obj;
		if (allergiesID == null) {
			if (other.allergiesID != null)
				return false;
		} else if (!allergiesID.equals(other.allergiesID))
			return false;
		return true;
	}
	
    @Override
    public String toString() {
        return "AllergiesPOJO{" +
                "allergiesID=" + allergiesID +
                ", allergiesName='" + allergiesName + '\'' +
                '}';
    }

	public List <Patient> getPatients() {
		return patients;
	}

	public void setPatients(List <Patient> patients) {
		this.patients = patients;
	}
}
