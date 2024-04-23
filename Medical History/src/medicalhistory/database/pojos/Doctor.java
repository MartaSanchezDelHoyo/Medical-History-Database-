
package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4748700239185078330L;
	private int doctor_id;
	private String name;
	private String speciality;
	private String contact;
	private ArrayList <Patient> patients;
	
	
	public Doctor(int doctor_id, String name, String speciality, String contact) {
		this.setDoctor_id(doctor_id);
		this.setName(name);
		this.setSpeciality(speciality);
		this.contact= contact;
	}
	
	
	
	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", speciality=" + speciality + ", contact="
				+ contact + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(contact, doctor_id, name, speciality);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		return Objects.equals(contact, other.contact) && doctor_id == other.doctor_id
				&& Objects.equals(name, other.name) && Objects.equals(speciality, other.speciality);
	}



	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}



	public ArrayList <Patient> getPatients() {
		return patients;
	}



	public void setPatients(ArrayList <Patient> patients) {
		this.patients = patients;
	}
	
	
}