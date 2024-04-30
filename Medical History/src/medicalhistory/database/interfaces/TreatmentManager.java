package medicalhistory.database.interfaces;

import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface TreatmentManager {
	
public Treatment showTreatment(Visit toSearch);

}
