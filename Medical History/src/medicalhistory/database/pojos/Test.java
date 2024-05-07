package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Test implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 888971237847553913L;
	private int test_id;
	private String type;
	private List <Visit> visits;
	
	public Test(int test_id, String type) {
		this.test_id = test_id;
		this.type = type;
		this.visits= new ArrayList<>();
	}
	
	
	
	@Override
	public String toString() {
		return "Test [test_id=" + test_id + ", type=" + type + "]";
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List <Visit> getVisits() {
		return visits;
	}
	public void setVisits(List <Visit> visits) {
		this.visits = visits;
	}
}
