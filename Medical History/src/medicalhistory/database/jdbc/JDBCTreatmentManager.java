package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;

public class JDBCTreatmentManager implements TreatmentManager {
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCTreatmentManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
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
			rs.close();
			search.close();
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
	public List<Treatment> showTreatment(int visit_id) {
		List<Treatment> listOfTreatments=null;
		
		try {
			String sql = "SELECT t.treatment_id, t.treatment_type FROM visit-treatment AS vt JOIN treatments AS t ON vt.treatment_id=t.treatment_id WHERE vt.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, visit_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer treatmentID = rs.getInt("treatmentID");
				String treatmentType = rs.getString("treatmentType");
				Treatment obtained = new Treatment(treatmentID,treatmentType);
				listOfTreatments.add(obtained);
			}
			rs.close();
			search.close();
			return listOfTreatments;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listOfTreatments;
	}
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
}
