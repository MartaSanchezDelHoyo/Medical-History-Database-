
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
	private String username;
	private String specialty;
	private String contact;
	private byte[] photo;
	private List <Patient> patients;
	private List <Hospital> hospitals;
	private List <Visit> visits;
	
	/**
	 * Constructor of the object doctor that has as parameters:
	 * @param name
	 * @param surname
	 * @param specialty
	 * @param contact
	 * @param photo
	 */
	public Doctor(String name, String surname,String username, String specialty, String contact, byte[]photo) {
		this.name = name;
		this.surname = surname;
		this.username= username;
		this.specialty = specialty;
		this.contact = contact;
		this.photo=photo;
		this.patients=new ArrayList<>();
		this.hospitals=new ArrayList<>();
		this.visits=new ArrayList<>();
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
	public Doctor(int doctor_id, String name, String surname,String username, String speciality, String contact, byte[]photo) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.surname = surname;
		this.username= username;
		this.specialty = speciality;
		this.contact = contact;
		this.photo=photo;
		this.patients=new ArrayList<>();
		this.hospitals=new ArrayList<>();
		this.visits=new ArrayList<>();
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
	public Doctor(int doctor_id, String name, String surname,String username, String speciality, String contact, byte[]photo, List<Hospital> hospitals) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.surname = surname;
		this.username= username;
		this.specialty = speciality;
		this.contact = contact;
		this.photo=photo;
		this.patients=new ArrayList<>();
		this.hospitals=hospitals;
		this.visits=new ArrayList<>();
	}
	
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
	public Doctor(int doctor_id, String name, String surname,String username, String speciality, String contact,byte[] photo, List<Patient> patients,
			List<Hospital> hospitals, List<Visit> visits) {
		this.doctor_id = doctor_id;
		this.name = name;
		this.surname = surname;
		this.username= username;
		this.specialty = speciality;
		this.contact = contact;
		this.photo=photo;
		this.patients = patients;
		this.hospitals = hospitals;
		this.visits = visits;
		
	}
	
	
	
	

	public Doctor( String name, String surname, String specialty, String contact, byte[] photo,
			List<Patient> patients, List<Hospital> hospitals ,String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.specialty = specialty;
		this.contact = contact;
		this.photo = photo;
		this.patients = patients;
		this.hospitals = hospitals;
		this.visits=new ArrayList<Visit>();
		this.username=username;
	}
	@Override
	public String toString() {
		return "Doctor [doctor_id=" + doctor_id + ", name=" + name + ", surname=" + surname + ", speciality="
				+ specialty + ", contact=" + contact + ", photo=" + Arrays.toString(photo) + ", patients=" + patients
				+ ", hospitals=" + hospitals + ", visits=" + visits + "]";
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result
				+ Objects.hash(contact, doctor_id, hospitals, name, patients, specialty, surname, username, visits);
		return result;
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
				&& Objects.equals(patients, other.patients) && Arrays.equals(photo, other.photo)
				&& Objects.equals(specialty, other.specialty) && Objects.equals(surname, other.surname)
				&& Objects.equals(username, other.username) && Objects.equals(visits, other.visits);
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}