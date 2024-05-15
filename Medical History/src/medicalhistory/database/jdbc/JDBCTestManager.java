package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Visit;

public class JDBCTestManager implements TestManager{
	private Connection c;
	private ConnectionManager conMan;
	
	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCTestManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	public void addTest(Test entry) {
		try {
			String template = "INSERT INTO tests (test_type, pdf) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1,entry.getType());
			pstmt.setBytes(2, entry.getArchivoPDF());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	/**
	 *Returns an object test that has all the information of a test from the database that is from a specified visit of the database
	 *@param toSearch is the Visit object with the information we are looking for in the database 
	 *@return Test object with all the actualized information from the database
	  */
@Override
    public Test getTest (Visit toSearch) {
		Test obtained = null;
		try {
			String sql = "SELECT t.test_id, t.test_type FROM Visits AS v JOIN tests AS t ON v.test_id=t.test_id WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer test_id = rs.getInt("test_id");
				String test_type = rs.getString("test_type");
				byte[] archivoPDF= rs.getBytes("pdf");
				obtained = new Test(test_id, test_type, archivoPDF);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	
/**
 *Returns an object test that has all the information of a test from the database that has the corresponding test id 
 *@param testToSearch is the Test object with the information we are looking for in the database 
 *@return Test object with all the actualized information from the database
  */
	@Override
    public Test getTest (int testToSearch) {
		Test obtained = null;
		try {
			String sql = "SELECT * FROM tests WHERE test_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, testToSearch);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer test_id = rs.getInt("test_id");
				String test_type = rs.getString("test_type");
				byte[] archivoPDF= rs.getBytes("pdf");
				obtained = new Test(test_id, test_type, archivoPDF);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	/**
	 *Returns a list of objects test that has all the information of a test from the database that is from a specified patient from the database
	 *@param patient_id is the patient's id of the patient from the database with the information we are looking for 
	 *@return list of Test objects with all the actualized information from the database
	  */
	@Override
	public List<Test> getTestsbyPatient(int patient_id) {
	    List<Test> tests = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM tests WHERE patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, patient_id);
	       
	        ResultSet resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            int test_id = resultSet.getInt("test_id");
	            String test_type = resultSet.getString("test_type");
	            byte[] archivoPDF= resultSet.getBytes("pdf");
	            Test obtained = new Test(test_id, test_type, archivoPDF);
	            tests.add(obtained);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving tests by patient: " + e.getMessage());
	    }
	    return tests;
	}
	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

	
}
