package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface TreatmentManager {
	
public String getTreatmentType(int treatmentID );
public List<Treatment> getTreatments(int visit_id);
public void addTreatment(Treatment treatment);
}
