package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.*;


public interface DoctorManager {
	public void addDoctor(Doctor a);
	public void linkDoctorToHospital(Hospital hos, Doctor doc);
	public void changeDoctor(Doctor a);
	public List<Doctor> getDoctorsbySpecialties(String specialty);
	public List<Doctor> getDoctorsbyHospital(String hospitalName);
	public List<Doctor> getDoctorByNameSurname(String name, String surname);
	
	public Doctor getDoctor(int id) ;
	
}