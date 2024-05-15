package medicalhistory.database.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.pojos.Doctor;
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
	
	/**
	 * To add a medication into the database
	 */
	public void addMedication(Medication entry) {
		try {
			String template = "INSERT INTO medications (medication_type) VALUES ( ? )";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, entry.getType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	
	/**
	 * To add a manufacturer into the database
	 */
	
	public void addManufacturer( Manufacturer entry ) {
		try {
			String template = "INSERT INTO manufacturers (manufacturer_name) VALUES ( ? )";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, entry.getManufacturerName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}		
	}
	
	/**
	 * To modify a medication into the database, selecting the medication we want to change by the id, and introducing the new medication already modified
	 */
	
	public void modifyMedication(Medication entry) {
		try {
			String template = "UPDATE medications SET type= ?, WHERE medication_id= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, entry.getType());
			pstmt.setInt(2, entry.getMedication_id());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}		
	}
	
	//metodos (este y el siguiente) no añadidos al menu (pipe tonto no lo capta)
	public Medication showMedication(Visit toSearch) {
		Medication obtained = null;
		try {
			String sql = "SELECT m.medication_id, m.medication_type FROM Visits AS v JOIN medications AS m ON v.medication_id=m.medication_id WHERE v.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getVisit_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer medication_id = rs.getInt("medication_id");
				String type = rs.getString("medication_type");
				List<Manufacturer> listOfManufacturers = conMan.getMedicationMan().showManufacturers(medication_id);
				obtained = new Medication(medication_id,type, listOfManufacturers);
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
	/**
	 * To show the medications related with a visit, by introducing the id
	 */
	public List<Medication> showMedications(int visit_id) {
		List<Medication> listOfMedications= new ArrayList<Medication>();

		try {
			String sql = "SELECT m.medication_id, m.medication_type FROM visit_medication AS vm JOIN medications AS m ON vm.medication_id=m.medication_id WHERE vm.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, visit_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer medication_id = rs.getInt("medication_id");
				String type = rs.getString("type");
				List<Manufacturer> listOfManufacturers = conMan.getMedicationMan().showManufacturers(medication_id);
				Medication obtained = new Medication(medication_id,type, listOfManufacturers);
				listOfMedications.add(obtained);
			}
			rs.close();
			search.close();
			return listOfMedications;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listOfMedications;
	}
	
	
	/**
	 * To show the medications related with a manufacturer, the ones that produces
	 */
	public List<Medication> showMedicationsByManufacturer(int manufacturer_id) {
		List<Medication> listOfMedications= new ArrayList<Medication>();

		try {
			String sql = "SELECT m.medication_id, m.medication_type FROM manufacturer_medication AS mm JOIN medications AS m ON mm.medication_id=m.medication_id WHERE mm.manufacturer_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, manufacturer_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer medication_id = rs.getInt("medication_id");
				String type = rs.getString("type");
				Medication obtained = new Medication(medication_id,type);
				listOfMedications.add(obtained);
			}
			rs.close();
			search.close();
			return listOfMedications;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listOfMedications;
	}
	
	/**
	 * to show all the manufacturers that are in the database
	 */
	public List<Manufacturer> showManufacturers(int medication_id) {
		List<Manufacturer> listOfManufacturers= new ArrayList<Manufacturer>();

		try {
			String sql = "SELECT m.manufacturer_id, m.manufacturer_name FROM manufacturer_medication AS mm JOIN manufacturers AS m ON mm.manufacturer_id=m.manufacturer_id WHERE mm.medication_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, medication_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer manufacturer_id = rs.getInt("manufacturer_id");
				String manufacturer_name = rs.getString("manufacturer_name");
				Manufacturer obtained = new Manufacturer(manufacturer_id, manufacturer_name);
				listOfManufacturers.add(obtained);
			}
			rs.close();
			search.close();
			return listOfManufacturers;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listOfManufacturers;
	}
	
	/**
	 * To show the manufcturer we have selected with all the medications that produces
	 */
	public List<Manufacturer> showManufacturerWithMedications(int manufacturerId) {
		List<Manufacturer> listOfManufacturers= new ArrayList<Manufacturer>();

		try {
			String sql = "SELECT m.manufacturer_id, m.manufacturer_name FROM manufacturer-medication AS mm JOIN manufacturers AS m ON mm.manufacturer_id=m.manufacturer_id WHERE mm.medication_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, manufacturerId);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer manufacturer_id = rs.getInt("manufacturer_id");
				String manufacturer_name = rs.getString("manufacturer_name");
				List<Medication> listOfMedications=conMan.getMedicationMan().showMedicationsByManufacturer(manufacturerId);
				Manufacturer obtained = new Manufacturer(manufacturer_id,manufacturer_name, listOfMedications);
				listOfManufacturers.add(obtained);
			}
			rs.close();
			search.close();
			return listOfManufacturers;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listOfManufacturers;
	}
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}


}
