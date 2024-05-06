package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface TreatmentManager {
	
public Treatment showTreatment(Visit toSearch);
public List<Treatment> showTreatment(int visit_id);
public void addTreatment(Treatment treatment);
}
