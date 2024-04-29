package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	@Override
	public void addVisit (Visit temporal) {
		try {
			String template = "INSERT INTO Visit (visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			String template = "INSERT INTO Visit (date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
    public Visit showVisitBy (Hospital temporal) {
		Visit obtained= null;
		try {
			String sql = "SELECT * FROM Visits WHERE hospital_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, temporal.getHospitalID());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Visit showVisitBy (Doctor toSearch) {
		Visit obtained= null;
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
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Visit showVisitBy (Patient toSearch) {
		Visit obtained= null;
		try {
			String sql = "SELECT *d FROM Visits WHERE patient_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getPatientID());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Visit showVisitBy (Treatment toSearch) {
		Visit obtained= null;
		try {
			String sql = "SELECT v.visit_id, v.date, v.observations, v.duration_medication, v.patient_id, v.doctor_id, v.test_id, v.hospital_id FROM visit-treatment AS vt JOIN Visits AS v ON vt.visit_id=v.visit_id WHERE vt.treatment_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getTreatmentID());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Visit showVisitBy (Medication toSearch) {
		Visit obtained= null;
		try {
			String sql = "SELECT d.visit_id, d.date, d.observations, d.duration_medication, d.patient_id, d.doctor_id, d.test_id, d.hospital_id FROM visit-medication AS vm JOIN Visits AS v ON vm.visit_id=v.visit_id WHERE vm.medication_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getMedication_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
	@Override
    public Visit showVisitBy (Test toSearch) {
		Visit obtained= null;
		try {
			String sql = "SELECT * FROM Visits WHERE test_id= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, toSearch.getTest_id());
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer visit_id = rs.getInt("visit_id");
				Date date = rs.getDate("date");
				String observations = rs.getString("observations");
				String duration_medication = rs.getString("duration_medication");
				Integer patient_id = rs.getInt("patient_id");
				Integer doctor_id = rs.getInt("doctor_id");
				Integer test_id = rs.getInt("test_id");
				Integer hospital_id = rs.getInt("visit_id");
				
				obtained = new Visit(visit_id, date, observations, duration_medication, patient_id, doctor_id, test_id, hospital_id);
			}
			return obtained;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return obtained;
	}
}