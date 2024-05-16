package medicalhistory.database.interfaces;
import java.util.List;
import medicalhistory.database.pojos.*;
public interface PatientManager {
	
	public void addPatient(Patient a);
	public void addAllergiestoPatient (Patient a, Allergies s);
	public void changePatient(Patient a);
	public List<Patient> getPatientByName(String name);
	public Patient getPatient(int patient_id);
	public Patient getPatient(String name);
	


}
