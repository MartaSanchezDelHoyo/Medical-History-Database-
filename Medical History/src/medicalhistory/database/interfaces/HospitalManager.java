
package medicalhistory.database.interfaces;
import java.util.List;

import medicalhistory.database.pojos.*;

public interface HospitalManager {

	public void addHospital (Hospital temporal);
	//To change the hospital, the attribute that the user wants to change would be
	//declared in the method where the user introduce the new data, returning the hospital changed to introduce it in the following method
	public void changeHospital (int hospitalToChange,Hospital temporal);
	public Hospital getHospital (int hospital_id);
	public List<Hospital> getHospitalByDoctor(int doctor_id);
	public Hospital getHospitalByVisit(int visit_id);
	public List<Hospital> getHospitalByPatient(int patientID);
	public List<String> getSpecialtybyHospital(int hospitalID);
	
	
	
}
 