package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.*;

public class JDBCHospitalManager implements HospitalManager {
	private Connection c;
	private ConnectionManager conMan;
	public JDBCHospitalManager(ConnectionManager connectionManager) {
		// TODO Auto-generated constructor stub
	}


	public Treatment showTreatment(Visit toSearch) {
		Treatment obtained = null;
		try {
			String sql = "SELECT t.treatmentID, t.treatmentType FROM Visits AS v JOIN test AS t ON v.treatmentID=t.treatmentID WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer treatmentID = rs.getInt("treatmentID");
				String treatmentType = rs.getString("treatmentType");

				obtained = new Treatment(treatmentID,treatmentType);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}

	
	public Medication showMedication(Visit toSearch) {
		Medication obtained = null;
		try {
			String sql = "SELECT m.medication_id, m.type FROM Visits AS v JOIN test AS m ON v.medication_id=m.medication_id WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer medication_id = rs.getInt("medication_id");
				String type = rs.getString("type");

				obtained = new Medication(medication_id,type);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}

	@Override
	public void addTest(Test entry) {
		try {
			String template = "INSERT INTO test (test_id, type) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, entry.getTest_id());
			pstmt.setString(2, entry.getType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	@Override
	public void addMedication(Medication entry) {
		try {
			String template = "INSERT INTO medication (medication_id, type) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, entry.getMedication_id());
			pstmt.setString(2, entry.getType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	@Override
	public void addManufacturer( Manufacturer entry ) {
		try {
			String template = "INSERT INTO manufacturer (manufacturerID, manufacturerName) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, entry.getManufacturerID());
			pstmt.setString(2, entry.getManufacturerName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}		
	}
	@Override
	public void modifyMedication(Medication entry, Integer medicationID) {
		try {
			String template = "UPDATE medication SET type= ?, WHERE medicationID= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, entry.getType());
			pstmt.setInt(3, medicationID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}		
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
	
}