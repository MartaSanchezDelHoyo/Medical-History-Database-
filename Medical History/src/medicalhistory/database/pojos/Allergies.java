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
     * @param allergiesID: identification of an allergy
     * @param allergiesName: name of the allergy
     * @param patients: list of patient that has an specific allergy
     */
	private static final long serialVersionUID = 309157577865656134L;
	@XmlElement
	private Integer allergiesID;
	@XmlElement
	private String allergiesName;
	@XmlTransient
	private List <Patient> patients;
	
	/**Constructor with only the name of the allergy
     * @param allergiesName is the name of the allergy
     */
	public Allergies( String allergiesName) {
		this.allergiesName= allergiesName;
		this.patients= new ArrayList<>();
	}
	
	/**Constructor with  the name of the allergy and the id
	 * @param allergiesID is the identification of an allergy
     * @param allergiesName 
     */
	public Allergies(Integer allergiesID, String allergiesName) {
		this.allergiesID= allergiesID;
		this.allergiesName= allergiesName;
		this.patients= new ArrayList<>();
	}
	
	/**Constructor with every attribute
     * @param allergiesID
     * @param allergiesName
     * @param patients is the list of patient that has an specific allergy
     */
	public Allergies(Integer allergiesID, String allergiesName,List <Patient> patients) {
		this.allergiesID= allergiesID;
		this.allergiesName= allergiesName;
		this.patients= patients;
	}
	
	/** Equals method to compare two allergiesID
	 * @param Object 
	 * @return boolean
	 */
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
	    public int hashCode() {
	        return Objects.hash(allergiesID, allergiesName);
	    }

	 /**
	     * Getter and setters of the attributes
	     */
	
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

    public List <Patient> getPatients() {
		return patients;
	}

	public void setPatients(List <Patient> patients) {
		this.patients = patients;
	}
	
	
    @Override
    public String toString() {
        return "AllergiesPOJO{" +
                "allergiesID=" + allergiesID +
                ", allergiesName='" + allergiesName + '\'' +
                '}';
    }
}
