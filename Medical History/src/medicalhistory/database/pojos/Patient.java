package medicalhistory.database.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 
 */
public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2478862032476138610L;
	private Integer patientID;
	private String sex;
	private String patientName;
	private Date dateofbirth;
	private String bloodtype;
	private String email;
	private List <Allergies> allergies;
	private List <Visit> visits ;
	private List <Doctor> doctors;
	
	public Patient(Integer patientID, String patientName, String Sex, Date dateofbirth, String bloodtype, String email, List<Allergies> allergies, List <Visit> visits, List <Doctor> doctors, String sex) {
		super();
		this.patientID = patientID;
		this.patientName = patientName;
		this.sex = sex;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.allergies = new ArrayList<>();
		this.visits = new ArrayList<>();
		this.doctors = new ArrayList<>();
	}

	public Patient(int patientId2, String patientName2, String sex2, Date dateOfBirth2, String email2) {
		this.patientID = patientID;
		this.patientName = patientName;
		this.sex = sex;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
	}

	public List<Allergies> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<Allergies> allergies) {
		this.allergies = allergies;
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
	
	public Date getDateofbirth() {
		return dateofbirth;
	}
	
	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
	public String getBloodtype() {
		return bloodtype;
	}
	
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	
	public List <Allergies> getAlergies() {
		return allergies;
	}

	public void setAlergies(List <Allergies> allergies) {
		this.allergies = allergies;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List <Visit> getVisits() {
		return visits;
	}

	public void setVisits(List <Visit> visits) {
		this.visits = visits;
	}

	public List <Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List <Doctor> doctors) {
		this.doctors = doctors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allergies, bloodtype, dateofbirth, doctors, email, patientID, patientName, sex, visits);
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
		return Objects.equals(allergies, other.allergies) && Objects.equals(bloodtype, other.bloodtype)
				&& Objects.equals(dateofbirth, other.dateofbirth) && Objects.equals(doctors, other.doctors)
				&& Objects.equals(email, other.email) && Objects.equals(patientID, other.patientID)
				&& Objects.equals(patientName, other.patientName) && Objects.equals(sex, other.sex)
				&& Objects.equals(visits, other.visits);
	}

	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", sex=" + sex + ", patientName=" + patientName + ", dateofbirth="
				+ dateofbirth + ", bloodtype=" + bloodtype + ", email=" + email + ", allergies=" + allergies
				+ ", visits=" + visits + ", doctors=" + doctors + "]";
	}
	
	
}
