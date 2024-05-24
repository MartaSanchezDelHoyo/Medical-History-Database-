package medicalhistory.database.pojos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medication {

	private int medication_id;
	private String type;
    private List <Manufacturer> manufacturers;
	private List <Visit> visits;
    
	public Medication() {
		super();
	}
	
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

	public Medication(String type) {
		this.type=type;
		this.manufacturers=new ArrayList<>();
		this.visits= new ArrayList<>();
	}

	
	public Medication(int medication_id, String type) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers=new ArrayList<>();
		this.visits= new ArrayList<>();
	}

	
	public Medication(int medication_id, String type, List <Manufacturer> manufacturers) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers= manufacturers;
		this.visits= new ArrayList<>();
		
	}
	
	public Medication(int medication_id, String type, List <Manufacturer> manufacturers, List <Visit> visits) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers= manufacturers;
		this.visits=visits;
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

	public List <Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List <Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public List <Visit> getVisits() {
		return visits;
	}

	public void setVisits(List <Visit> visits) {
		this.visits = visits;
	}
}