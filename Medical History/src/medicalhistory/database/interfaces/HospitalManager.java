package medicalhistory.database.interfaces;

import java.util.ArrayList;

import medicalhistory.database.pojos.*;

public interface HospitalManager {

	public Visit showVisit (ArrayList<Visit> list_visits, Integer visit_id);
	public Treatment showTreatment(ArrayList<Treatment> list_treatment, Integer treatmentID);
	public Medication showMedication(ArrayList<Medication> list_medication, int medication_id);
	public void addTest (ArrayList<Test> list_test);
	public void addMedication (ArrayList<Medication> list_medication);
	public void addManufacturer (ArrayList<Manufacturer> list_manufacturer);
	public void modifyMedication (ArrayList<Medication> list_medication, Integer manufacturerID); 
	
	public void AddHospital (Hospital temporal);
	public void ChangeHospital (Hospital temporal);
	public Hospital showHospitalBy (String specialization);
	public Hospital showHospitalBy (Doctor toSearch);
	public Hospital showHospitalBy (Patient toSearch);
	public Hospital showHospitalBy (Visit toSearch);
	public Hospital showHospitalBy (Treatment toSearch);
	public Hospital showHospitalBy (Test toSearch);
	public void addVisit (Hospital temporally, Visit temporal);
    public void changeVisit (Hospital temporal, int visit_id);
    public Visit showVisitBy (Hospital temporal);
    public Visit showVisitBy (Hospital temporal, Doctor toSearch);
    public Visit showVisitBy (Hospital temporal, Patient toSearch);
    public Visit showVisitBy (Hospital temporal, Treatment toSearch);
    public Visit showVisitBy (Hospital temporal, Medication toSearch);
    public Visit showVisitBy (Hospital temporal, Test toSearch);
    public Test showTest (Hospital temporal, Visit temp);

}
