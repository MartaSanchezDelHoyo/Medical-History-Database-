package userinterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
	// private static UserManager userMan;
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	
	private static void menuLogin() throws NumberFormatException, IOException {
		System.out.print("Username:");
		String username = r.readLine();
		System.out.print("Password:");
		String password = r.readLine();
		//User u = userMan.login(username, password);
		
		// TODO Redirect the user to the proper menu
	}
	
	private static void menuRegister() throws NumberFormatException, IOException {
		System.out.print("Choose a username:");
		String username = r.readLine();
		System.out.print("Choose a password:");
		String password = r.readLine();
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
	            Date dateOfBirth = null;
	            while (dateOfBirth == null) {
	                try {
	                    String input = reader.readLine();
	                  dateOfBirth = dateFormat.parse(input);
	                } catch (ParseException e) {
	                    System.err.println("Invalid date format. Please enter the date in the format yyyy-MM-dd:");
	                }
	            }
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


}
