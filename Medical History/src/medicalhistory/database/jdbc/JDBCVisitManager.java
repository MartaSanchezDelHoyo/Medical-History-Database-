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
	@Override
	public void addVisit (Visit temporal) {
		try {
			String template = "INSERT INTO Visit (visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, temporal.getVisit_id());
			pstmt.setDate(2, temporal.getVisit_date());
			pstmt.setString(3, temporal.getVisit_observation());
			pstmt.setString(4, temporal.getDuration_medication());
			pstmt.setInt(5, temporal.getVisit_patient().getPatientID());
			pstmt.setInt(6, temporal.getVisit_doctor().getDoctor_id());
			pstmt.setInt(7, temporal.getVisit_test().getTest_id());
			pstmt.setInt(8, temporal.getHospital().getHospitalID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	@Override
    public void changeVisit (int visitIdToChange, Visit temporal) {
		try {
			String template = "UPDATE Visit SET visit_id = ?, date = ?, observations = ?, duration_medication = ?, patient_id = ?, doctor_id = ?, test_id = ?, hospital_id = ?. WHERE visit_id= ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, temporal.getVisit_id());
			pstmt.setDate(2, temporal.getVisit_date());
			pstmt.setString(3, temporal.getVisit_observation());
			pstmt.setString(4, temporal.getDuration_medication());
			pstmt.setInt(5, temporal.getVisit_patient().getPatientID());
			pstmt.setInt(6, temporal.getVisit_doctor().getDoctor_id());
			pstmt.setInt(7, temporal.getVisit_test().getTest_id());
			pstmt.setInt(8, temporal.getHospital().getHospitalID());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	@Override
    public List<Visit> showVisitByHospital (int hospital_id) { 
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
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().showTreatment(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
    public List<Visit> showVisitByTest (int test_id) { 
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
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().showTreatment(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
	@Override
    public List<Visit> showVisitByTreatment (int treatment_id) { 
		List<Visit> listVisit= new ArrayList<Visit>();
		
		try {
			String sql = "SELECT v.* FROM visit-treatment AS vt JOIN Visits AS v ON vt.visit_id = v.visit_id WHERE vt.treatment_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, treatment_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications);
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
    public List<Visit> showVisitBy (Doctor toSearch) {
		List<Visit> listVisit= new ArrayList<Visit>();
	
		try {
			String sql = "SELECT * FROM Visits WHERE doctor_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getDoctor_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().showTreatment(visit_id);
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
    public List<Visit> showVisitByDoctor (int doctor_id) {
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
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().showTreatment(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
    public List<Visit> showVisitByPatient (int patient_id) {
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
				String duration_medication = rs.getString("duration_medication");
				Patient patient= conMan.getPatientMan().getPatient(rs.getInt("patient_id"));
				Doctor doctor = conMan.getDocMan().getDoctor(rs.getInt("doctor_id"));
				Test test= conMan.getTestMan().showTest(rs.getInt("test_id"));
				Hospital hospital = conMan.getHospitalMan().showHospital(rs.getInt("visit_id"));
				List<Medication> listOfMedications= conMan.getMedicationMan().showMedications(visit_id);
				List<Treatment> listOfTreatments= conMan.getTreatmentMan().showTreatment(visit_id);
				Visit obtained = new Visit(visit_id, date, observations, duration_medication, hospital, patient, doctor, test, listOfMedications, listOfTreatments);
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
	
	
	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}
	
	
}