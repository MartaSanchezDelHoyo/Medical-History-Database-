package medicalhistory.database.interfaces;

import java.sql.SQLException;
import java.util.List;

import medicalhistory.database.pojos.*;


public interface DoctorManager {
	public void addDoctor(Doctor a) throws SQLException;
	public void changeDoctor(Doctor a);
	public Doctor getDoctor(int id);
	public Doctor getDoctorCI(int id);
	public Doctor getDoctorsbyUsername(String usern);
	public List<Doctor> getDoctorsbySpecialties(String specialty);
	public List<Doctor> getDoctorsByPatient(int patientId);
	public List<Doctor> getDoctorsbyHospital(int hospitalID);
	public List<Doctor> getDoctorByNameSurname(String name, String surname);
	public List<Doctor> getAllDoctors();
	
	
}