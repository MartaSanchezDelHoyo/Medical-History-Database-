package POJOS;

public class Medication {

	private int medication_id;
	private String type;
	
	public static void main(String[] args) {
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
