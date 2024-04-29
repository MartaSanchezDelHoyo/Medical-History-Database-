package medicalhistory.database.interfaces;

import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Visit;

public interface TestManager {

	public Test showTest (Visit toSearch);
	public Test showTest (int test_id);	
	
}
