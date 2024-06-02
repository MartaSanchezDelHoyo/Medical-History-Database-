package medicalhistory.database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.pojos.*;
import medicalhistory.database.interfaces.DoctorManager;

public class JDBCDocManager implements DoctorManager {
	private Connection c;
	private ConnectionManager conMan;

	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCDocManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
 
	/**
	 *This method allows to add a doctor in the database
	 *@param to the object Doctor with the  actualized information 
	 * @throws SQLException 
	 */
	@Override
	public void addDoctor(Doctor a) throws SQLException {
	
			String template = "INSERT INTO doctors (name, surname, specialty, contact, photo, username) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			try {
		
			pstmt = c.prepareStatement(template);
			System.out.println("1");
			pstmt.setString(1, a.getName());
			System.out.println("2");
			pstmt.setString(2, a.getSurname());
			System.out.println("3");
			pstmt.setString(3, a.getSpecialty());
			System.out.println("4");
			pstmt.setString(4, a.getContact());
			System.out.println("5");
			pstmt.setBytes(5, a.getPhoto());
			System.out.println("6");
			pstmt.setString(6, a.getUsername());
			System.out.println("7");
			pstmt.executeUpdate();
			System.out.println("8");
			pstmt.close();
		} catch (SQLException e) {
			 throw new SQLException("Error creating the object") ;
		}
		
	}

	/**
	 *Updates the data of a doctor that already exists in the database
	 *@param a is the object Doctor that has the updated information 
	 */
	@Override
	public void changeDoctor(Doctor a) {
		String template = "UPDATE doctors SET name = ?, surname = ?,specialty = ?,contact = ?, photo=?, username=? WHERE doctor_id = ?";
		PreparedStatement search;
		try {
			search = c.prepareStatement(template);
			search.setInt(7,   a.getDoctor_id());
		    search.setString(1, a.getName());
		    search.setString(2, a.getSurname());
		    search.setString(3, a.getSpecialty());
		    search.setString(4, a.getContact());
		    search.setBytes(5, a.getPhoto());
		    search.setString(6, a.getUsername());
		    search.executeUpdate();
			search.close();
			
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		
	}


	/**
	 *Returns an object doctor that has all the information of a doctor from the database (except all list) with an specified id
	 *@param id is the id from the doctor we want to get from the database
	 *@return Doctor object with all the actualized information from the database
	  */
	@Override
	public Doctor getDoctor(int id) {
		try {
			String sql = "SELECT * FROM doctors WHERE doctor_id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			byte[] photo =rs.getBytes("photo");
			Doctor a = new Doctor (rs.getInt("doctor_id"), rs.getString("name"), rs.getString("surname"),rs.getString("username"), rs.getString("specialty"),rs.getString("contact"), photo);
			return a;} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
		return null;
	
	}
	/**
	 *Returns an object doctor that has all the information of a doctor from the database with an specified id
	 *@param id is the id from the doctor we want to get from the database
	 *@return Doctor object with all the actualized information from the database
	  */
	@Override
	public Doctor getDoctorCI(int id) {
		try {
			String sql = "SELECT * FROM doctors WHERE doctor_id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(id);
			List<Patient> patients= conMan.getPatientMan().getPatientsByDoctor(id);
			List<Visit> visits = conMan.getVisitMan().getVisitByDoctor(id);
			byte[] photo =rs.getBytes("photo");
			Doctor a = new Doctor (rs.getInt("doctor_id"), rs.getString("name"), rs.getString("surname"),rs.getString("username"), rs.getString("specialty"),rs.getString("contact"), photo, patients, hospitals, visits);
			return a;} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
		return null;
	
	}
	
	/**
	 *Adds the doctors that have the username the same as the parameter
	 *@param usern is the username of the doctor we are searching
	 *@return the doctor that fulfill this condition 
	 */
	@Override
	public Doctor getDoctorsbyUsername(String usern) {
		Doctor newDoctor=null;
		try {
			String sql = "SELECT * FROM doctors WHERE username LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + usern + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatientsByDoctor(doctor_id);
				List<Visit> visits = conMan.getVisitMan().getVisitByDoctor(doctor_id);
				newDoctor = new Doctor(doctor_id, name, surname,username, specialty, contact,photo, patients, hospitals, visits);
			}
			    search.close();
				rs.close();
				
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return newDoctor;
	}
	
	/**
	 *Adds all the doctors to list that have as specialty the same as the parameter
	 *@param specialty is the specialty we want to group the doctors by 
	 *@return List of doctors that fulfill  this condition 
	 */
	@Override
	public List<Doctor> getDoctorsbySpecialties(String specialty) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors WHERE specialty = ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + specialty + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty_ = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatientsByDoctor(doctor_id);
				List<Visit> visits = conMan.getVisitMan().getVisitByDoctor(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, username, specialty_, contact, photo,patients, hospitals, visits);
				doctors.add(newDoctor);
			}
			
			    search.close();
				rs.close();
			return doctors;
			
			
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}
	
	/**
	 *Adds all the doctors to list that attends an specific patient 
	 *@param patientId is the patient we want to group the doctors by 
	 *@return List of doctors that fulfill  this condition 
	 */
	@Override
	public List<Doctor> getDoctorsByPatient(int patientId) {
		List<Doctor> doctors = new ArrayList<Doctor>();
	    try {
	        String sql = "SELECT DISTINCT d.* FROM Visits AS v JOIN doctors AS d ON v.doctor_id = d.doctor_id WHERE v.patient_id = ?";
	        PreparedStatement search = c.prepareStatement(sql);
	        search.setInt(1, patientId);
	        ResultSet rs = search.executeQuery();

	        while (rs.next()) {
	        	Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				//NO meter ninguna lista (Pablo)
				Doctor newDoctor = new Doctor(doctor_id, name, surname, username, specialty, contact, photo);
	            doctors.add(newDoctor);
	        }
	        
	        rs.close();
	        search.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving patients by name: " + e.getMessage());
	    }
	    return doctors;
	}
	
	

	/**
	 *Adds all the doctors to list that work in the hospital that the parameter indicates
	 *@param hospitalID is the hospital we want to group the doctors by 
	 *@return List of doctors that fulfill  this condition 
	 */
	@Override
	public List<Doctor> getDoctorsbyHospital(int hospitalID) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT DISTINCT d.* FROM Visits AS v JOIN doctors AS d ON v.doctor_id=d.doctor_id WHERE v.hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1,hospitalID );
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				//NO meter ninguna lista 
				Doctor newDoctor = new Doctor(doctor_id, name, surname,username, specialty, contact, photo);
				doctors.add(newDoctor);
			}
			search.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}
	
	/**
	 *Adds all the doctors to a list that has the same name and surname that the parameter indicates
	 *@param name_ is the name we want to group the doctors by 
	 *@param surname_ is the surname we want to group the doctors by 
	 *@return List of doctors that fulfill  this condition 
	 */
	@Override
	public List<Doctor> getDoctorByNameSurname(String name_, String surname_) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors WHERE name LIKE ? AND surname LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + name_ + "%");
			search.setString(2, "%" + surname_ + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatientsByDoctor(doctor_id);
				List<Visit> visits = conMan.getVisitMan().getVisitByDoctor(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, username, specialty, contact, photo,patients, hospitals, visits);
				doctors.add(newDoctor);
			}
			search.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}

	/**
	 *Adds all the doctors to a list that has the same name and surname that the parameter indicates 
	 *@return List of doctors that fulfill  this condition 
	 */
	@Override
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors";
			PreparedStatement search = c.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				byte[] photo =rs.getBytes("photo");
				String username = rs.getString("username");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatientsByDoctor(doctor_id);
				List<Visit> visits = conMan.getVisitMan().getVisitByDoctor(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, username, specialty, contact, photo,patients, hospitals, visits);
				doctors.add(newDoctor);
			}
			search.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}

	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

}
