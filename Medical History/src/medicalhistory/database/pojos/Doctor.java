
package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Doctor implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4748700239185078330L;
	private int doctor_id;
	private String name;
	private String surname;
	private String specialty;
	private String contact;
	private byte[] photo;
	private List <Patient> patients;
	private List <Hospital> hospitals;
	private List <Visit> visits;
	
	

	/**
	 * Constructor of the object doctor that has as parameters:
	 * @param doctor_id
	 * @param name
	 * @param surname
	 * @param specialty
	 * @param contact
	 * @param patients
	 * @param hospitals
	 * @param visits
	 * @param photo
	 */
	public Doctor(int doctor_id, String name, String surname, String speciality, String contact, List<Patient> patients,
			List<Hospital> hospitals, List<Visit> visits,byte[] photo) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.surname = surname;
		this.specialty = speciality;
		this.contact = contact;
		this.patients = patients;
		this.hospitals = hospitals;
		this.visits = visits;
		this.photo=photo;
	}
	/**
	 * Constructor of the object doctor that has as parameters:
	 * @param doctor_id
	 * @param name
	 * @param surname
	 * @param specialty
	 * @param contact
	 * @param photo
	 */
	public Doctor(int doctor_id, String name, String surname,String speciality, String contact,byte[]photo) {
		this.setDoctor_id(doctor_id);
		this.setName(name);
		this.setSurname(surname);
		this.setSpecialty(speciality);
		this.setContact(contact);
		this.setPhoto(photo);
		this.patients=new ArrayList<>();
		this.hospitals=new ArrayList<>();
		this.visits=new ArrayList<>();
	}
	
	/**
	 * Constructor of the object doctor that has as parameters:
	 * @param name
	 * @param surname
	 * @param specialty
	 * @param contact
	 * @param photo
	 */
	public Doctor(String name, String surname, String specialty, String contact,byte[]photo) {
		this.setName(name);
		this.setSurname(surname);
		this.setSpecialty(specialty);
		this.setContact(contact);
		this.setPhoto(photo);
		this.patients=new ArrayList<>();
		this.hospitals=new ArrayList<>();
		this.visits=new ArrayList<>();
	}
	

	public Doctor( String name, String surname, String specialty, String contact, byte[] photo,
			List<Patient> patients, List<Hospital> hospitals ) {
		super();
		this.name = name;
		this.surname = surname;
		this.specialty = specialty;
		this.contact = contact;
		this.photo = photo;
		this.patients = patients;
		this.hospitals = hospitals;
	}
	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", surname=" + surname + ", speciality="
				+ specialty + ", contact=" + contact + ", photo=" + Arrays.toString(photo) + ", patients=" + patients
				+ ", hospitals=" + hospitals + ", visits=" + visits + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, doctor_id, hospitals, name, patients, specialty, surname, visits);
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
				&& Objects.equals(hospitals, other.hospitals) && Objects.equals(name, other.name)
				&& Objects.equals(patients, other.patients) && Objects.equals(specialty, other.specialty)
				&& Objects.equals(surname, other.surname) && Objects.equals(visits, other.visits);
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}



	public List <Patient> getPatients() {
		return patients;
	}



	public void setPatients(List <Patient> patients) {
		this.patients = patients;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public List <Hospital> getHospitals() {
		return hospitals;
	}



	public void setHospitals(List <Hospital> hospitals) {
		this.hospitals = hospitals;
	}



	public List <Visit> getVisits() {
		return visits;
	}

	public void setVisits(List <Visit> visits) {
		this.visits = visits;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
}