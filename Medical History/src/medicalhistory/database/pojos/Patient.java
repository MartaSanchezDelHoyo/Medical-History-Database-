package medicalhistory.database.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2478862032476138610L;
	private Integer patientID;
	private String patientName;
	private LocalDate dateofbirth;
	private String bloodtype;
	private Integer allergy_id;
	
	public Patient(Integer patientID, String patientName, LocalDate dateofbirth, String bloodtype, Integer allergy_id) {
		super();
		this.patientID = patientID;
		this.patientName = patientName;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.allergy_id = allergy_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergy_id, bloodtype, patientID, patientName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patient other = (Patient) obj;
		return Objects.equals(allergy_id, other.allergy_id) && Objects.equals(bloodtype, other.bloodtype)
				&& Objects.equals(patientID, other.patientID) && Objects.equals(patientName, other.patientName);
	}
	
	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", patientName=" + patientName + ", bloodtype=" + bloodtype
				+ ", allergy_id=" + allergy_id + "]";
	}

	public Integer getPatientID() {
		return patientID;
	}
	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public LocalDate getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	public Integer getAllergy_id() {
		return allergy_id;
	}
	public void setAllergy_id(Integer allergy_id) {
		this.allergy_id = allergy_id;
	}
	
	
}
