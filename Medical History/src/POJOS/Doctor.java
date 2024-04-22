package POJOS;

public class Doctor {

	private int doctor_id;
	private String name;
	private String speciality;
	private String contact;

	
	public static void main(String[] args) {
      Doctor Doctor1= new Doctor(1,"Alfonso", "Caca","caca01@gmail.com");
      System.out.println(Doctor1.contact);
    } 
	
	public Doctor(int doctor_id, String name, String speciality, String contact) {
		this.setDoctor_id(doctor_id);
		this.setName(name);
		this.setSpeciality(speciality);
		this.contact= contact;
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
	
	
}
