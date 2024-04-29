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
	        // statement.setLocalDate(3, a.getDateofbirth());
	        statement.setString(4, a.getBloodtype());
	        // statement.setString(4, a.getAllergies());
	        statement.setString(4, a.getEmail());

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
	        PreparedStatement statement = conn.prepareStatement(sql);
	        statement.setString(1, name);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int patientId = resultSet.getInt("patient_id");
	            String patientName = resultSet.getString("name");
	            String surname = resultSet.getString("surname");
	            String sex = resultSet.getString("sex");
	            //LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate(); 
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
        // Implement changePatient method here
        // You need to update patient details in the database
    } */

  /*  @Override
    public Patient getDoctors(int patientid) {
        Patient patient = null;
        // Implement getDoctors method here
        // You need to retrieve patient by id from the database
        return patient;
    } */
}
