package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Visit;

public interface TestManager {

	public void addTest (Test entry);
	public Test showTest (Visit toSearch);
	public Test showTest (int test_id);	
	public List<Test> getTestsbyPatient(int patient_id);
	
}
