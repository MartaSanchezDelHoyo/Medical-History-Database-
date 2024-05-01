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
    public List<Visit> showVisitByHospital (int hospital_id);
    public List<Visit> showVisitByTest (int test_id);
    public List<Visit> showVisitByTreatment(int treatment_id);
    public List<Visit> showVisitBy (Doctor toSearch);
    public List<Visit> showVisitByDoctor (int doctor_id);
    public List<Visit> showVisitByPatient (int patient_id);
  
   
}
