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
    public List<Visit> getVisitByHospital (int hospital_id);
    public List<Visit> getVisitByTest (int test_id);
    public List<Visit> getVisitByTreatment(int treatment_id);
    public Visit getVisit (int visitID);
    public List<Visit> getVisitByDoctor (int doctor_id);
    public List<Visit> getVisitByPatient (int patient_id);

  
   
}
