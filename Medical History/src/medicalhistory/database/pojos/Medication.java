package medicalhistory.database.pojos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Medication {
	/**
     * @param medication_id: identification of a medication
     * @param type: name of the medication
     * @param manufacturers: list of manufacturers that manufacture that medication
     *@param visits: list of visits that has that medication 
     */
	private int medication_id;
	private String type;
    private List <Manufacturer> manufacturers;
	private List <Visit> visits;
    
	/**Constructor with only the name as a parameter
     * @param type is the name of the medication
     */
	public Medication(String type) {
		this.type=type;
		this.manufacturers=new ArrayList<>();
		this.visits= new ArrayList<>();
	}

	/** Constructor without the visits and the manufacturers as a parameter
     * @param medication_id is the identification of a medication
     * @param type
   
     */
	public Medication(int medication_id, String type) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers=new ArrayList<>();
		this.visits= new ArrayList<>();
	}

	/** Constructor without the visits as a parameter
     * @param medication_id
     * @param type
     * @param manufacturers is the list of manufacturers that manufacture that medication
     */
	public Medication(int medication_id, String type, List <Manufacturer> manufacturers) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers= manufacturers;
		this.visits= new ArrayList<>();
		
	}
	
	/** Constructor with every attribute
     * @param medication_id
     * @param type
     * @param manufacturers
     *@param visits is the list of visits that has that medication 
     */
	public Medication(int medication_id, String type, List <Manufacturer> manufacturers, List <Visit> visits) {
		this.medication_id= medication_id;
		this.type=type;
		this.manufacturers= manufacturers;
		this.visits=visits;
	}
	
	/** Equals method to compare two medication_id
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
		Medication other = (Medication) obj;
		return medication_id == other.medication_id && Objects.equals(type, other.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(medication_id, type);
	}
	
	/**
     * Getter and setters of the attributes
     */
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
	
	@Override
	public String toString() {
		return "Medication [medication_id=" + medication_id + ", type=" + type + "]";
	}
}