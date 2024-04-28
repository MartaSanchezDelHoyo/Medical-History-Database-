package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public class JDBCHospitalManager implements HospitalManager {
	private Connection c;
	private ConnectionManager conMan;

	public JDBCHospitalManager(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Visit showVisit(ArrayList<Visit> list_visits, Integer visit_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Treatment showTreatment(ArrayList<Treatment> list_treatment, Integer treatmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medication showMedication(ArrayList<Medication> list_medication, int medication_id) {
		// TODO Auto-generated meta ver hod stub
		return null;
	}
	
	@Override
	public void addTest(ArrayList<Test> list_test) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMedication(ArrayList<Medication> list_medication) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addManufacturer(ArrayList<Manufacturer> list_manufacturer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMedication(ArrayList<Medication> list_medication, Integer manufacturerID) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
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
	public Hospital showHospitalBy (String specialization) {
		
		return null;
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
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, null, null, null);
				
			}
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
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, null, null, null);
				
			}
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
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, null, null, null);
				
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	//Habria que hacer una lista de treatment en hospital ++REVISAR++
	@Override
	public Hospital showHospitalBy (Treatment toSearch) {
		Hospital obtained = null;
		try {
			String sql = "SELECT h.hospital_id, h.hospital_name, h.hospital_adress FROM Visits AS v JOIN treatments AS t ON v.treatment_id=t.treatment_id JOIN hospitals AS h ON v.hospital_id=h.hospital_id GROUP BY t.treatment_id WHERE t.treatment_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getTreatmentID());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, null, null, null);
				
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	//LO mismo que el de antes ++REVISAR++
	@Override
	public Hospital showHospitalBy (Test toSearch) {
		Hospital obtained = null;
		try {
			String sql = "SELECT h.hospital_id, h.hospital_name, h.hospital_adress FROM Visits AS v JOIN hospitals AS h ON v.hospital_id=h.hospital_id GROUP BY t.test_id WHERE v.test_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getTest_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer hospital_id = rs.getInt("hospital_id");
				String hospital_name = rs.getString("hospital_name");
				String hospital_adress = rs.getString("hospital_adress");
				obtained = new Hospital(hospital_id, hospital_name, hospital_adress, null, null, null);
				
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Test showTest (Visit toSearch) {
		Test obtained = null;
		try {
			String sql = "SELECT t.test_id, t.test_type FROM Visits AS v JOIN test AS t ON v.test_id=t.test_id WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer test_id = rs.getInt("test_id");
				String test_type = rs.getString("test_type");
				
				obtained = new Test(test_id, test_type);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
}

