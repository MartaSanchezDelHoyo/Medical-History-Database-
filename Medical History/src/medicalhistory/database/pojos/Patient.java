package medicalhistory.database.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2478862032476138610L;
	private Integer patientID;
	private String sex;
	private String patientName;
	private LocalDate dateofbirth;
	private String bloodtype;
	private Integer allergy_id;
	private String email;
	private List <Allergies> allergies;
	
	public Patient(Integer patientID, String patientName, LocalDate dateofbirth, String bloodtype, Integer allergy_id, String email, List allergies) {
		super();
		this.patientID = patientID;
		this.patientName = patientName;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.allergy_id = allergy_id;
		this.email = email;
		this.allergies = new ArrayList<>();
	}




	public List<Allergies> getAllergies() {
		return allergies;
	}




	public void setAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
	}




	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", patientName=" + patientName + ", dateofbirth=" + dateofbirth
				+ ", bloodtype=" + bloodtype + ", allergy_id=" + allergy_id + ", email=" + email + ", allergies="
				+ allergies + "]";
	}




	@Override
	public int hashCode() {
		return Objects.hash(allergies, allergy_id, bloodtype, dateofbirth, email, patientID, patientName);
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
		return Objects.equals(allergies, other.allergies) && Objects.equals(allergy_id, other.allergy_id)
				&& Objects.equals(bloodtype, other.bloodtype) && Objects.equals(dateofbirth, other.dateofbirth)
				&& Objects.equals(email, other.email) && Objects.equals(patientID, other.patientID)
				&& Objects.equals(patientName, other.patientName);
	}




	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List <Allergies> getAlergies() {
		return alergies;
	}

	public void setAlergies(List <Allergies> alergies) {
		this.alergies = alergies;
	}




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
