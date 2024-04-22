package medicalhistory.database.pojos;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;

public class Hospital implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1457717917835220464L;
	private Integer hospitalID;
    private String hospitalName;
    private String hospitalAddress;
    private List <Doctor> Hospital_doctors;
    private List <Visit> Hospital_visits;
    private List <String> Hospital_specialties;
    

    public Hospital() {
    }

        
    public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress, List<Doctor> hospital_doctors,
			List<Visit> hospital_visits, List<String> hospital_specialties) {
		super();
		this.hospitalID = hospitalID;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		Hospital_doctors = hospital_doctors;
		Hospital_visits = hospital_visits;
		Hospital_specialties = hospital_specialties;
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

	public List <String> getHospital_specialties() {
		return Hospital_specialties;
	}

	public void setHospital_specialties(List <String> hospital_specialties) {
		Hospital_specialties = hospital_specialties;
	}
}