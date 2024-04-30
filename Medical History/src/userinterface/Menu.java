package userinterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
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
	
	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
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
	

}
