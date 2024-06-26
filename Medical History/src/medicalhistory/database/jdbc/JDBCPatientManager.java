package medicalhistory.database.jdbc;
import java.sql.*;
import medicalhistory.database.interfaces.PatientManager;
import java.util.ArrayList;
import java.util.List;
import medicalhistory.database.pojos.*;

public class JDBCPatientManager implements PatientManager {
	private Connection c;
	private ConnectionManager conMan;
	
	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCPatientManager(ConnectionManager connectionManager) {
		this.c = connectionManager.getConnection();
		this.setConMan(connectionManager);
	}
	
	/**This method allows to add a patient in the database
     * @param Obj patient to add the information
     */
	@Override
	public void addPatient(Patient a) {
	    try {
	        String sql = "INSERT INTO patients (name, date_of_birth, contact, blood_type, photo, username) VALUES (?, ?, ?, ?, ?)";
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
	/**This method allows to link a patient to an allergy
     * @param Obj patient to link to Obj allergy
     */
	@Override
	public void linkAllergiesToPatient (Patient a, Allergies s){
	    try {
	        String sql = "INSERT INTO patient_allergy (patient_id, allergy_id) VALUES (?, ?)";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, a.getPatientID());
	        statement.setInt(2, s.getAllergiesID());
	        statement.executeUpdate();
	        statement.close();
	        
	        System.out.println("Allergy added to patient successfully");
	    } catch (SQLException e) {
	        System.err.println("Error adding allergy: " + e.getMessage());
	    }
	}
	/** Update of a patient
	 * @param the patient that will get updated 
	 */
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
	/**Method to show all the information of a patient except for the lists by the patient_ID
	 * @param ID of the patient
	 * @return Obj patient who's information you want
	 */
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
	            
	            patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username);
	            
	       }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patient;
	}
	/**Method to show all the information of a patient by the patient_ID
	 * @param ID of the patient
	 * @return Obj patient who's information you want
	 */
	@Override
	public Patient getPatientCI(int patient_ID ) {
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
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            List<Visit> visits = conMan.getVisitMan().getVisitByPatient(patientID);
	            List<Doctor> doctors = conMan.getDocMan().getDoctorsByPatient(patientID);
	            
	            patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username, aller, visits, doctors);
	            
	       }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patient;
	}
	/**Method to get all the information of a patient by the name
	 * @param name of the patient
	 * @return Obj patient who's information you want
	 */
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
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            List<Visit> visits = conMan.getVisitMan().getVisitByPatient(patientID);
	            List<Doctor> doctors = conMan.getDocMan().getDoctorsByPatient(patientID);
	            
	            patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username, aller,visits, doctors);
	            
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patient;
	}
	
	/**
	 *Method to get the patient by its username
	 *@param the patients username 
	 *@return Obj patient the fulfills the username
	 */
	@Override
	public Patient getPatientssbyUsername(String usern) {
		Patient patient=null;
		try {
			String sql = "SELECT * FROM patients WHERE username LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + usern + "%");
			ResultSet resultSet = search.executeQuery();
			
			while(resultSet.next()) {
				int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            List<Visit> visits = conMan.getVisitMan().getVisitByPatient(patientID);
	            List<Doctor> doctors = conMan.getDocMan().getDoctorsByPatient(patientID);
	            
	            patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username, aller,visits, doctors);
			}
			    search.close();
			    resultSet.close();
				
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return patient;
	}
	/**Method to get all the information of a patient by the name
	 * @param name of the patient
	 * @return Obj patient who's information you want
	 */
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
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            List<Visit> visits = conMan.getVisitMan().getVisitByPatient(patientID);
	            List<Doctor> doctors = conMan.getDocMan().getDoctorsByPatient(patientID);
	            
	            Patient patient = new Patient (patientID,patientName, dateOfBirth, bloodtype, email, photo, username, aller,visits, doctors);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patients;
	}
	/**Method to get a list with the information of patients by a hospital_id
	 * @param ID of the hospital
	 * @return List of Obj patient who's information you want
	 */
	@Override
	public List<Patient> getPatientsByHospital(int hospital_id) {
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT DISTINCT p.* FROM Visits AS v JOIN patients AS p ON v.patient_id = p.patient_id WHERE v.hospital_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, hospital_id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            //No se necesitaria ninguna lista
	            //Probar solo con la lista de alergias (Pablo)
	            Patient patient = new Patient (patientID, patientName, dateOfBirth, bloodtype, email, photo, username, aller);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patients;
	}
	/**Method to get a list with the information of patients by a doctor_id
	 * @param ID of the doctor
	 * @return List of Obj patient who's information you want
	 */
	@Override
	public List<Patient> getPatientsByDoctor(int doctor_id) {
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT DISTINCT p.* FROM Visits AS v JOIN patients AS p ON v.patient_id = p.patient_id WHERE v.doctor_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, doctor_id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            //No se necesitaria ninguna lista
	            //Probar solo con la lista de alergias (Pablo)
	            Patient patient = new Patient (patientID, patientName, dateOfBirth, bloodtype, email, photo, username, aller);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return patients;
	}
	
	/**Method to get a list with the information of all patient
	 * @return List of Obj patient
	 */
	@Override
	public List<Patient> getAllPatients() {
	    List<Patient> patients = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM patients";
	        PreparedStatement statement = c.prepareStatement(sql);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int patientID= resultSet.getInt("patient_id");
	        	String patientName = resultSet.getString("name");
	            Date dateOfBirth = resultSet.getDate("date_of_birth"); 
	            String email = resultSet.getString("contact");
	            String bloodtype = resultSet.getString("blood_type");
	            byte[] photo = resultSet.getBytes("photo");
	            String username = resultSet.getString("username");
	            List<Allergies> aller = conMan.getAllergiesMan().getAllergies(patientID);
	            //No se necesitaria ninguna lista
	            //Probar solo con la lista de alergias (Pablo)
	            Patient patient = new Patient (patientID, patientName, dateOfBirth, bloodtype, email, photo, username, aller);
	            patients.add(patient);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
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
