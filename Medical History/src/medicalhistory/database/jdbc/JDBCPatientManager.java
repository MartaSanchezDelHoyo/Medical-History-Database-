package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.*;
import medicalhistory.database.interfaces.PatientManager;
import java.util.ArrayList;
import java.util.List;
import medicalhistory.database.pojos.*;
public class JDBCPatientManager implements PatientManager {
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCPatientManager(ConnectionManager connectionManager) {
		this.c = connectionManager.getConnection();
		this.setConMan(connectionManager);
	}
	
	@Override
	public void addPatient(Patient a) {
	    try {
	        String sql = "INSERT INTO patients (name, surname, age, gender, email) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = c.prepareStatement(sql);

	        statement.setString(1, a.getPatientName());
	        statement.setString(2, a.getSex());
	        statement.setDate(3, a.getDateofbirth());
	        statement.setString(4, a.getBloodtype());
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
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, name);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String patientName = resultSet.getString("name");
	            String surname = resultSet.getString("surname");
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("dateofbirth"); 
	            String email = resultSet.getString("email"); 
	            
	            Patient patient = new Patient (patientName, sex, dateOfBirth, email);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patients;
	}
	@Override
	public Patient getPatient(int patientID ) {
	    Patient patient= null;
	    try {
	        String sql = "SELECT * FROM patients WHERE patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1,patientID);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String patientName = resultSet.getString("name");
	            String surname = resultSet.getString("surname");
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("dateofbirth"); 
	            String email = resultSet.getString("email"); 
	            
	            patient = new Patient (patientName, sex, dateOfBirth, email);
	            
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patient;
	}
	@Override
	public void changePatient(Patient a) {
	    try {
	        String sql = "UPDATE patients SET name = ?, sex = ?, dateofbirth = ?, email = ? WHERE patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, a.getPatientName());
	        statement.setString(2, a.getSex());
	        statement.setDate(3, a.getDateofbirth());
	        statement.setString(4, a.getEmail());
	        statement.setInt(5, a.getPatientID());
	        
	        int rowsUpdated = statement.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            System.out.println("Patient information updated successfully.");
	        } else {
	            System.out.println("No patient found with the given ID.");
	        }
	        
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error updating patient information: " + e.getMessage());
	    }
	}


	@Override
	public List<Doctor> getDoctors(int patientId) {
	    List<Doctor> doctors = new ArrayList<>();
	    try {
	        String sql = "SELECT doctors.* FROM doctors " +
	                     "INNER JOIN Patient_Doctor ON doctors.doctor_id = Patient_Doctor.doctor_id " +
	                     "WHERE Patient_Doctor.patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, patientId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int doctorId = resultSet.getInt("doctor_id");
	            String doctorName = resultSet.getString("ame");
	            String doctorSurname = resultSet.getString("surname");
	            String specialization = resultSet.getString("specialty");
	            String contact = resultSet.getString("contact");
	            Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname, specialization, contact);
	            doctors.add(doctor);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return doctors;
	}
	
	public Connection getC() {
		return c;
	}
	public void setC(Connection c) {
		this.c = c;
	}
	public ConnectionManager getConMan() {
		return conMan;
	}
	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

}
