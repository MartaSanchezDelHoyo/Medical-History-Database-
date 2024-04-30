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
	
	public JDBCHospitalManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c= connectionManager.getConnection();
	}


	public void AddHospital (Hospital temporal) {
		try {
			String template = "INSERT INTO hospitals (hospital_name, hospital_adress) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, temporal.getHospitalName());
			pstmt.setString(2, temporal.getHospitalAddress());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	@Override
	public void ChangeHospital (int hospitalToChange,Hospital temporal) {
		try {
			String template = "UPDATE hospitals SET hospital_name= ?, hospital_adress= ?, WHERE hospital_id= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, temporal.getHospitalName());
			pstmt.setString(2, temporal.getHospitalAddress());
			pstmt.setInt(3, hospitalToChange);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}	
	@Override
	public Hospital showHospital (int hospitalID) {
		Hospital obtained = null;
		try {
			String sql = "SELECT * FROM hospitals WHERE hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, hospitalID);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress);
				
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
	public Hospital showHospitalBy (Doctor toSearch) {
		Hospital obtained = null;
		try {
			String sql = "SELECT h.hospital_id, h.hospital_name, h.hospital_adress FROM hospital-doctor AS hd JOIN hospitals AS h ON hd.hospital_id=h.hospital_id WHERE hd.doctor_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getDoctor_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress);
				
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
	public Hospital showHospitalBy (Patient toSearch) {
		Hospital obtained = null;
		try {
			String sql = "SELECT h.hospital_id, h.hospital_name, h.hospital_adress FROM Visits AS v JOIN hospitals AS h ON v.hospital_id=h.hospital_id WHERE v.patient_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getPatientID());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress);
				
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
	public Hospital showHospitalBy (Visit toSearch) {
		Hospital obtained = null;
		try {
			String sql = "SELECT h.hospital_id, h.hospital_name, h.hospital_adress FROM Visits AS v JOIN hospitals AS h ON v.hospital_id=h.hospital_id WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress);
				
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
	public List<Hospital> getHospitalByDoctor(int doctor_id){
		List<Hospital> hospitals = new ArrayList<>();
	    try {
	        String sql = "SELECT hospitals.* FROM hospitals " +
	                     "INNER JOIN hospital-doctor ON hospital.hospitalID = hospital-doctor.hospitalID " +
	                     "WHERE  hospital-doctor.doctor_id= ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, doctor_id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int hospitalID = resultSet.getInt("hospitalID");
	            String hospitalName = resultSet.getString("hospitalName");
	            String hospitalAddress = resultSet.getString("hospitalAddress");
	            Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
	}
	
	@Override
	public List<Hospital> getHospitalByVisit(int visit_id){
		List<Hospital> hospitals = new ArrayList<>();
	    try {
	        String sql = "SELECT hospitals.* FROM hospitals " +
	                     "INNER JOIN Visits ON hospital.hospitalID = Visits.hospitalID " +
	                     "WHERE  Visits.visit_id= ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, visit_id);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int hospitalID = resultSet.getInt("hospitalID");
	            String hospitalName = resultSet.getString("hospitalName");
	            String hospitalAddress = resultSet.getString("hospitalAddress");
	            Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
		
	}
	
	@Override
	public List<Hospital> getHospitalByPatient(int patientID){
		List<Hospital> hospitals = new ArrayList<>();
	    try {
	        String sql = "SELECT hospitals.* FROM hospitals " +
	                     "INNER JOIN Visits ON hospital.hospitalID = Visits.hospitalID " +
	        		     "INNER JOIN patients ON Visits. patient_id =patients.patientID "+
	                     "WHERE  patients.patientID= ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, patientID);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int hospitalID = resultSet.getInt("hospitalID");
	            String hospitalName = resultSet.getString("hospitalName");
	            String hospitalAddress = resultSet.getString("hospitalAddress");
	            Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
		
	}
	

	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

	
}