package POJOS;
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

    public Hospital() {
    }

    public Hospital(Integer hospitalID, String hospitalName, String hospitalAddress) {
        this.hospitalID = hospitalID;
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
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
}