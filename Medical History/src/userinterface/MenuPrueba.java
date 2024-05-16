package userinterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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



public class MenuPrueba {
	private static ConnectionManager conMan;
	private static PatientManager patientMan;
	private static DoctorManager doctorMan;
	private static HospitalManager hospitalMan;
	private static TestManager testMan;
	private static VisitManager visitMan;
	private static MedicationManager medicationMan;
	private static TreatmentManager treatmentMan;
	// private static UserMan userMan;
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static Scanner scanner = new Scanner(System.in);
	 
	public static void main(String[] Args) throws IOException {
		
		 conMan= new ConnectionManager();
		 
		 
    }
	
	 public static void addDoctor() {
	        try {
	           
	            System.out.println("Enter doctor name:");
	            String name = scanner.nextLine();

	            System.out.println("Enter doctor surname:");
	            String surname = scanner.nextLine();

	            System.out.println("Enter doctor specialty:");
	            String specialty = scanner.nextLine();

	            System.out.println("Enter doctor contact:");
	            String contact = scanner.nextLine();
	            
	            System.out.println("Enter image path :");
	            String imagePath = scanner.nextLine();
	            File imageFile = new File(imagePath);

	            byte[] image = new byte[(int) imageFile.length()];
	            FileInputStream fis = new FileInputStream(imageFile);
	            fis.read(image);
	            fis.close();

	            Doctor doctor = new Doctor(name, surname, specialty, contact,image);
	            doctorMan.addDoctor(doctor);
	            
	        } catch (Exception e) {
	            System.err.println("Error adding doctor: " + e.getMessage());
	        }
	    }

	 
		public static void changeDoctor() {
			System.out.println("Enter doctor ID:");
			int id = scanner.nextInt();
			Doctor newDoctor = doctorMan.getDoctor(id);

			System.out.println("Enter doctor name:");
			String newname = scanner.nextLine();
			newDoctor.setName(newname);

			System.out.println("Enter doctor surname:");
			String surname = scanner.nextLine();
			newDoctor.setSurname(surname);

			System.out.println("Enter doctor specialty:");
			String specialty = scanner.nextLine();
			newDoctor.setSpecialty(specialty);

			System.out.println("Enter doctor contact:");
			String contact = scanner.nextLine();
			newDoctor.setContact(contact);

			doctorMan.changeDoctor(newDoctor);
		}

		public void getDoctorsbyHospital () {
			System.out.println("Enter hospital name:");
			String name = scanner.nextLine();
			List <Doctor> doctors = doctorMan.getDoctorsbyHospital(name);
			System.out.println(doctors);
		}

		public void getDoctorsbySpecialties () {
			System.out.println("Enter doctor specialty:");
			String specialty = scanner.nextLine();
			List <Doctor> doctors = doctorMan.getDoctorsbySpecialties(specialty);
			System.out.println(doctors);
		}

		public void getDoctorByNameSurname() {
			System.out.println("Enter doctor name:");
			String name = scanner.nextLine();
			System.out.println("Enter doctor surname:");
			String surname = scanner.nextLine();
			List<Doctor> doctors = doctorMan.getDoctorByNameSurname(name, surname);
			System.out.println(doctors);
		}
		/*
		ConnectionManager connectionManager = new ConnectionManager();
		JDBCAllergiesManager allergiesManager = new JDBCAllergiesManager(connectionManager);
		Allergies allergies = new Allergies("Peanuts");
		allergiesManager.addAllergy(allergies);
		Allergies addedAllergy = allergiesManager.getAllergy(allergies.getAllergiesID());
		if (addedAllergy != null) {
		    System.out.println("Allergy added successfully!");
		} else {
		    System.out.println("Failed to add allergy!");
		}
		connectionManager.close();
		*/

}

