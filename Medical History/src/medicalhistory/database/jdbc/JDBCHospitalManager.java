package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.util.ArrayList;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public class JDBCHospitalManager implements HospitalManager {
	private Connection c;
	private ConnectionManager conMan;

	public JDBCHospitalManager(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Visit showVisit(ArrayList<Visit> list_visits, Integer visit_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Treatment showTreatment(ArrayList<Treatment> list_treatment, Integer treatmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medication showMedication(ArrayList<Medication> list_medication, int medication_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void addTest(ArrayList<Test> list_test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMedication(ArrayList<Medication> list_medication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addManufacturer(ArrayList<Manufacturer> list_manufacturer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMedication(ArrayList<Medication> list_medication, Integer manufacturerID) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void AddHospital (Hospital temporal) {
		// TODO Auto-generated method stub
	}
	@Override
	public void ChangeHospital (Hospital temporal) {
		// TODO Auto-generated method stub
	}
	@Override
	public Hospital showHospitalBy (String specialization) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hospital showHospitalBy (Doctor toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hospital showHospitalBy (Patient toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hospital showHospitalBy (Visit toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hospital showHospitalBy (Treatment toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hospital showHospitalBy (Test toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addVisit (Hospital temporally, Visit temporal) {
		// TODO Auto-generated method stub
	}
	@Override
    public void changeVisit (Hospital temporal, int visit_id) {
		// TODO Auto-generated method stub
	}
	@Override
    public Visit showVisitBy (Hospital temporal) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Visit showVisitBy (Hospital temporal, Doctor toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Visit showVisitBy (Hospital temporal, Patient toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Visit showVisitBy (Hospital temporal, Treatment toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Visit showVisitBy (Hospital temporal, Medication toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Visit showVisitBy (Hospital temporal, Test toSearch) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public Test showTest (Hospital temporal, Visit temp) {
		// TODO Auto-generated method stub
		return null;
	}
}

