package medicalhistory.database.pojos;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;
import java.time.LocalDate;

public class Visit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4922111399657589755L;
	private Integer visit_id; 
	private Date visit_date;
	private String visit_observation;
	private String duration_medication;
	private Hospital hospital;
	private Patient visit_patient;
	private Doctor visit_doctor;
	private Test visit_test;
	private List <Medication> medications;
	private List <Treatment> treatments;
	
	public Visit(Integer visit_id, Date visit_date, String visit_observation, String duration_medication, Hospital hospital, Patient visit_patient, Doctor visit_doctor, Test visit_test,
			Treatment visit_treatment, List<Medication> medications, List<Treatment> treatments) {
		super();
		this.visit_id = visit_id;
		this.visit_date = visit_date;
		this.visit_observation = visit_observation;
		this.duration_medication = duration_medication;
		this.hospital = hospital;
		this.visit_patient = visit_patient;
		this.visit_doctor = visit_doctor;
		this.visit_test = visit_test;
		this.visit_treatment = visit_treatment;
		this.medications = medications;
		this.treatments = treatments;
	}
	public Visit() {
		super();
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(visit_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visit other = (Visit) obj;
		return Objects.equals(visit_id, other.visit_id);
	}
	public Integer getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Integer visit_id) {
		this.visit_id = visit_id;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public String getVisit_observation() {
		return visit_observation;
	}
	public void setVisit_observation(String visit_observation) {
		this.visit_observation = visit_observation;
	}
	public String getDuration_medication() {
		return duration_medication;
	}
	public void setDuration_medication(String duration_medication) {
		this.duration_medication = duration_medication;
	}

	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Patient getVisit_patient() {
		return visit_patient;
	}
	public void setVisit_patient(Patient visit_patient) {
		this.visit_patient = visit_patient;
	}
	public Doctor getVisit_doctor() {
		return visit_doctor;
	}
	public void setVisit_doctor(Doctor visit_doctor) {
		this.visit_doctor = visit_doctor;
	}
	public Test getVisit_test() {
		return visit_test;
	}
	public void setVisit_test(Test visit_test) {
		this.visit_test = visit_test;
	}
	public Treatment getVisit_treatment() {
		return visit_treatment;
	}
	public void setVisit_treatment(Treatment visit_treatment) {
		this.visit_treatment = visit_treatment;
	}
	public List<Medication> getMedications() {
		return medications;
	}
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}
	public List<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Visit [visit_id=" + visit_id + ", visit_date=" + visit_date + ", visit_observation=" + visit_observation
				+ ", duration_medication=" + duration_medication + ", hospital=" + hospital + ", visit_patient=" + visit_patient 
				+ ", visit_doctor=" + visit_doctor + ", visit_test=" + visit_test + ", visit_treatment=" + visit_treatment
				+ ", medications=" + medications + ", treatments=" + treatments + "]";
	}
	
	
	

}
