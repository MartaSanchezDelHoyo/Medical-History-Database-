
package medicalhistory.database.interfaces;
import java.util.ArrayList;
import medicalhistory.database.pojos.*;

public interface HospitalManager {


	public Treatment showTreatment(Visit toSearch);
	public Medication showMedication(Visit toSearch);
	public void addTest (Test entry);
	public void addMedication (Medication entry);
	public void addManufacturer (Manufacturer entry);
	public void modifyMedication (Medication entry, Integer medicationID); 
	
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
 