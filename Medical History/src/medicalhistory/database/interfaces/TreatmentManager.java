package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Treatment;

public interface TreatmentManager {
	
	public void addTreatment(Treatment treatment);
	public void changeTreatment (Treatment treatment) ;
    public String getTreatmentType(int treatmentID );
    public List<Treatment> getTreatments(int visit_id);
    public Treatment getTreatment (int treatmentid);

}
