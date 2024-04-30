package medicalhistory.database.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import medicalhistory.database.interfaces.*;


public class ConnectionManager {

	private Connection c;
	private DoctorManager docMan;
	private PatientManager patientMan;
	private HospitalManager hospitalMan;
	private TestManager testMan;
	private VisitManager visitMan;
	// Design pattern Singleton

	public Connection getConnection() {
		return c;
	}
	
	public ConnectionManager() {
		this.connect();
		this.docMan=new JDBCDocManager(this);
		this.patientMan = new JDBCPatientManager(this);
		this.hospitalMan = new JDBCHospitalManager(this);
		this.visitMan= new JDBCVisitManager(this);
		this.testMan =new JDBCTestManager(this);
		this.createTables();
	}
	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/library.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (ClassNotFoundException cnfE) {
			System.out.println("Databases libraries not loaded");
			cnfE.printStackTrace();
		} catch (SQLException sqlE) {
			System.out.println("Error with database");
			sqlE.printStackTrace();
		}
	}
	
	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Error closing the database");
			e.printStackTrace();
		}
	}
	private void createTables() {
		try {
			// Create the tables
			Statement createTables1 = c.createStatement();
			String create1 = "CREATE TABLE doctors ("
					+ "doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL,"
					+ "speciality TEXT NOT NULL,"
					+ "contact TEXT,"
					+ ")";
			createTables1.executeUpdate(create1);
			createTables1.close();
			Statement createTables2 = c.createStatement();
			String create2 = "CREATE TABLE patients ( "
					+ " patient_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " name TEXT NOT NULL,"
					+ " date_of_birth DATE NOT NULL,"
					+ " contact NOT NULL,"
					+ " blood_type TEXT NOT NULL,"
					+ " allergy_id INTEGER,"
					+ " FORGEIN_KEY (allergy_id)references allergies(allergy_id)"
					+ ")";
			createTables2.executeUpdate(create2);
			createTables2.close();
			Statement createTables3 = c.createStatement();
			String create3 = "CREATE TABLE Visits ( "
					+ " visit_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " date DATE NOT NULL,"
					+ " observations TEXT,"
					+ " duration_medication TEXT,"
					+ " patient_id INTEGER NOT NULL,"
					+ " doctor_id INTEGER NOT NULL ,"
					+ " test_id INTEGER NOT NULL,"
					+ " hospital_id INTEGER NOT NULL,"
					+ " JOIN patients AS p ON v.patient_id=p.patient_id INTEGER NOT NULL;"
					+ " FORGEIN_KEY (patient_id) references patients (patient_id),"
					+ " FORGEIN_KEY (doctor_id) references doctors(doctor_id),"
					+ " FORGEIN_KEY (test_id) references tests (test_id),"
					+ " FORGEIN_KEY (hospital_id) references hospitals (hospital_id))";
			createTables3.executeUpdate(create3);
			createTables3.close();
			Statement createTables4 = c.createStatement();
			String create4 = "CREATE TABLE hospitals ( "
					+ " hospital_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " hospital_name TEXT NOT NULL,"
					+ " hospital_adress TEXT NOT NULL)";
			createTables4.executeUpdate(create4);
			createTables4.close();
			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE tests ( "
					+ " test_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " test_type TEXT NOT NULL"
					+ " )";
			createTables5.executeUpdate(create5);
			createTables5.close();
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE treatments ( "
					+ " treatment_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " treatment_type TEXT NOT NULL"
					+ " )";
			createTables6.executeUpdate(create6);
			createTables6.close();
			Statement createTables7 = c.createStatement();
			String create7 = "CREATE TABLE medications ( "
					+ " medication_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " medication_type TEXT NOT NULL"
					+ " )";
			createTables7.executeUpdate(create7);
			createTables7.close();
			Statement createTables8 = c.createStatement();
			String create8 = "CREATE TABLE allergies ( "
					+ " allergy_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " allergy_type TEXT NOT NULL"
					+ " )";
			createTables8.executeUpdate(create8);
			createTables8.close();
			Statement createTables9 = c.createStatement();
			String create9 = "CREATE TABLE manufacturers ( "
					+ " manufacturer_id INTEGER PRIMARY_KEY AUTOINCREMENT,"
					+ " manufacturer_name TEXT NOT NULL"
					+ " )";
			createTables9.executeUpdate(create9);
			createTables9.close();
			Statement createTables10 = c.createStatement();
			String create10 = "CREATE TABLE manufacturer-medication ( "
					+ " medication_id INTEGER PRIMARY_KEY ,"
					+ " manufacturer_id INTEGER PRIMARY_KEY,"
					+ " FOREIGN_KEY (medication_id ) references medications(medication_id),"
					+ " FOREIGN_KEY (manufacturer_id) references manufacturers (manufacturer_id)"
					+ " )";
			createTables10.executeUpdate(create10);
			createTables10.close();
			Statement createTables11 = c.createStatement();
			String create11 = "CREATE TABLE visit-medication ( "
					+ " medication_id INTEGER PRIMARY_KEY ,"
					+ " visit_id INTEGER PRIMARY_KEY,"
					+ " FOREIGN_KEY (medication_id ) references medications(medication_id),"
					+ " FOREIGN_KEY (visit_id) references visits (visit_id)"
					+ " )";
			createTables11.executeUpdate(create11);
			createTables11.close();
			Statement createTables12 = c.createStatement();
			String create12 = "CREATE TABLE visit-treatment ( "
					+ " visit_id INTEGER PRIMARY_KEY ,"
					+ " treatment_id INTEGER PRIMARY_KEY,"
					+ " FOREIGN_KEY (visit_id ) references visits(visit_id),"
					+ " FOREIGN_KEY (treatment_id) references treatments (treatment_id)"
					+ " )";
			createTables12.executeUpdate(create12);
			createTables12.close();
			Statement createTables13 = c.createStatement();
			String create13 = "CREATE TABLE hospital-doctor ( "
					+ " hospital_id INTEGER PRIMARY_KEY ,"
					+ " doctor_id INTEGER PRIMARY_KEY,"
					+ " FOREIGN_KEY (hospital_id ) references hospitals(hospital_id),"
					+ " FOREIGN_KEY (doctor_id) references doctors (doctor_id)"
					+ " )";
			createTables13.executeUpdate(create13);
			createTables13.close();
		} catch (SQLException sqlE) {
			if (sqlE.getMessage().contains("already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}

	public DoctorManager getDocMan() {
		return docMan;
	}

	public void setDocMan(DoctorManager docMan) {
		this.docMan = docMan;
	}

	public PatientManager getPatientMan() {
		return patientMan;
	}

	public void setPatientMan(PatientManager patientMan) {
		this.patientMan = patientMan;
	}

	public HospitalManager getHospitalMan() {
		return hospitalMan;
	}

	public void setHospitalMan(HospitalManager hospitalMan) {
		this.hospitalMan = hospitalMan;
	}

	public TestManager getTestMan() {
		return testMan;
	}

	public void setTestMan(TestManager testMan) {
		this.testMan = testMan;
	}

	public VisitManager getVisitMan() {
		return visitMan;
	}

	public void setVisitMan(VisitManager visitMan) {
		this.visitMan = visitMan;
	}
}