package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Treatment;
import medicalhistory.database.pojos.Visit;
public class JDBCVisitManager implements VisitManager {
	private Connection c;
	private ConnectionManager conMan;
	
	
	public JDBCVisitManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
	
	/**
	 * To add a visit into the program
	 * @throws SQLException 
	 */
	
	@Override
	public void addVisit (Visit temporal) throws SQLException {
		try {
			String template = "INSERT INTO Visits (date, observations, patient_id, doctor_id, test_id, hospital_id) VALUES ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setDate(1, temporal.getVisit_date());
			pstmt.setString(2, temporal.getVisit_observation());
			pstmt.setInt(3, temporal.getVisit_patient().getPatientID());
			pstmt.setInt(4, temporal.getVisit_doctor().getDoctor_id());
			if(temporal.getVisit_test()==null) {
				pstmt.setInt(5, 0);
			}else {
			pstmt.setInt(5, temporal.getVisit_test().getTest_id());
			}
			pstmt.setInt(6, temporal.getHospital().getHospitalID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
			throw new SQLException("Error creating the visit, please check all data are provided ");
		}
	}
	
	
	/**
	 * To link a medication to a visit into the database
	 */
	@Override
	public void linkMedicationToVisit(Visit visit, Medication medi) {
		try {
			String template = "INSERT INTO visit_medication (medication_id, visit_id) VALUES ( ?, ? )";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, medi.getMedication_id());
			pstmt.setInt(2, visit.getVisit_id());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	
	/**
	 * To link a treatment to a visit into the database
	 */
	@Override
	public void linkTreatmentToVisit(Visit visit, Treatment treat) {
		try {
			String template = "INSERT INTO visit_treatment (treatment_id, visit_id) VALUES ( ?, ? )";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, treat.getTreatmentID());
			pstmt.setInt(2, visit.getVisit_id());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}			
	}
	
	/**
	 * To change the visit selected, introducing the new visit changed
	 */
	
	@Override
    public void changeVisit (Visit temporal) {
		try {
			String template = "UPDATE Visits SET date = ?, observations = ?, patient_id = ?, doctor_id = ?, test_id = ?, hospital_id = ? WHERE visit_id= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setDate(1, temporal.getVisit_date());
			pstmt.setString(2, temporal.getVisit_observation());
			pstmt.setInt(3, temporal.getVisit_patient().getPatientID());
			pstmt.setInt(4, temporal.getVisit_doctor().getDoctor_id());
			pstmt.setInt(5, temporal.getVisit_test().getTest_id());
			pstmt.setInt(6, temporal.getHospital().getHospitalID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	
		/**
		 * To show the visits related with the doctor we have selected (By the object)
		 */
		
		@Override
	    public Visit getVisit (int visitID) {
			
			Visit obtained=null;
			
			try {
				String sql = "SELECT * FROM Visits WHERE doctor_id= ?";
				PreparedStatement search = c.prepareStatement(sql);
				search.setInt(1, visitID);
				ResultSet rs = search.executeQuery();
				while(rs.next()) {
					Integer visit_id = rs.getInt("visit_id");
					Date date = rs.getDate("date");
					String observations = rs.getString("observations");
					Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
					Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
					Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
					Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
					List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
					List<Treatment> listOfTreatments= conMan.getTreatmentMan().getTreatments(visit_id);
					obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
	 * To show visits that are in the hospital we have selected
	 */
	@Override
    public List<Visit> getVisitByHospital (int hospital_id) { 
		List<Visit> listVisit= new ArrayList<Visit>();
		
		try {
			String sql = "SELECT * FROM Visits WHERE hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, hospital_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
				//A lo mejor dederia quitarse (Solo para prueba)
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().getTreatments(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
			    listVisit.add(obtained);
			}
			rs.close();
			search.close();
			return listVisit;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listVisit;
	}
	
	/**
	 * To show the visits related on the test we have selected
	 */
	@Override
    public List<Visit> getVisitByTest (int test_id) { 
		List<Visit> listVisit= new ArrayList<Visit>();
		
		try {
			String sql = "SELECT * FROM Visits WHERE test_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, test_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().getTreatments(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
			    listVisit.add(obtained);
			}
			rs.close();
			search.close();
			return listVisit;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listVisit;
	}
	
	
	//No se si meterle tambien el resto de los otros tratamientos( si tiene, el list<Treatments>)
	/**
	 * To show visits related with the treatment we have selected
	 */
	@Override
    public List<Visit> getVisitByTreatment (int treatment_id) { 
		List<Visit> listVisit= new ArrayList<Visit>();
		
		try {
			String sql = "SELECT v.* FROM visit_treatment AS vt JOIN Visits AS v ON vt.visit_id = v.visit_id WHERE vt.treatment_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, treatment_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				
				Visit obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications);
			    listVisit.add(obtained);
			}
			rs.close();
			search.close();
			return listVisit;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listVisit;
	}
	
	
	/**
	 * To show the visits related with the doctor we have selected
	 */
	@Override
    public List<Visit> getVisitByDoctor (int doctor_id) {
		List<Visit> listVisit= new ArrayList<Visit>();
		try {
			String sql = "SELECT * FROM Visits WHERE doctor_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, doctor_id);
			ResultSet rs = search.executeQuery();
			
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().getTreatments(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
				listVisit.add(obtained);
			}
			rs.close();
			search.close();
			return listVisit;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listVisit;
	}
	
	/**
	 * To show the visits related with the patient  we have selected
	 */
	
	@Override
    public List<Visit> getVisitByPatient (int patient_id) {
		List<Visit> listVisit= new ArrayList<Visit>();
		try {
			String sql = "SELECT * FROM Visits WHERE patient_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, patient_id);
			ResultSet rs = search.executeQuery();
			
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().getTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().getHospital(rs.getInt("hospital_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().getTreatments(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
				listVisit.add(obtained);
			}
			rs.close();
			search.close();
			return listVisit;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return listVisit;
	}
	
	@Override
    public void deleteVisit(Visit temporal) {
		try {
			String template = "DELETE FROM Visits WHERE visit_id = ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, temporal.getVisit_id());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
	
}