package medicalhistory.database.interfaces;
import java.util.List;
import medicalhistory.database.pojos.*;
public interface PatientManager {
	
	public void addPatient(Patient a);
	public void linkAllergiesToPatient (Patient a, Allergies s);
	public void changePatient(Patient a);
	public Patient getPatientssbyUsername(String usern);
	public List<Patient> getPatientByName(String name);
	public Patient getPatientCI(int patient_ID );
	public List<Patient> getPatientsByHospital(int hospital_id);
	public List<Patient> getPatientsByDoctor(int doctor_id);
	public Patient getPatient(int patient_id);
	public Patient getPatient(String name);
	public List<Patient> getAllPatients();

}
