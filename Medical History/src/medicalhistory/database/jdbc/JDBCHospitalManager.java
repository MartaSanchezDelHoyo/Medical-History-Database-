package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.*;

public class JDBCHospitalManager implements HospitalManager {
	private Connection c;
	private ConnectionManager conMan;
	
	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCHospitalManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c= connectionManager.getConnection();
	}

	/**This method allows to add a hospital in the database
     * @param Obj hospital to add the information
     */
	@Override
	public void addHospital (Hospital temporal) {
		try {
			String template = "INSERT INTO hospitals (hospital_name, hospital_adress) VALUES (?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, temporal.getHospitalName());
			pstmt.setString(2, temporal.getHospitalAddress());
			pstmt.setString(3, temporal.getUsername());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}

	/** Update of a hospital
	 * @param the hospital that will get updated
	 */
	@Override
	public void changeHospital (Hospital temporal) {
		try {
			String template = "UPDATE hospitals SET hospital_name= ?, hospital_adress= ?, username=? WHERE hospital_id= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, temporal.getHospitalName());
			pstmt.setString(2, temporal.getHospitalAddress());
			pstmt.setString(3, temporal.getUsername());
			//pstmt.setInt(3, temporal.getHospitalID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	/**Method to get all the information of a hospital by the hospitalID
	 * @param ID of the hospital
	 * @return Obj hospital who's information you want
	 */
	@Override
	public Hospital getHospital (int hospitalID) {
		Hospital obtained = null;
		try {
			String sql = "SELECT * FROM hospitals WHERE hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, hospitalID);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_address");
				String username = rs.getString("username");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, username);
				
			}
			rs.close();
			search.close();
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	
	/**Method to get all the information of a hospital by the hospitalID
	 * @param ID of the hospital
	 * @return Obj hospital who's information you want
	 */
	@Override
	public Hospital getHospitalCI (int hospitalID) {
		Hospital obtained = null;
		try {
			String sql = "SELECT * FROM hospitals WHERE hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, hospitalID);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_address");
				String username = rs.getString("username");
				List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospital_id);
				List<Visit> visits= conMan.getVisitMan().getVisitByHospital(hospital_id);
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, username, doctors, visits);
			}
			rs.close();
			search.close();
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	@Override
	public Hospital getHospitalbyUsername(String username01) {
		Hospital obtained = null;
		try {
			String sql = "SELECT * FROM hospitals WHERE username= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, username01);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_address");
				String username = rs.getString("username");
				List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospital_id);
				List<Visit> visits= conMan.getVisitMan().getVisitByHospital(hospital_id);
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, username, doctors, visits);
				
			}
			rs.close();
			search.close();
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	/**Method to get all the information of a hospital by the visit_id
	 * @param ID of the visit
	 * @return Obj hospital who's information you want
	 */
	@Override
	public Hospital getHospitalByVisit(int visit_id){
		Hospital hospital = null;
	    try {
	        String sql =  "SELECT h.* FROM Visits AS v JOIN hospitals AS h ON v.hospital_id = h.hospital_id WHERE v.visit_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, visit_id);
	        ResultSet resultSet = statement.executeQuery();

	            int hospitalID = resultSet.getInt("hospital_id");
	            String hospitalName = resultSet.getString("hospital_name");
	            String hospitalAddress = resultSet.getString("hospital_address");
	            String username =resultSet.getString("username");
	            hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, username);
	            
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospital;
		
	}
	
	
	/**
	 *Adds all the hospitals where a specific doctor works in to a list
	 *@param ID of the doctor 
	 *@return List of hospitals that fulfill  this condition 
	 */
	@Override
	public List<Hospital> getHospitalByDoctor(int doctor_id){
		List<Hospital> hospitals = new ArrayList<>();
	    try {
	        String sql = "SELECT DISTINCT h.* FROM Visits AS v JOIN hospitals AS h ON v.hospital_id = h.hospital_id WHERE v.doctor_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, doctor_id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int hospitalID = resultSet.getInt("hospital_id");
	            String hospitalName = resultSet.getString("hospital_name");
	            String hospitalAddress = resultSet.getString("hospital_address");
	            String username =resultSet.getString("username");
				Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, username);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
	}
	
	
	/**
	 *Adds all the hospitals where a specific patient works in to a list
	 *@param ID of the patient 
	 *@return List of hospitals that fulfill  this condition 
	 */
	@Override
	public List<Hospital> getHospitalByPatient(int patientID){
		List<Hospital> hospitals = new ArrayList<>();
	    try {
	        String sql = "SELECT DISTINCT h.* FROM Visits AS v JOIN hospitals AS h ON v.hospital_id = h.hospital_id WHERE v.patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, patientID);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int hospitalID = resultSet.getInt("hospital_id");
	            String hospitalName = resultSet.getString("hospital_name");
	            String hospitalAddress = resultSet.getString("hospital_address");
	            String username =resultSet.getString("username");
	            List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospitalID);
				Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, username, doctors);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
		
	}
	
	
	
	/**
	 * Getters and setters of the attribute conMan
	 */

	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

	

	
}