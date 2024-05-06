package userinterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import medicalhistory.database.interfaces.*;
import medicalhistory.database.jdbc.*;
import medicalhistory.database.pojos.*;



public class Menu {
	private static ConnectionManager conMan;
	private static PatientManager patientMan;
	private static DoctorManager doctorMan;
	private static HospitalManager hospitalMan;
	private static TestManager testMan;
	private static VisitManager visitMan;
	private static MedicationManager medicationMan;
	private static TreatmentManager treatmentMan;
	// private static UserManager userMan;
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 private Scanner scanner;
	
	private static void menuLogin() throws NumberFormatException, IOException {
		System.out.print("Username:");
		String username = reader.readLine();
		System.out.print("Password:");
		String password = reader.readLine();
		//User u = userMan.login(username, password);
		// TODO Redirect the user to the proper menu
	}
	
	private static void menuRegister() throws NumberFormatException, IOException {
		System.out.print("Choose a username:");
		String username = reader.readLine();
		System.out.print("Choose a password:");
		String password = reader.readLine();
		//User u = new User(username, password, r);
		//userMan.register(u);
	}
	
	 public void addPatient() {
	        try {
	            System.out.println("Enter patient name:");
	            String name = reader.readLine();
	            System.out.println("Enter patient sex:");
	            String sex = reader.readLine();
	            System.out.println("Enter patient birth date (yyyy-MM-dd):");
	            String dateStr = scanner.nextLine();
	            Date dateOfBirth = Date.valueOf(dateStr);
	            System.out.println("Enter patient bloodtype:");
	            String bloodtype = reader.readLine();
	            System.out.println("Enter patient email:");
	            String email = reader.readLine();

	            Patient patient = new Patient(name, sex, dateOfBirth, bloodtype, email);
	            patientMan.addPatient(patient);
	        } catch (IOException e) {
	            System.err.println("Error reading input: " + e.getMessage());
	        }
	    }

		public void getPatientsByName() {
			System.out.println("Enter patient name:");
			String name = scanner.nextLine();
			List<Patient> patients = patientManager.getPatientByName(name);
			for (Patient patient : patients) {
				System.out.println(patient);
			}
		}

		public void getPatient() {
			System.out.println("Enter patient ID:");
			int id = scanner.nextInt();
			Patient patient = patientManager.getPatient(id);
			System.out.println(patient);
		}

		public void changePatient() {
			System.out.println("Enter patient ID:");
			int id = scanner.nextInt();  
			Patient newpatient = patientManager.getPatient(id); 

			System.out.println("Enter patient name:");
			String name = scanner.nextLine();
			newpatient.setName(name);

			System.out.println("Enter patient sex: ");
			String sex = scanner.nextLine();
			newpatient.setSex(sex);

			System.out.println("Enter patient birth date (yyyy-MM-dd):");
			String dateStr = scanner.nextLine();
			Date dateOfBirth = Date.valueOf(dateStr);
			newpatient.setDateOfBirth(dateOfBirth);

			System.out.println("Enter patient bloodtype:");
			String bloodtype = scanner.nextLine();
			newpatient.setBloodtype(bloodtype);

			System.out.println("Enter patient email:");
			String email = scanner.nextLine();
			newpatient.setEmail(email);

			patientManager.changePatient(newpatient);
		}

		public void getPatients() {
			System.out.println("Enter the doctor ID:");
			int id = scanner.nextInt();
			List <Patient> patients = patientManager.getPatients(id);
			System.out.println(patients);
		}

	    public void addDoctor() {
	        try {
	           
	            System.out.println("Enter doctor name:");
	            String name = scanner.nextLine();

	            System.out.println("Enter doctor surname:");
	            String surname = scanner.nextLine();

	            System.out.println("Enter doctor specialty:");
	            String specialty = scanner.nextLine();

	            System.out.println("Enter doctor contact:");
	            String contact = scanner.nextLine();

	            // no hay constructor para esto
	            //Doctor doctor = new Doctor(name, surname, specialty, contact);

	            // comentado para que no haya errores
	            //doctorMan.addDoctor(doctor);
	        } catch (Exception e) {
	            System.err.println("Error adding doctor: " + e.getMessage());
	        }
	    }

	public void getDoctor() {
		System.out.println("Enter doctor ID:");
		int id = scanner.nextInt();
		Doctor doctor = doctorManager.getDoctor(id);
		System.out.println(doctor);
	}

