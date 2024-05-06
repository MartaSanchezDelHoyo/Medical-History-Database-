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
	
	public JDBCTestManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
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
	
	@Override
    public Test showTest (int testToSearch) {
		Test obtained = null;
		try {
			String sql = "SELECT * FROM tests WHERE test_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, testToSearch);
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
	
	@Override
	public List<Test> getTestsbyPatient(int patient_id) {
	    List<Test> tests = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM tests WHERE patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setString(1, patient_id);
	       
	        ResultSet resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            int testId = resultSet.getInt("test_id");
	            String testName = resultSet.getString("test_name");
	            Test test = new Test(testId, testName);
	            tests.add(test);
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
