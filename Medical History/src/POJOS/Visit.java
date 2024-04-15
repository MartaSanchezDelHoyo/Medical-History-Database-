package POJOS;
import java.time.LocalDate;

public class Visit {
	private int visit_id; 
	private LocalDate visit_date;
	private String visit_observation;
	private String duration_medication;
	private int patien_id;
	private int doctor_id;
	private int test_id;
	private int hospital_id;
	
	
	public int getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(int visit_id) {
		this.visit_id = visit_id;
	}
	public LocalDate getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(LocalDate visit_date) {
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
	public int getPatien_id() {
		return patien_id;
	}
	public void setPatien_id(int patien_id) {
		this.patien_id = patien_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public int getHospital_id() {
		return hospital_id;
	}
	public void setHospital_id(int hospital_id) {
		this.hospital_id = hospital_id;
	}
	@Override
	public String toString() {
		return "Visits [visit_id=" + visit_id + ", visit_date=" + visit_date + ", visit_observation="
				+ visit_observation + ", duration_medication=" + duration_medication + ", patien_id=" + patien_id
				+ ", doctor_id=" + doctor_id + ", test_id=" + test_id + ", hospital_id=" + hospital_id + "]";
	}
	public Visit(int visit_id, LocalDate visit_date, String visit_observation, String duration_medication,
			int patien_id, int doctor_id, int test_id, int hospital_id) {
		super();
		this.visit_id = visit_id;
		this.visit_date = visit_date;
		this.visit_observation = visit_observation;
		this.duration_medication = duration_medication;
		this.patien_id = patien_id;
		this.doctor_id = doctor_id;
		this.test_id = test_id;
		this.hospital_id = hospital_id;
	}
	

}
