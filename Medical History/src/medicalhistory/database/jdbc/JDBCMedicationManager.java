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
	
	/**
	 * Constructor of the object that receives as a parameter a connection manager to connect with the database
	 * @param connectionManager
	 */
	public JDBCMedicationManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	/** To add a medication into the database
	 * @param Obj (entry) we want to add (Medication)
	 */
	@Override
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
	
	
	/** To link a medication to a manufacturer into the database
	  * @param Obj (entry) we want to link with the manufacturer
	 * @param Obj (manu) we want to link with the medication
	 */
	@Override
	public void linkMedicationToManufacturer(Medication entry, Manufacturer manu) {
		try {
			String template = "INSERT INTO manufacturer_medication (medication_id, manufacturer_id) VALUES ( ?, ? )";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, entry.getMedication_id());
			pstmt.setInt(2, manu.getManufacturerID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	
	/** To add a manufacturer into the database
	 * @param Obj (entry) we want to add (Manufacturer) 
	 */
	@Override
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
	
	/** To modify a medication into the database, selecting the medication we want to change 
	 * @param Obj the medication that will get updated
	 */
	@Override
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
	
	/**Method to show all the information of a medication  
	 * @param Obj the visit that we want to see the medication
	 * @return Obj the medicatin complete
	 */
	@Override
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
			System.out.println("Error looking for the medicine");
			e.printStackTrace();
		}
		return obtained;
	}
	
	/**Method to show all the information of a medication 
	 * @param name of the medication
	 * @return Obj medication with all the information
	 */
		@Override
		public Medication showMedication(String name) {
			Medication obtained = null;
			try {
				String sql = "SELECT * FROM medications WHERE medication_type= ?";
				PreparedStatement search = c.prepareStatement(sql);
				search.setString(1, name);
				ResultSet rs = search.executeQuery();
				while(rs.next()) {
					Integer medication_id = rs.getInt("medication_id");
					String type = rs.getString("medication_type");
					obtained = new Medication(medication_id,type);
				}
				rs.close();
				search.close();
				return obtained;
			} catch (SQLException e) {
				System.out.println("Error looking for the medicine");
				e.printStackTrace();
			}
			return obtained;
		}
		
	/** To show the medications related with a visit, by introducing the id
	 * @param ID of the visit of which we want to see the medication
	 * @return List of the medications related with that visit 
	 */
	@Override
	public List<Medication> showMedications(int visit_id) {
		List<Medication> listOfMedications= new ArrayList<Medication>();

		try {
			String sql = "SELECT m.medication_id, m.medication_type FROM visit_medication AS vm JOIN medications AS m ON vm.medication_id=m.medication_id WHERE vm.visit_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, visit_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer medication_id = rs.getInt("medication_id");
				String type = rs.getString("medication_type");
				List<Manufacturer> listOfManufacturers = conMan.getMedicationMan().showManufacturers(medication_id);
				Medication obtained = new Medication(medication_id,type, listOfManufacturers);
				listOfMedications.add(obtained);
			}
			rs.close();
			search.close();
			return listOfMedications;
		} catch (SQLException e) {
			System.out.println("Error looking for the medicines");
			e.printStackTrace();
		}
		return listOfMedications;
	}
	
	
	/** To show the medications related with a manufacturer, the ones that produces
	 * @param ID of the manufacturer of which we want to see the medications it produces
	 * @return List of the medications related with that manufacturer  
	 */
	@Override
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
	
	/** To show the manufacturers related with a medication, the ones that manufacture that medication
	 * @param ID of the medication of which we want to see the manufacturers
	 * @return List of the manufacturers related with that medication  
	 */
	@Override
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
	
	/** To show the manufacturers related with a medication, the ones that manufacture that medication with their medications too
	 * @param ID of the medication of which we want to see the manufacturers
	 * @return List of the manufacturers related with that medication  
	 */
	@Override
	public List<Manufacturer> showManufacturerWithMedications(int medication_id) {
		List<Manufacturer> listOfManufacturers= new ArrayList<Manufacturer>();

		try {
			String sql = "SELECT m.manufacturer_id, m.manufacturer_name FROM manufacturer_medication AS mm JOIN manufacturers AS m ON mm.manufacturer_id=m.manufacturer_id WHERE mm.medication_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, medication_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer manufacturer_id = rs.getInt("manufacturer_id");
				String manufacturer_name = rs.getString("manufacturer_name");
				List<Medication> listOfMedications=conMan.getMedicationMan().showMedicationsByManufacturer(medication_id);
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
