package medicalhistory.database.interfazGrafica;

import java.sql.Connection;

import javax.swing.*;

import medicalhistory.database.jdbc.ConnectionManager;
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
private Connection c;
private static DoctorManager docMan;
private static PatientManager patientMan;
private static HospitalManager hospitalMan;
private TestManager testMan;
private VisitManager visitMan;
private TreatmentManager treatmentMan;
private MedicationManager medicationMan;
private static JPAUserManager userman;


public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
           Registation registation = new Registation();
           try {
        	   User user = new User();
			if ( userman.login(registation.getCampoUsuario().toString(),registation.getCampoContraseña().toString()) != null ){
				user =userman.login(registation.getCampoUsuario().toString(),registation.getCampoContraseña().toString());
				JOptionPane.showInputDialog("Welcome back "+ registation.getCampoUsuario().toString());
				Role role=user.getRole();
				switch (role.toString()) {
			    case "Doctor":
			    	Doctor doc=docMan.getDoctor(user.getId());
			    	new DoctorInfo(doc);
			        break;
			    case "Patient":
			       Patient patient= patientMan.getPatient(user.getId());
			       new PatientInfo(patient);
			        break;
			    case "Hospital":
			       Hospital hospi= hospitalMan.getHospital(user.getId());
			       new HospitalInfo(hospi);
			        break;
			    default:
			        
			        break;
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
    });
}
 
}
