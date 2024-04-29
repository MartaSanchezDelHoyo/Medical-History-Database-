package medicalhistory.database.pojos;
import java.util.Objects;

public class Medication {

	private int medication_id;
	private String type;

	
	@Override
	public String toString() {
		return "Medication [medication_id=" + medication_id + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(medication_id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medication other = (Medication) obj;
		return medication_id == other.medication_id && Objects.equals(type, other.type);
	}

	public Medication(int medication_id, String type) {
		this.setMedication_id(medication_id);
		this.type=type;
	}

	public int getMedication_id() {
		return medication_id;
	}

	public void setMedication_id(int medication_id) {
		this.medication_id = medication_id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type=type;
	}
}