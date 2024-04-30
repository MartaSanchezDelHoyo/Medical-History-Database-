package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Visit;

public class JDBCMedicationManager implements MedicationManager {
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCMedicationManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	
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
			rs.close();
			search.close();
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
}
