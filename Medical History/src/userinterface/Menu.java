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
	


public class Menu {
	private static ConnectionManager conMan;
	private static PatientManager patientMan;
	private static DoctorManager doctorMan;
	private static HospitalManager hospitalMan;
	private static TestManager testMan;
	private static VisitManager visitMan;
	private static MedicationManager medicationMan;
	private static TreatmentManager treatmentMan;
	private static AllergiesManager allergiesMan;
	// private static UserMan userMan;
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static Scanner scanner = new Scanner(System.in);
	
	 public static void main(String[] Args) throws IOException {
		 conMan= new ConnectionManager();
		 patientMan = conMan.getPatientMan();
		 doctorMan = conMan.getDocMan();
		 hospitalMan = conMan.getHospitalMan();
		 testMan= conMan.getTestMan();
		 visitMan= conMan.getVisitMan();
		 medicationMan= conMan.getMedicationMan();
		 treatmentMan= conMan.getTreatmentMan();
		 allergiesMan=conMan.getAllergiesMan();
		 
		 LocalDate date= LocalDate.parse("24-05-2004", formatter);
         Date dateOfBirth = Date.valueOf(date);
		
<<<<<<< HEAD
		 //Patient acambiar= new Patient( 15,"Pablo", dateOfBirth, "b+", "nico@gm.com", null);
		//Doctor doc= new Doctor("Ariana", "Grande", "Radiology", "ary@gmail.com",null);
		 //Test test =new Test("Radiografía", null);
		 //Hospital hos= new Hospital(1,"Vicceroy", "Calle Maria Maria, 30");
         //Visit visit= new Visit(dateOfBirth,"AYAYA", acambiar, doc, test, hos);
=======
		 Patient acambiar= new Patient( 15,"Pablo", dateOfBirth, "b+", "nico@gm.com", null, "jose");
		 Doctor doc= new Doctor("Ariana", "Grande", "Radiology", "ary@gmail.com",null, "pablo");
		 Test test =new Test("Radiografía", null);
		 Hospital hos= new Hospital(1,"Vicceroy", "Calle Maria Maria, 30", "luis");
         Visit visit= new Visit(dateOfBirth,"AYAYA", acambiar, doc, test, hos);
>>>>>>> branch 'master' of https://github.com/MartaSanchezDelHoyo/Medical-History-Database-.git
         Medication med= new Medication("Vivaporux");
         Manufacturer manu= new Manufacturer("Pfizer");
         Treatment treat= new Treatment("Radiografía");
         Allergies allergy= new Allergies("Meat");
<<<<<<< HEAD
                  
         //conMan.getVisitMan().addVisit(visit);
         
         //Alergies aller= conMan.getAllergiesMan()
         
         //Doctor newDoc= conMan.getDocMan().getDoctor(1);
        // System.out.println(newDoc);
=======
         
         Allergies allergy2 = conMan.getAllergiesMan().getAllergy(4);
         System.out.println(allergy2);
      
        
>>>>>>> branch 'master' of https://github.com/MartaSanchezDelHoyo/Medical-History-Database-.git
        //Manufacturer has an error in db
        //Medication has an error in db
		//Test has an error in db
        // conMan.getTestMan().addTest(test);
		//Patient verifyed (Without lists)
        //Hospital verifyed (Without lists)
		 //Doctor verifyed (Without lists)
		 
		 
		
	 }
	 

}