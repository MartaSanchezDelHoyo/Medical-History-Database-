package medicalhistory.database.interfaces;
import java.util.List;
import medicalhistory.database.pojos.*;
public interface PatientManager {
	public void addPatient(Patient a);
	public List<Test> getTestsbyPatient(String name);
	public List<Patient> getPatientByName(String name);
	public Patient getPatient(int patient_id);
	public void changePatient(Patient a);
	public List<Patient> getPatients(int doctorId);

}
