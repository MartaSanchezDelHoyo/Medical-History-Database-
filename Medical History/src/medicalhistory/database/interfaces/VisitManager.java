package medicalhistory.database.interfaces;

import java.util.List;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface VisitManager {
	public void addVisit (Visit temporal);
    public void changeVisit (int visitIdToChange, Visit temporal);
    public Visit showVisitBy (Hospital temporal);
    public Visit showVisitBy ( Doctor toSearch);
    public Visit showVisitBy ( Patient toSearch);
    public Visit showVisitBy ( Treatment toSearch);
    public Visit showVisitBy ( Medication toSearch);
    public Visit showVisitBy ( Test toSearch);
    public List<Visit> showVisitBy (int doctor_id);
    public List<Visit> showVisitByPatient (int patient_id);
  
   
}
