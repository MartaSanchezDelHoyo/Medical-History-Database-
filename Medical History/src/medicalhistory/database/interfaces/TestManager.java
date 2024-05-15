package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.*;

public interface TestManager {

	public void addTest (Test entry);
	public Test getTest (Visit toSearch);
	public Test getTest (int test_id);	
	public List<Test> getTestsbyPatient(int patient_id);
	
}