	public void getDoctors() {
		System.out.println("Enter patient ID:");
		int id = scanner.nextInt();
		List <Doctor> doctors = doctorManager.getDoctors(id);
		System.out.println(doctors);
	}

	public void changeDoctor() {
		System.out.println("Enter doctor ID:");
		int id = scanner.nextInt();
		Doctor newDoctor = doctorManager.getDoctor(id);

		System.out.println("Enter doctor name:");
		String newname = scanner.nextLine();
		newdoctor.setName(newname);

		System.out.println("Enter doctor surname:");
		String surname = scanner.nextLine();
		newdoctor.setSurname(surname);

		System.out.println("Enter doctor specialty:");
		String specialty = scanner.nextLine();
		newdoctor.setSpecialty(specialty);

		System.out.println("Enter doctor contact:");
		String contact = scanner.nextLine();
		newdoctor.setContact(contact);

		doctorManager.changeDoctor(newDoctor);
	}

	public void getDoctorsbyHospital () {
		System.out.println("Enter hospital name:");
		String name = scanner.nextLine();
		List <Doctor> doctors = doctorManager.getDoctorsbyHospital(name);
		System.out.println(doctors);
	}

	public void getDoctorsbySpecialties () {
		System.out.println("Enter doctor specialty:");
		String specialty = scanner.nextLine();
		List <Doctor> doctors = doctorManager.getDoctorsbySpecialties(specialty);
		System.out.println(doctors);
	}

	public void getDoctorByNameSurname() {
		System.out.println("Enter doctor name:");
		String name = scanner.nextLine();
		System.out.println("Enter doctor surname:");
		String surname = scanner.nextLine();
		Doctor doctor = doctorManager.getDoctorByNameSurname(name, surname);
		System.out.println(doctor);
	}

	public void addHospital() {
	try {
		// Get hospital details from the user
		System.out.println("Enter hospital name:");
		String name = scanner.nextLine();

		System.out.println("Enter hospital address:");
		String address = scanner.nextLine();

		//  no hay constructor para esto
		//Hospital hospital = new Hospital(name, address);

		// comentado para que no haya errores
		//hospitalMan.addHospital(hospital);
	} catch (Exception e) {
		System.err.println("Error adding hospital: " + e.getMessage());
	}
	}

	public void getHospital() {
		System.out.println("Enter hospital ID:");
		int id = scanner.nextInt();
		Hospital hospital = hospitalManager.showHospital(id);
		System.out.println(hospital);
	}


	public void addMedication() {
		try {
			System.out.println("Enter medication ID:");
			int medicationId = scanner.nextInt();
			scanner.nextLine(); 
			
			System.out.println("Enter medication type:");
			String type = scanner.nextLine();

			
			Medication medication = new Medication(medicationId, type);

			medicationMan.addMedication(medication);
		} catch (Exception e) {
			System.err.println("Error adding medication: " + e.getMessage());
		}
	}

	public void addManufacturer() {
		try {
			System.out.println("Enter manufacturer ID:");
			int manufacturerId = scanner.nextInt();
			scanner.nextLine(); 

			System.out.println("Enter manufacturer name:");
			String name = scanner.nextLine();

			Manufacturer manufacturer = new Manufacturer(manufacturerId, name);
			medicationManager.addManufacturer(manufacturer);

			System.out.println("Manufacturer added successfully.");
		} catch (Exception e) {
			System.err.println("Error adding manufacturer: " + e.getMessage());
		}
	}

	public void modifyMedication() {
		try {
			System.out.println("Enter medication ID:");
			int medicationId = scanner.nextInt();
			scanner.nextLine(); 

			System.out.println("Enter new medication type:");
			String type = scanner.nextLine();

			Medication medication = new Medication(medicationId, type);

			medicationMan.modifyMedication(medication);
		} catch (Exception e) {
			System.err.println("Error modifying medication: " + e.getMessage());
		}
	}
	public void getMedicationsByManufacturer() {
		try {
			System.out.println("Enter manufacturer ID:");
			int manufacturerId = scanner.nextInt();
			scanner.nextLine(); 

			List<Medication> medications = medicationMan.showMedicationsByManufacturer(manufacturerId);
			System.out.println(medications);
		} catch (Exception e) {
			System.err.println("Error showing medications by manufacturer: " + e.getMessage());
		}
	}

