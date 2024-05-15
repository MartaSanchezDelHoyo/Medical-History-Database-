package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Treatment implements Serializable{
    /**
     * @param treatmentID: identification of a treatment
     * @param treatmentType: type of treatment
     * @param visits: list of visits that took to do that treatment
     */
	private static final long serialVersionUID = -5230755319444165612L;
	private Integer treatmentID;
    private String treatmentType;
    private List <Visit> visits;
    
    
    /** Constructor without the visits as a parameter
     * @param treatmentID identification of a treatment
     * @param treatmentType type of treatment
     */
      public Treatment( String treatmentType) {
          this.treatmentType = treatmentType;
          this.visits= new ArrayList<>();
      }
    
   /** Constructor without the visits as a parameter
   * @param treatmentID identification of a treatment
   * @param treatmentType type of treatment
   */
    public Treatment(Integer treatmentID, String treatmentType) {
        this.treatmentID = treatmentID;
        this.treatmentType = treatmentType;
        this.visits= new ArrayList<>();
    }


    /** Constructor with every attribute
     * @param treatmentID
     * @param treatmentType
     * @param visits
     */
    public Treatment(Integer treatmentID, String treatmentType, List <Visit> visits) {
        this.treatmentID = treatmentID;
        this.treatmentType = treatmentType;
        this.visits= visits;
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
		Treatment other = (Treatment) obj;
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
    /**
     * Getter and setters of the attributes
     */
    
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

	public List <Visit> getVisits() {
		return visits;
	}

	public void setVisits(List <Visit> visits) {
		this.visits = visits;
	}
}
