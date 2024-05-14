package medicalhistory.database.pojos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class Hospital implements Serializable{

	
	private static final long serialVersionUID = -1457717917835220464L;
	private Integer hospitalID;
    private String hospitalName;
    private String hospitalAddress;
    private List <Doctor> Hospital_doctors;
    private List <Visit> Hospital_visits;
        
    /** 
     * @param hospitalID identification of the hospital
     * @param hospitalName name of the hospital
     * @param hospitalAddress address of the hospital
     */
    public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress) {
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.Hospital_doctors = new ArrayList<>();
		this.Hospital_visits = new ArrayList<>();
	}

	/**Constructor without the visits as a parameter
	 * @param hospitalID
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param hospital_doctors list of doctors of the hospital
	 */
	public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, List<Doctor> hospital_doctors) {
		super();
		this.Hospital_doctors= hospital_doctors;
	}
	
	/**Constructor with every parameter
	 * @param hospitalID
	 * @param hospitalName
	 * @param hospitalAddress
	 * @param hospital_doctors
	 * @param hospital_visits list of visits of the hospitals
	 */
	public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, List<Doctor> hospital_doctors, List<Visit> hospital_visits) {
		super();
	this.Hospital_doctors =hospital_doctors;
	this.Hospital_visits= hospital_visits;
	}
	


	@Override
    public int hashCode() {
        return Objects.hash(hospitalID, hospitalName, hospitalAddress);
    }

    @Override
    public String toString() {
        return "HospitalPOJO{" +
                "hospitalID=" + hospitalID +
                ", hospitalName='" + hospitalName + '\'' +
                ", hospitalAddress='" + hospitalAddress + '\'' +
                '}';
    }
    /** Equals method to compare two treatmentID
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
		if (hospitalID == null) {
			if (other.hospitalID != null)
				return false;
		} else if (!hospitalID.equals(other.hospitalID))
			return false;
		return true;
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
}