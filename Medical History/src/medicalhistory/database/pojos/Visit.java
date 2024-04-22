package medicalhistory.database.pojos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Visit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4922111399657589755L;
	private Integer visit_id; 
	private LocalDate visit_date;
	private String visit_observation;
	private String duration_medication;
	private Integer patien_id;
	private Integer doctor_id;
	private Integer test_id;
	private Integer hospital_id;
	
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

	@Override
	public int hashCode() {
		return Objects.hash(doctor_id, duration_medication, hospital_id, patien_id, test_id, visit_date, visit_id,
				visit_observation);
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
		return doctor_id == other.doctor_id && Objects.equals(duration_medication, other.duration_medication)
				&& hospital_id == other.hospital_id && patien_id == other.patien_id && test_id == other.test_id
				&& Objects.equals(visit_date, other.visit_date) && visit_id == other.visit_id
				&& Objects.equals(visit_observation, other.visit_observation);
	}
	

}