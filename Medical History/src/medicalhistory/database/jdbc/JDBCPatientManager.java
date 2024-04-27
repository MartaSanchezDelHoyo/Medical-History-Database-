package medicalhistory.database.jdbc;

import java.sql.Connection;

import medicalhistory.database.interfaces.PatientManager;

public class JDBCPatientManager implements PatientManager {
	private Connection c;
	private ConnectionManager conMan;

	public JDBCPatientManager(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
	}
}
