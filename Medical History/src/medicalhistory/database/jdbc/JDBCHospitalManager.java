package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.util.ArrayList;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
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

	
}

