package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Treatment;

public interface TreatmentManager {
	
public String getTreatmentType(int treatmentID );
public List<Treatment> getTreatments(int visit_id);
public void addTreatment(Treatment treatment);
public Treatment getTreatment (int treatmentid);
}
