package medicalhistory.database.interfaces;

import java.util.ArrayList;

import interfaces.Doctor;
import interfaces.Medicaton;
import interfaces.Patient;
import interfaces.Test;
import interfaces.Treatment;
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
	//To change the hospital, the attribute that the user wants to change would be
	//declared in the method where the user introduce the new data, returning the hospital changed to introduce it in the following method
	public void ChangeHospital (int hospitalToChange,Hospital temporal);
	public Hospital showHospitalBy (String specialization);
	public Hospital showHospitalBy (Doctor toSearch);
	public Hospital showHospitalBy (Patient toSearch);
	public Hospital showHospitalBy (Visit toSearch);
	public Hospital showHospitalBy (Treatment toSearch);
	public Hospital showHospitalBy (Test toSearch);
	public Test showTest (Visit temp);

}
