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
	        String sql = "INSERT INTO patients (name, date_of_birth, contact, blood_type, photo, username) VALUES (?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = c.prepareStatement(sql);

	        statement.setString(1, a.getPatientName());
	        statement.setDate(2, a.getDateofbirth());
	        statement.setString(3, a.getEmail());
	        statement.setString(4, a.getBloodtype());
	        statement.setBytes(5, a.getPhoto());
	        statement.setString(6, a.getUsername());
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
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	            //Falta lista de alergias y de doctores
	            Patient patient = new Patient (patientID, patientName, dateOfBirth, bloodtype, email, photo, username);
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
	public Patient getPatient(int patient_ID ) {
	    Patient patient= null;
	    try {
	        String sql = "SELECT * FROM patients WHERE patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1,patient_ID);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username =resultSet.getString("username");
	          //Falta lista de alergias y de doctores
	            patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username);
	            
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patient;
	}
	
	@Override
	public Patient getPatient(String name ) {
	    Patient patient= null;
	    try {
	        String sql = "SELECT * FROM patients WHERE name = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1,name);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	          //Falta lista de alergias y de doctores
	            patient = new Patient (patientID, patientName, dateOfBirth, bloodtype, email, photo, username);
	            
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
	        String sql = "UPDATE patients SET name = ?, date_of_birth = ?, contact = ?, blood_type = ?, photo = ? WHERE patient_id = ? ";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, a.getPatientName());
	        statement.setDate(2, a.getDateofbirth());
	        statement.setString(3, a.getEmail());
	        statement.setString(4, a.getBloodtype());
	        statement.setBytes(5, a.getPhoto());
	        statement.setInt(6, a.getPatientID());
	        
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
