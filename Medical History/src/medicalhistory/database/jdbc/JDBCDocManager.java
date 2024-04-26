package medicalhistory.database.jdbc;

import java.sql.*;
import java.util.List;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.interfaces.DoctorManager;

public class JDBCDocManager implements DoctorManager {
	private Connection c;
	private ConnectionManager conMan;

	public JDBCDocManager(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDoctor(Doctor a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Doctor> getDoctorsbySpecialties(String specialty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctorsbyHospital(String hospitalName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Doctor> getDoctorByNameSurname(String name, String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeDoctor(Doctor a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Doctor getDoctor(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
