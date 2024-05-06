package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface TreatmentManager {
	
public String showTreatmentType(int treatmentID );
public List<Treatment> showTreatments(int visit_id);
public void addTreatment(Treatment treatment);
}
