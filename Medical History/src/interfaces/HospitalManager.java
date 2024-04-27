package interfaces;

import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Visit;

public class HospitalManager {
	
	public void AddHospital (Hospital temporal);
	public void ChangeHospital (Hospital temporal);
	public Hospital showHospitalBy (String specialization);
	public Hospital showHospitalBy (Doctor toSearch);
	public Hospital showHospitalBy (Patient toSearch);
	public Hospital showHospitalBy (Visit toSearch);
	public void addVisit (Hospital temporally, Visit temporal);
    public void changeVisit (Hospital temporal, int visit_id);
    public Visit showVisitBy (Hospital temporal, Doctor toSearch);
    public Visit showVisitBy (Hospital temporal, Patient toSearch);
    public Visit showVisitBy (Hospital temporal, Treatment toSearch);
    public Visit showVisitBy (Hospital temporal, Medicaton toSearch);
    public Test showTest (Hospital temporal, Visit temp);
}
