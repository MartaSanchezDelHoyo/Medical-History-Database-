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
	
    public void addTreatment(Treatment treatment) {
        try {
            String template = "INSERT INTO treatments (treatmentId, type) VALUES (?, ?)";;
            PreparedStatement pstmt = c.prepareStatement(template);
            pstmt.setInt(1, treatment.getTreatmentID());
            pstmt.setString(2, treatment.getTreatmentType());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error in the database");
            e.printStackTrace();
        }
    }
	
	// necesito que me expliquen esto
	public String getTreatmentType(int treatmentID ) {
		String treatmentType = null;
		try {
			String sql = "SELECT treatmentType FROM treatment WHERE treatmentID= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, treatmentID);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				treatmentType = rs.getString("treatmentType");
			}
			rs.close();
			search.close();
			return treatmentType;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return treatmentType;
	}
	
	// necesito que me expliquen esto
	public List<Treatment> getTreatments(int visit_id) {
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
