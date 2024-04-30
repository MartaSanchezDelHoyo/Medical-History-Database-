package medicalhistory.database.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import medicalhistory.database.pojos.*;
import medicalhistory.database.interfaces.DoctorManager;

public class JDBCDocManager implements DoctorManager {
	private Connection c;
	private ConnectionManager conMan;

	public JDBCDocManager(ConnectionManager connectionManager) {
		this.setConMan(connectionManager);
		this.c = connectionManager.getConnection();
	}
 
	@Override
	public void addDoctor(Doctor a) {
		try {
			String template = "INSERT INTO doctors (name,surname, speciality,contact) VALUES (?, ?, ?,?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getSurname());
			pstmt.setString(3, a.getSpecialty());
			pstmt.setString(4, a.getContact());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Doctor> getDoctorsbySpecialties(String specialty) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors WHERE specialty LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + specialty + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty_ = rs.getString("specialty");
				String contact = rs.getString("contact");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatients(doctor_id);
				List<Visit> visits = conMan.getVisitMan().showVisitBy(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, specialty_, contact,patients, hospitals, visits);
				doctors.add(newDoctor);
				search.close();
				rs.close();
			}
			return doctors;
			
			
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}
	

	@Override
	public List<Doctor> getDoctorsbyHospital(String hospitalName) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors AS d JOIN hospital-doctor AS hd ON d.doctor_id=hd.doctor_id JOIN hospitals AS h ON hd.hospital_id=h.hospital_id WHERE h.hospital_name= ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + hospitalName + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatients(doctor_id);
				List<Visit> visits = conMan.getVisitMan().showVisitBy(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, specialty, contact,patients, hospitals, visits);
				doctors.add(newDoctor);
			}
			search.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public List<Doctor> getDoctorByNameSurname(String name_, String surname_) {
		List<Doctor> doctors = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctors WHERE name LIKE ? AND surname LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + name_ + "%");
			search.setString(2, "%" + surname_ + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer doctor_id = rs.getInt("doctor_id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String specialty = rs.getString("specialty");
				String contact = rs.getString("contact");
				List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctor_id);
				List<Patient> patients= conMan.getPatientMan().getPatients(doctor_id);
				List<Visit> visits = conMan.getVisitMan().showVisitBy(doctor_id);
				Doctor newDoctor = new Doctor(doctor_id, name, surname, specialty, contact,patients, hospitals, visits);
				doctors.add(newDoctor);
			}
			search.close();
			rs.close();
			return doctors;
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		return doctors;
	}

	@Override
	public void changeDoctor(Doctor a) {
		String template = "UPDATE authors SET name = ?, surname = ?,specialty = ?,contact = ? WHERE id = ?";
		PreparedStatement search;
		try {
			search = c.prepareStatement(template);
			search.setInt(5,   a.getDoctor_id());
		    search.setString(1, a.getName());
		    search.setString(2, a.getSurname());
		    search.setString(3, a.getSpecialty());
		    search.setString(4, a.getContact());
		    search.executeUpdate();
			search.close();
			search.close();
		} catch (SQLException e) {
			System.out.println("Error looking for a doctor");
			e.printStackTrace();
		}
		
	}

	@Override
	public Doctor getDoctor(int id) {
		try {
			String sql = "SELECT * FROM doctors WHERE doctor_id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(id);
			List<Patient> patients= conMan.getPatientMan().getPatients(id);
			List<Visit> visits = conMan.getVisitMan().showVisitBy(id);
			Doctor a = new Doctor (rs.getInt("doctor_id"), rs.getString("name"), rs.getString("surname"),rs.getString("specialty"),rs.getString("contact"),patients,hospitals,visits);
			return a;} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
		return null;
	
	}

	@Override
	public List<Doctor> getDoctors(int patientId){
	    List<Doctor> doctors = new ArrayList<>();
	    try {
	        String sql = "SELECT doctors.* FROM doctors " +
	                     "INNER JOIN Patient_Doctor ON doctors.doctor_id = Patient_Doctor.doctor_id " +
	                     "WHERE Patient_Doctor.patient_id = ?";
	        PreparedStatement statement = c.prepareStatement(sql);
	        statement.setInt(1, patientId);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int doctorId = resultSet.getInt("doctor_id");
	            String doctorName = resultSet.getString("name");
	            String doctorSurname = resultSet.getString("surname");
	            String specialization = resultSet.getString("specialty");
	            String contact = resultSet.getString("contact");
	            List<Hospital> hospitals = conMan.getHospitalMan().getHospitalByDoctor(doctorId);
				List<Patient> patients= conMan.getPatientMan().getPatients(doctorId);
				List<Visit> visits = conMan.getVisitMan().showVisitBy(doctorId);
	            Doctor doctor = new Doctor(doctorId, doctorName, doctorSurname, specialization, contact,patients,hospitals, visits);
	            doctors.add(doctor);
	        }
	        
	        resultSet.close();
	        statement.close();
	    } catch (SQLException e) {
	        System.err.println("Error retrieving doctors for patient: " + e.getMessage());
	    }
	    return doctors;
	}

	public ConnectionManager getConMan() {
		return conMan;
	}

	public void setConMan(ConnectionManager conMan) {
		this.conMan = conMan;
	}

}
