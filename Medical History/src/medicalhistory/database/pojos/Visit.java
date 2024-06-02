package medicalhistory.database.pojos;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

import java.sql.Date;
import java.util.Objects;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import medicalhistory.database.xml.utils.SQLDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Visit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4922111399657589755L;
	@XmlAttribute
	private Integer visit_id;
	@XmlAttribute
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date visit_date;
	@XmlAttribute
	private String visit_observation;
	@XmlTransient
	private Hospital hospital;
	@XmlTransient
	private Patient visit_patient;
	@XmlTransient
	private Doctor visit_doctor;
	@XmlTransient
	private Test visit_test;
	@XmlTransient
	private List <Medication> medications;
	@XmlTransient
	private List <Treatment> treatments;
	
	public Visit() {
		super();
	}
	
	public Visit(Date visitDate, String observations, Patient patient, Doctor doctor , Test test, Hospital hospital) {
		this.visit_date = visitDate;
		this.visit_observation = observations;
		this.hospital = hospital;
		this.visit_patient = patient;
		this.visit_doctor = doctor;
		this.visit_test=test;
	}
	
	public Visit(int visitId, Date visitDate, String observations, Patient patient, Doctor doctor, Test test, Hospital hospital) {
		this.visit_id = visitId;
		this.visit_date = visitDate;
		this.visit_observation = observations;
		this.hospital = hospital;
		this.visit_patient = patient;
		this.visit_doctor = doctor;
		this.visit_test=test;
	}
	
	public Visit(Integer visit_id, Date visit_date, String visit_observation, Hospital hospital, Patient visit_patient, Doctor visit_doctor, Test visit_test, List<Medication> medications) {
		this.visit_id = visit_id;
		this.visit_date = visit_date;
		this.visit_observation = visit_observation;
		this.hospital = hospital;
		this.visit_patient = visit_patient;
		this.visit_doctor = visit_doctor;
		this.visit_test=visit_test;
		this.medications = medications;
		
	}
	
	
	public Visit(Integer visit_id, Date visit_date, String visit_observation, Hospital hospital, Patient visit_patient, Doctor visit_doctor, Test visit_test,
			 List<Medication> medications, List<Treatment> treatments) {
		this.visit_id = visit_id;
		this.visit_date = visit_date;
		this.visit_observation = visit_observation;
		this.hospital = hospital;
		this.visit_patient = visit_patient;
		this.visit_doctor = visit_doctor;
		this.visit_test=visit_test; 
		this.medications = medications;
		this.treatments = treatments;
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
				+ ", hospital=" + hospital + ", visit_patient=" + visit_patient 
				+ ", visit_doctor=" + visit_doctor + ", visit_test=" + visit_test +
				", medications=" + medications + ", treatments=" + treatments + "]";
	}
	
	
	

}
