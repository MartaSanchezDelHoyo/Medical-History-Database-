package medicalhistory.database.pojos;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import medicalhistory.database.xml.utils.SQLDateAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name= "Patient")
@XmlType (propOrder = { "patientName", "allergies", "doctors", "vistis"})
public class Patient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2478862032476138610L;
	@XmlAttribute
	private Integer patientID;
	@XmlElement
	private String patientName;
	@XmlAttribute
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dateofbirth;
	@XmlAttribute
	private String bloodtype;
	@XmlAttribute
	private String email;
	@XmlTransient
	private byte[] photo;
	@XmlAttribute
	private String username;
	@XmlElement(name= "Allergies")
	@XmlElementWrapper(name = "allergies")
	private List <Allergies> allergies;
	@XmlElement(name= "Visit")
	@XmlElementWrapper(name = "visits")
	private List <Visit> visits ;
	@XmlElement(name= "Doctor")
	@XmlElementWrapper(name = "Doctors")
	private List <Doctor> doctors;

	
	public Patient() {
		super();
	}


	public Patient(String patientName, Date dateofbirth, String bloodtype, String email, byte[]photo, String username) {
		this.patientName = patientName;
		this.username=username;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo= photo;
		this.allergies = new ArrayList<>();
		this.visits = new ArrayList<>();
		this.doctors = new ArrayList<>();
	}
	
	
	public Patient(Integer patientID, String patientName, Date dateofbirth, String bloodtype, String email, byte[]photo, String username) {
		this.patientID=patientID;
		this.patientName = patientName;
		this.username=username;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo= photo;
		this.allergies = new ArrayList<>();
		this.visits = new ArrayList<>();
		this.doctors = new ArrayList<>();
	
	}

	public Patient(Integer patientID, String patientName, Date dateofbirth, String bloodtype, String email, byte[]photo, String username, List<Allergies> allergies) {
		this.patientID=patientID;
		this.patientName = patientName;
		this.username=username;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo= photo;
		this.allergies = allergies;
		this.visits = new ArrayList<>();
		this.doctors = new ArrayList<>();
	
	}
	
	public Patient(Integer patientID, String patientName, Date dateofbirth, String bloodtype, String email, byte[]photo, String username,List<Allergies> allergies, List <Visit> visits, List <Doctor> doctors) {
		this.patientID=patientID;
		this.patientName = patientName;
		this.username=username;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo= photo;
		this.allergies = allergies;
		this.visits = visits;
		this.doctors =doctors;
	}

	
	public Patient(Integer patientID, String patientName, Date dateofbirth, String bloodtype, String email, byte[]photo) {
		this.patientID=patientID;
		this.patientName = patientName;
		this.username=username;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo= photo;
		this.allergies = allergies;
		this.visits = visits;
		this.doctors =doctors;
	}
	


	public Patient(String patientName, Date dateofbirth, String bloodtype, String email, byte[] photo,
			List<Allergies> allergies, List<Doctor> doctors, String username) {
		super();
		this.patientName = patientName;
		this.dateofbirth = dateofbirth;
		this.bloodtype = bloodtype;
		this.email = email;
		this.photo = photo;
		this.username = username;
		this.allergies = allergies;
		this.doctors = doctors;
	}


	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", patientName=" + patientName + ", dateofbirth=" + dateofbirth
				+ ", bloodtype=" + bloodtype + ", email=" + email + ", photo=" + Arrays.toString(photo) + ", username="
				+ username + ", allergies=" + allergies + ", visits=" + visits + ", doctors=" + doctors + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + Objects.hash(allergies, bloodtype, dateofbirth, doctors, email, patientID,
				patientName, username, visits);
		return result;
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
				&& Objects.equals(patientName, other.patientName) && Arrays.equals(photo, other.photo)
				&& Objects.equals(username, other.username) && Objects.equals(visits, other.visits);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


}