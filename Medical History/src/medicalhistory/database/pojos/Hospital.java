package medicalhistory.database.pojos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class Hospital implements Serializable{

	/**
	 * @param hospitalID: identification of the hospital
	 * @param hospitalName: name of the hospital
	 * @param hospitalAddress: address of the hospital
	 * @param username: the username of the hospital in the program
	 * @param hospital_doctors: list of doctors of the hospital
	 * @param hospital_visits: list of visits of the hospitals
	 */
	private static final long serialVersionUID = -1457717917835220464L;
	@XmlAttribute
	private Integer hospitalID;
	@XmlAttribute
    private String hospitalName;
	@XmlAttribute
    private String hospitalAddress;
	@XmlTransient
	private String username;
	@XmlTransient
    private List <Doctor> Hospital_doctors;
	@XmlTransient
    private List <Visit> Hospital_visits;
       
	public Hospital() {
		super();
	}
	
    /**
     * @param hospitalName name of the hospital
     * @param hospitalAddress address of the hospital
     * @param username: the username of the hospital in the program
     */
    public Hospital(String hospitalName, String hospitalAddress, String username) {
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.username=username;
		this.Hospital_doctors = new ArrayList<>();
		this.Hospital_visits = new ArrayList<>();
	}
    
    /**
     * @param hospitalName 
     * @param hospitalAddress 
     * @param username
     * @param hospital_doctors list of doctors of the hospital
     */
    public Hospital(String hospitalName, String hospitalAddress, String username,List<Doctor> hospital_doctors) {
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.username=username;
		this.Hospital_visits = new ArrayList<>();
		this.Hospital_doctors=hospital_doctors;
	}
    
    /** 
     * @param hospitalID identification of the hospital
     * @param hospitalName 
     * @param hospitalAddress 
     * @param username 
     */
    public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, String username) {
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
	    this.username=username;
		this.hospitalAddress = hospitalAddress;
		this.Hospital_doctors = new ArrayList<>();
		this.Hospital_visits = new ArrayList<>();
	}

	/**Constructor without the visits as a parameter
	 * @param hospitalID
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param username 
	 * @param hospital_doctors list of doctors of the hospital
	 */
	public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, String username, List<Doctor> hospital_doctors) {
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
		this.username=username;
		this.hospitalAddress = hospitalAddress;
		this.Hospital_doctors= hospital_doctors;
		this.Hospital_visits = new ArrayList<>();
	}
	
	/**Constructor with every parameter
	 * @param hospitalID
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param username 
	 * @param hospital_doctors
	 * @param hospital_visits list of visits of the hospitals
	 */
	public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, String username, List<Doctor> hospital_doctors, List<Visit> hospital_visits) {
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
		this.username=username;
		this.hospitalAddress = hospitalAddress;
	    this.Hospital_doctors =hospital_doctors;
	    this.Hospital_visits= hospital_visits;
	}

	/** Equals method to compare two hospitals
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
		Hospital other = (Hospital) obj;
		return Objects.equals(Hospital_doctors, other.Hospital_doctors)
				&& Objects.equals(Hospital_visits, other.Hospital_visits)
				&& Objects.equals(hospitalAddress, other.hospitalAddress)
				&& Objects.equals(hospitalID, other.hospitalID) && Objects.equals(hospitalName, other.hospitalName)
				&& Objects.equals(username, other.username);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Hospital_doctors, Hospital_visits, hospitalAddress, hospitalID, hospitalName, username);
	}
	
	/**
     * Getter and setters of the attributes
     */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(Integer hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

	public List <Doctor> getHospital_doctors() {
		return Hospital_doctors;
	}

	public void setHospital_doctors(List <Doctor> hospital_doctors) {
		Hospital_doctors = hospital_doctors;
	}

	public List <Visit> getHospital_visits() {
		return Hospital_visits;
	}

	public void setHospital_visits(List <Visit> hospital_visits) {
		Hospital_visits = hospital_visits;
	}
	
	@Override
	public String toString() {
		return "Hospital [hospitalID=" + hospitalID + ", hospitalName=" + hospitalName + ", hospitalAddress="
				+ hospitalAddress + ", username=" + username + ", Hospital_doctors=" + Hospital_doctors
				+ ", Hospital_visits=" + Hospital_visits + "]";
	}
}