	public void getManufacturerWithMedications() {
		try {
			System.out.println("Enter manufacturer ID:");
			int manufacturerId = scanner.nextInt();
			scanner.nextLine(); 

			List<Manufacturer> manufacturers = medicationMan.showManufacturerWithMedications(manufacturerId);
			System.out.println(manufacturers);
		} catch (Exception e) {
			System.err.println("Error showing manufacturer with medications: " + e.getMessage());
		}
	}

	public void getManufacturers () {
		try {
			System.out.println("Enter medication ID:");
			int medicationId = scanner.nextInt();
			scanner.nextLine(); 

			List<Manufacturer> manufacturers = medicationMan.showManufacturers(medicationId);
			System.out.println(manufacturers);
		} catch (Exception e) {
			System.err.println("Error showing manufacturers: " + e.getMessage());
		}
	}

	public void addTest() {
		try {
			System.out.println("Enter test ID:");
			int testId = scanner.nextInt();
			scanner.nextLine(); 
			
			System.out.println("Enter test type:");
			String type = scanner.nextLine();

			Test test = new Test(testId, type);

			testMan.addTest(test);
		} catch (Exception e) {
			System.err.println("Error adding test: " + e.getMessage());
		}
	}

	public void getTest() {
		System.out.println("Enter test ID:");
		int id = scanner.nextInt();
		Test test = testManager.showTest(id);
		System.out.println(test);
	}

	public void getTestsByPatient() {
		System.out.println("Enter patient id:");
		int id = scanner.nextInt();
		List<Test> tests = testManager.getTestsbyPatient(id);
		System.out.println(tests);
	}

	public void addTreatment() {
		try {
			System.out.println("Enter treatment ID:");
			int treatmentId = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Enter treatment type:");
			String type = scanner.nextLine();

			Treatment treatment = new Treatment(treatmentId, type);

			treatmentMan.addTreatment(treatment);
		} catch (Exception e) {
			System.err.println("Error adding treatment: " + e.getMessage());
		}
	}

	public void addVisit() {
		try {
			System.out.println("Enter visit ID:");
			int visitId = scanner.nextInt();
			scanner.nextLine(); 

			System.out.println("Enter visit date (YYYY-MM-DD):");
			String dateStr = scanner.nextLine();
			Date visitDate = Date.valueOf(dateStr);

			System.out.println("Enter visit observations:");
			String observations = scanner.nextLine();

			System.out.println("Enter duration of medication:");
			String durationMedication = scanner.nextLine();

			System.out.println("Enter patient ID:");
			int patientId = scanner.nextInt();

			System.out.println("Enter doctor ID:");
			int doctorId = scanner.nextInt();

			System.out.println("Enter test ID:");
			int testId = scanner.nextInt();

			System.out.println("Enter hospital ID:");
			int hospitalId = scanner.nextInt();
		
			//  no hay constructor para esto
			//Visit visit = new Visit(visitId, visitDate, observations, durationMedication, patientId, doctorId, testId, hospitalId);
			
			// comentado para que no haya errores
			//visitMan.addVisit(visit);
		} catch (Exception e) {
			System.err.println("Error adding visit: " + e.getMessage());
		}
	}

	public void getVisit() {
		System.out.println("Enter visit ID:");
		int id = scanner.nextInt();
		Visit visit = visitManager.showVisit(id);
		System.out.println(visit);
	}

	public void getVisitsByPatient() {
		System.out.println("Enter patient id:");
		int id = scanner.nextInt();
		List<Visit> visits = visitManager.getVisitsByPatient(id);
		System.out.println(visits);

	}

	public void getVisitsByDoctor() {
		System.out.println("Enter doctor id:");
		int id = scanner.nextInt();
		List<Visit> visits = visitManager.getVisitsByDoctor(id);
		System.out.println(visits);
	}

	public void getVisitsByPatientDoctor() {
		System.out.println("Enter patient id:");
		int patientId = scanner.nextInt();
		System.out.println("Enter doctor id:");
		int doctorId = scanner.nextInt();
		List<Visit> visits = visitManager.getVisitsByPatientDoctor(patientId, doctorId);
		System.out.println(visits);
	}

	public void getVisitsVisitByTreatment() {
		System.out.println("Enter treatment id:");
		int id = scanner.nextInt();
		List<Visit> visits = visitManager.getVisitsByTreatment(id);
		System.out.println(visits);
	}

}