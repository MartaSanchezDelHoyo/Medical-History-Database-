package medicalhistory.database.jdbc;
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
	public List<Patient> getPatientByName(String name) {
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM patients WHERE name = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, name);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String patientName = resultSet.getString("name");
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("dateofbirth"); 
	            String bloodtype = resultSet.getString("bloodtype");
	            String email = resultSet.getString("email"); 
	            
	            Patient patient = new Patient (patientName, sex, dateOfBirth, bloodtype, email);
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
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("dateofbirth"); 
	            String bloodtype = resultSet.getString("bloodtype");
	            String email = resultSet.getString("email"); 
	            
	            patient = new Patient (patientName, sex, dateOfBirth, bloodtype, email);
	            
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
	public List<Patient> getPatients(int doctorId){
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT patients.* FROM patients " +
	                     "INNER JOIN Patient_Doctor ON patients.patient_id = Patient_Doctor.patient_id " +
	                     "WHERE Patient_Doctor.doctor_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, doctorId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            String patientName = resultSet.getString("name");
	            String sex = resultSet.getString("sex");
	            Date dateOfBirth = resultSet.getDate("dateofbirth"); 
	            String bloodtype = resultSet.getString("bloodtype");
	            String email = resultSet.getString("email");
	            Patient patient = new Patient (patientName, sex, dateOfBirth, bloodtype, email);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return patients;
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
