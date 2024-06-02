package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.interfaces.AllergiesManager;
import medicalhistory.database.pojos.Allergies;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Treatment;

public class JDBCAllergiesManager implements AllergiesManager{
	private Connection c;
	private ConnectionManager conMan;
	
	
	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCAllergiesManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	/**
	 * To add an allergy into the program
	 * @param Obj allergies we want to add
	 */
	@Override
	 public void addAllergy(Allergies allergies) {
	        try {
	            String template = "INSERT INTO allergies (allergy_type) VALUES (?)";;
	            PreparedStatement pstmt = c.prepareStatement(template);
	            pstmt.setString(1, allergies.getAllergiesName());
	            pstmt.executeUpdate();
	            pstmt.close();
	        } catch (SQLException e) {
	            System.out.println("Error in the database");
	            e.printStackTrace();
	        }
	    }
	
	 /** Update of a allergy
		 * @param Obj the allergy that will get updated
		 */
		@Override
		public void changeAllergy (Allergies allergies) {
			try {
				String template = "UPDATE allergies SET allergy_type= ? WHERE allergy_id= ?";
				PreparedStatement pstmt;
				pstmt = c.prepareStatement(template);
				pstmt.setString(1, allergies.getAllergiesName());
				pstmt.setInt(2, allergies.getAllergiesID());
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("Error in the database");
				e.printStackTrace();
			}
		}
		
		/**Method to get all the information of an allergy by the allergy_id
		 * @param ID of the allergy
		 * @return Obj allergy who's information you want
		 */
		@Override
	    public Allergies getAllergy(int allergy_id ) {
		 Allergies allergy = null;
			try {
				String sql = "SELECT * FROM allergies WHERE allergy_id= ?";
				PreparedStatement search = c.prepareStatement(sql);
				search.setInt(1, allergy_id);
				ResultSet rs = search.executeQuery();
				while(rs.next()) {
					Integer allergy_Id = rs.getInt("allergy_id");
					String allergy_type = rs.getString("allergy_type");
					allergy= new Allergies(allergy_Id, allergy_type);
				}
				rs.close();
				search.close();
				
			} catch (SQLException e) {
				System.out.println("Error looking for a doctor");
				e.printStackTrace();
			}
			return allergy;
		}
		
		/** To show the allergies related with the patient we have selected
		 * @param ID of the patient
		 * @return Obj the list of allergies that have the patient selected
		 */
		@Override
	    public List<Allergies> getAllergies(int patient_id) {
			List<Allergies> listOfAllergies =  new ArrayList<>();
			
			try {
				String sql = "SELECT a.allergy_id, a.allergy_type FROM patient_allergy AS pa JOIN allergies AS a ON pa.allergy_id=a.allergy_id WHERE pa.patient_id= ?";
				PreparedStatement search = c.prepareStatement(sql);
				search.setInt(1, patient_id);
				ResultSet rs = search.executeQuery();
				while(rs.next()) {
					Integer allergy_id = rs.getInt("allergy_id");
					String allergy_type = rs.getString("allergy_type");
					Allergies obtained = new Allergies(allergy_id, allergy_type);
					listOfAllergies.add(obtained);
				}
				rs.close();
				search.close();
				
			} catch (SQLException e) {
				System.out.println("Error looking for a doctor");
				e.printStackTrace();
			}
			return listOfAllergies;
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
