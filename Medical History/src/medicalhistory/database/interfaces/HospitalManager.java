
package medicalhistory.database.interfaces;
import java.sql.SQLException;
import java.util.List;

import medicalhistory.database.pojos.*;

public interface HospitalManager {

	public void addHospital (Hospital temporal) throws SQLException;
	public void changeHospital (Hospital temporal) throws SQLException;
	public Hospital getHospital (int hospital_id);
	public Hospital getHospitalCI (int hospitalID);
	public Hospital getHospitalByVisit(int visit_id);
	public List<Hospital> getHospitalByDoctor(int doctor_id);
	public List<Hospital> getHospitalByPatient(int patientID);
	public Hospital getHospitalbyUsername(String username);
	
	
	
}
 