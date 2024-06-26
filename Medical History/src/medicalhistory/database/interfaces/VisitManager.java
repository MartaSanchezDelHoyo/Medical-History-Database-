package medicalhistory.database.interfaces;

import java.sql.SQLException;
import java.util.List;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public interface VisitManager {
	public void addVisit (Visit temporal) throws SQLException;
	public void linkMedicationToVisit(Visit visit, Medication medi);
	public void linkTreatmentToVisit(Visit visit, Treatment treat);
    public void changeVisit (Visit temporal);
    public List<Visit> getVisitByHospital (int hospital_id);
    public List<Visit> getVisitByTest (int test_id);
    public List<Visit> getVisitByTreatment(int treatment_id);
    public Visit getVisit (int visitID);
    public List<Visit> getVisitByDoctor (int doctor_id);
    public List<Visit> getVisitByPatient (int patient_id);
    public void deleteVisit(Visit temporal);
  
   
}
