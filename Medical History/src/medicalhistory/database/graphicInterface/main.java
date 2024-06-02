package medicalhistory.database.graphicInterface;

import java.sql.Connection;


import javax.swing.*;
import medicalhistory.database.jdbc.*;
import medicalhistory.database.jpa.JPAUserManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Role;
import medicalhistory.database.pojos.User;
import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;

public class main extends JFrame {

private static ConnectionManager conMan;
private static DoctorManager docMan;
private static PatientManager patientMan;
private static HospitalManager hospitalMan;
private TestManager testMan;
private VisitManager visitMan;
private TreatmentManager treatmentMan;
private MedicationManager medicationMan;
private static JPAUserManager userMan;


	public static void main(String[] args) {
		
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	         
	            try {
	            	 new Registation();
	            } catch (Exception e) {
	                JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    });
	}
}