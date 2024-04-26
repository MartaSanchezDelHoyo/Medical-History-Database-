package medicalhistory.database.interfaces;

import java.util.ArrayList;
import medicalhistory.database.pojos.*;

public interface HospitalManager {

	public Visit showVisit(ArrayList<Visit> list_visits, Integer visit_id);
	public Treatment showTreatment(ArrayList<Treatment> list_treatment, Integer treatmentID);
	public Medication showMedication(ArrayList<Medication> list_medication, int medication_id);
	public void addTest (ArrayList<Test> list_test);
	public void addMedication (ArrayList<Medication> list_medication);
	public void addManufacturer (ArrayList<Manufacturer> list_manufacturer);
	public void modifyMedication (ArrayList<Medication> list_medication, Integer manufacturerID); 

}
