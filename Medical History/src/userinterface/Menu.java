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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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
		private static XMLManager xmlMan;
		
		private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		private static Scanner scanner = new Scanner(System.in);
		
		 public static void main(String[] Args) throws IOException, JAXBException {
			 conMan= new ConnectionManager();
			 patientMan = conMan.getPatientMan();
			 doctorMan = conMan.getDocMan();
			 hospitalMan = conMan.getHospitalMan();
			 testMan= conMan.getTestMan();
			 visitMan= conMan.getVisitMan();
			 medicationMan= conMan.getMedicationMan();
			 treatmentMan= conMan.getTreatmentMan();
			 allergiesMan=conMan.getAllergiesMan();
			 xmlMan=conMan.getXMLMan();
			 
			 
			 LocalDate date= LocalDate.parse("05-05-2024", formatter);
	         Date dateOfBirth = Date.valueOf(date);
	         Patient acambiar= new Patient( 15,"Pablo", dateOfBirth, "b+", "nico@gm.com", null);
	         
	         File xmlfile = conMan.getXMLMan().patient2Xml(acambiar);
	         
	         Patient patient1 =  conMan.getXMLMan().xml2Patient(xmlfile);
	         System.out.println(patient1);
			 
		 }
			/* LocalDate date= LocalDate.parse("05-05-2024", formatter);
	         Date dateOfBirth = Date.valueOf(date);
	         Visit acambiar= conMan.getVisitMan().getVisit(5);
	         System.out.println(acambiar);
	         
	
	         acambiar.setVisit_date(dateOfBirth);
	         conMan.getVisitMan().changeVisit(acambiar);
	         // acambiar.setVisit_date(dateOfBirth);
	         //conMan.getVisitMan().changeVisit(acambiar);
	         System.out.println(dateOfBirth);
	         System.out.println(acambiar);
	         
	         Doctor a2= conMan.getDocMan().getDoctorCI(3);
	         Doctor a3= conMan.getDocMan().getDoctor(3);
	         System.out.println(a2);
	         System.out.println(a3);
	        		 
			 //Patient acambiar= new Patient( 15,"Pablo", dateOfBirth, "b+", "nico@gm.com", null);
			 //Doctor doc= new Doctor("Ariana", "Grande", "Radiology", "ary@gmail.com",null);
			 //Test test =new Test("Radiografía", null);
			 //Hospital hos= new Hospital(1,"Vicceroy", "Calle Maria Maria, 30");
	         //Visit visit= new Visit(dateOfBirth,"AYAYA", acambiar, doc, test, hos);
	
			
			 Patient acambiar= new Patient( 15,"Pablo", dateOfBirth, "b+", "nico@gm.com", null, "jose");
			 //Doctor doc= new Doctor("Ariana", "Grande", "Radiology", "ary@gmail.com",null, "pablo");
			 Test test =new Test("Radiografía", null);
			 Hospital hos= new Hospital(1,"Vicceroy", "Calle Maria Maria, 30", "luis");
	         //Visit visit= new Visit(dateOfBirth,"AYAYA", acambiar, doc, test, hos);
	        
	         Medication med= new Medication("Vivaporux");
	         Manufacturer manu= new Manufacturer("Pfizer");
	         Treatment treat= new Treatment("Radiografía");
	         Allergies allergy= new Allergies("Meat");
	
	         
	         List<Medication> medUno= conMan.getMedicationMan().showMedicationsByManufacturer(1);
	         //System.out.println(medUno);
	         
	         for (Medication persona : medUno) {
	             System.out.println(persona);
	         }
			
	         
	         
	         //Patient pati= conMan.getPatientMan().getPatient(1);
	         //Patient getPatientByName= conMan.getPatientMan().getPatient(1);
	         //getPatientByName.setDateofbirth(dateOfBirth);
	         //conMan.getPatientMan().changePatient(getPatientByName);
	
	           
	         
	         //Alergies aller= conMan.getAllergiesMan()
	         
	         //Doctor newDoc= conMan.getDocMan().getDoctor(1);
	        //System.out.println(getPatientByName);
	
	        //Medication has an error in db
			//Test has an error in db
	        // conMan.getTestMan().addTest(test);
			//Patient verifyed (Without lists)
	        //Hospital verifyed (Without lists)
			 //Doctor verifyed (Without lists)
			 
			 
			
		 }
		 */
		 
	
	}