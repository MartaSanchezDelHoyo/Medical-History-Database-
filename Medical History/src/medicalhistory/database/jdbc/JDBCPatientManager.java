package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.*;
import medicalhistory.database.interfaces.PatientManager;
import java.util.ArrayList;
import java.util.List;
import medicalhistory.database.pojos.*;

public class JDBCPatientManager implements PatientManager {

    private Connection c;
    
	public JDBCPatientManager(ConnectionManager connectionManager) {
		this.c = connectionManager.getConnection();
	}
 
	@Override
	public void addPatient(Patient a) {
	    try {
	        // Prepare SQL statement
	        String sql = "INSERT INTO patients (name, surname, age, gender, allergies, email) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, a.getPatientName());
	        statement.setString(2, a.getSex());
	        statement.setDate(3, a.getDateofbirth());
	        statement.setString(4, a.getBloodtype());
	        statement.setString(4, a.getEmail());
	        statement.setString(4, a.getAllergies().toString());
	        statement.setString(4, a.getDoctors().toString());


	        statement.executeUpdate();
	        statement.close();
	        
	        System.out.println("Patient added successfully");
	    } catch (SQLException e) {
	        System.err.println("Error adding patient: " + e.getMessage());
	    }
	}

	@Override
	public List<Test> getTestsbyPatient(String name) {
	    List<Test> tests = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM tests WHERE patient_name = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, name);
	       
	        ResultSet resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            int testId = resultSet.getInt("test_id");
	            String testName = resultSet.getString("test_name");
	            Test test = new Test(testId, testName);
	            tests.add(test);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving tests by patient: " + e.getMessage());
	    }
	    return tests;
	}

	@Override
	public List<Patient> getPatientByName(String name) {
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM patients WHERE name = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, name);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int patientId = resultSet.getInt("patient_id");
	            String patientName = resultSet.getString("name");
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("email"); 
	            
	            Patient patient = new Patient(patientId, patientName, sex, dateOfBirth, email);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patients;
	}

   /* @Override
    public void changePatient(Patient a) {
    	// Todo
    	
    } */

 /* @Override
    public Patient getDoctors(int patientid) {
        // TODO 
	  return 0;
    } */
}