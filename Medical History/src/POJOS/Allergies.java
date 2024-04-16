package POJOS;
import java.util.Objects;

public class Allergies {
	private Integer allergiesID;
	private String allergiesName;
	
	public Allergies() {
		
	}
	
	public Allergies(Integer allergiesID, String allergiesName) {
		this.allergiesID= allergiesID;
		this.allergiesName= allergiesName;
	}
	
    public Integer getAllergiesID() {
        return allergiesID;
    }

    public void setAllergiesID(Integer allergiesID) {
        this.allergiesID = allergiesID;
    }

    public String getAllergiesName() {
        return allergiesName;
    }

    public void setAllergiesName(String allergiesName) {
        this.allergiesName = allergiesName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(allergiesID, allergiesName);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Allergies other = (Allergies) obj;
		if (allergiesID == null) {
			if (other.allergiesID != null)
				return false;
		} else if (!allergiesID.equals(other.allergiesID))
			return false;
		return true;
	}
	
    @Override
    public String toString() {
        return "AllergiesPOJO{" +
                "allergiesID=" + allergiesID +
                ", allergiesName='" + allergiesName + '\'' +
                '}';
    }
}
