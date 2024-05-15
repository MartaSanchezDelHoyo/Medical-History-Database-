package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.pojos.Treatment;


public class JDBCTreatmentManager implements TreatmentManager {
	private Connection c;
	private ConnectionManager conMan;
	
	/*
	 * Method that connects this JDBC with the connection manager
	 */
	public JDBCTreatmentManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	
    /**
     * @param
     * @return
     * @exception
     */
    public void addTreatment(Treatment treatment) {
        try {
            String template = "INSERT INTO treatments (treatment_type) VALUES ( ?)";
            PreparedStatement pstmt = c.prepareStatement(template);
            pstmt.setString(1, treatment.getTreatmentType());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error in the database");
            e.printStackTrace();
        }
    }
	
	/*
	 * Method to see the type of a treatment knowing the treatmentID
	 */
	public String getTreatmentType(int treatmentID ) {
		String treatmentType = null;
		try {
			String sql = "SELECT treatment_type FROM treatments WHERE treatment_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, treatmentID);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				treatmentType = rs.getString("treatment_type");
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
	
	/*
	 * Method to get all the information of a treatment by the treatmentID
	 */
	public Treatment getTreatment(int treatment_id) {
	    Treatment treatment = null;
	    try {
	        String sql = "SELECT * FROM treatments WHERE treatment_id= ?";
	        PreparedStatement search = c.prepareStatement(sql);
	        search.setInt(1, treatment_id);
	        ResultSet rs = search.executeQuery();
	        while (rs.next()) {
	            Integer obtainedTreatmentID = rs.getInt("treatment_id");
	            String obtainedTreatmentType = rs.getString("treatment_type");
	            treatment = new Treatment(obtainedTreatmentID, obtainedTreatmentType);
	        }
	        rs.close();
	        search.close();
	    } catch (SQLException e) {
	        System.out.println("Error looking for a treatment");
	        e.printStackTrace();
	    }
	    return treatment;
	}

	
	/*
	 * Method to get the list of treatments of a visit by visit_id
	 */
	public List<Treatment> getTreatments(int visit_id) {
		List<Treatment> listOfTreatments=null;
		
		try {
			String sql = "SELECT t.treatment_id, t.treatment_type FROM visit_treatment AS vt JOIN treatments AS t ON vt.treatment_id=t.treatment_id WHERE vt.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, visit_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer treatmentID = rs.getInt("treatment_id");
				String treatmentType = rs.getString("treatment_type");
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
	
	/*
	 * Getters and setters of the attribute conMan
	 */
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
}
