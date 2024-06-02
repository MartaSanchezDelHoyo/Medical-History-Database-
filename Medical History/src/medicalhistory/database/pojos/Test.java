package medicalhistory.database.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Test implements Serializable{
	/**
     * @param test_id: identification of a test
     * @param type: type of test
     * @param archivoPDF: the pdf with the information required
     * @param visits: list of visits that took to do that test
     */
	private static final long serialVersionUID = 888971237847553913L;
	private int test_id;
	private String type;
	private byte[] archivoPDF;
	private List <Visit> visits;
	
	
	/**Constructor without the visits and the id as a parameter
     * @param type is the name of the test
     * @param archivoPDF is the pdf with the information required
     */
	public Test( String type, byte[] archivoPDF) {
		this.setArchivoPDF(archivoPDF);
		this.type = type;
		this.visits= new ArrayList<>();
	}
	
	/**Constructor without the visits as a parameter
     * @param test_id is the identification of a test
     * @param type
     * @param archivoPDF
     */
	public Test(int test_id, String type, byte[] archivoPDF) {
		this.setArchivoPDF(archivoPDF);
		this.test_id = test_id;
		this.type = type;
		this.visits= new ArrayList<>();
	}
	
	/**Constructor with every attribute
     * @param test_id 
     * @param type
     * @param archivoPDF
     * @param visits is the list of visits that took to do that test
     */
	public Test(int test_id, String type, byte[] archivoPDF, List <Visit> visits) {
		this.setArchivoPDF(archivoPDF);
		this.test_id = test_id;
		this.type = type;
		this.visits= visits;
	}
	
	/** Equals method to compare two tests
	 * @param Object 
	 * @return boolean
	 */
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return Arrays.equals(archivoPDF, other.archivoPDF) && test_id == other.test_id
				&& Objects.equals(type, other.type) && Objects.equals(visits, other.visits);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(archivoPDF);
		result = prime * result + Objects.hash(test_id, type, visits);
		return result;
	}

	/**
     * Getter and setters of the attributes
     */

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
	public List<Visit> getVisits() {
		return visits;
	}
	public void setVisits(List<Visit> visits) {
		this.visits = visits;
	}
	public byte[] getArchivoPDF() {
		return archivoPDF;
	}
	public void setArchivoPDF(byte[] archivoPDF) {
		this.archivoPDF = archivoPDF;
	}
	

	@Override
	public String toString() {
		return "Test [test_id=" + test_id + ", type=" + type + "]";
	
}
}