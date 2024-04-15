package POJOS;

import java.util.Objects;

public class TreatmentPOJO {
    private Integer treatmentID;
    private String treatmentType;

    public TreatmentPOJO() {
    }

    public TreatmentPOJO(Integer treatmentID, String treatmentType) {
        this.treatmentID = treatmentID;
        this.treatmentType = treatmentType;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreatmentPOJO other = (TreatmentPOJO) obj;
		if (treatmentID == null) {
			if (other.treatmentID != null)
				return false;
		} else if (!treatmentID.equals(other.treatmentID))
			return false;
		return true;
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(treatmentID, treatmentType);
    }

    @Override
    public String toString() {
        return "TreatmentPOJO{" +
                "treatmentID=" + treatmentID +
                ", treatmentType='" + treatmentType + '\'' +
                '}';
    }
    
    public Integer getTreatmentID() {
        return treatmentID;
    }

    public void setTreatmentID(Integer treatmentID) {
        this.treatmentID = treatmentID;
    }

    public String getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }
}
