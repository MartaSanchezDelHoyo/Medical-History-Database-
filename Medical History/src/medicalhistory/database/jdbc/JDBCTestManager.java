package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
}
