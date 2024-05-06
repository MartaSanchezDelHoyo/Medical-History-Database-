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


	public void addHospital (Hospital temporal) {
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

	//metodos de aqui para abajo no añadidos al menu (pipe tonto no lo capta)
	@Override
	public void changeHospital (int hospitalToChange, Hospital temporal) {
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
				String hospital_adress = rs.getString("hospital_adress");
				List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospital_adress);
				List<Visit> visits= conMan.getVisitMan().showVisitByHospital(hospitalID);
				List<String> hospital_specialties =conMan.getHospitalMan().getSpecialtybyHospital(hospital_id);
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, doctors, visits, hospital_specialties );
				
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
				List<Visit> visits= conMan.getVisitMan().showVisitByHospital(hospitalID);
	            Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, visits);
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
	public Hospital getHospitalByVisit(int visit_id){
		Hospital hospital = null;
	    try {
	        String sql = "SELECT hospitals.* FROM hospitals " +
	                     "INNER JOIN Visits ON hospital.hospitalID = Visits.hospitalID " +
	                     "WHERE  Visits.visit_id= ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, visit_id);
	        ResultSet resultSet = statement.executeQuery();

	            int hospitalID = resultSet.getInt("hospitalID");
	            String hospitalName = resultSet.getString("hospitalName");
	            String hospitalAddress = resultSet.getString("hospitalAddress");
	            List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospitalAddress);
	            List<String> hospital_specialties =conMan.getHospitalMan().getSpecialtybyHospital(hospitalID);
	            hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, doctors, hospital_specialties);
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospital;
		
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
	            List<Doctor> doctors= conMan.getDocMan().getDoctorsbyHospital(hospitalAddress);
				List<Visit> visits= conMan.getVisitMan().showVisitByHospital(hospitalID);
				List<String> hospital_specialties =conMan.getHospitalMan().getSpecialtybyHospital(hospitalID);
	            Hospital hospital = new Hospital(hospitalID, hospitalName, hospitalAddress, doctors, visits, hospital_specialties);
	            hospitals.add(hospital);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return hospitals;
		
	}
	
	public List<String> getSpecialtybyHospital(int hospitalID){
		List<String> hospital_specialties = new ArrayList<String>();
		try {
			String sql = "SELECT Hospital_specialties  FROM hospital WHERE hospitalID LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + hospitalID + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				hospital_specialties = conMan.getHospitalMan().getSpecialtybyHospital(hospitalID);
			}
			    search.close();
				rs.close();
			return hospital_specialties;
			
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return hospital_specialties;
	}
	

	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

	
}