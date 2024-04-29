package medicalhistory.database.interfaces;
import java.util.List;
import medicalhistory.database.pojos.*;
public interface PatientManager {
	public void addPatient(Patient a);
	public List<Test> getTestsbyPatient(String name);
	public List<Patient> getPatientByName(String name);
	public void changePatient(Patient a);
	public List<Doctor> getDoctors(int patientId);

}
