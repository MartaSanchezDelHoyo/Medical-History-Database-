package medicalhistory.database.graphicInterface;

import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.jdbc.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.pojos.User;
import medicalhistory.database.pojos.Visit;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VisitInfo extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel botonPanelPatients;
	private JLabel DoctorText;
	private JPanel panelBotonesMedication;
	private static ConnectionManager conMan;
	 private static MedicationManager medicationMan;
	 private static TreatmentManager treatmentMan;
	
	
public VisitInfo (Visit a,User u) {	
	conMan = new ConnectionManager();
	medicationMan=conMan.getMedicationMan();
	treatmentMan= conMan.getTreatmentMan();
	
	setTitle("Visit Information");
    setSize(1600, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla

    getContentPane().add(panel);
    panel.setLayout(null);
    panel.setLayout(null);

    JLabel lblVisitId = new JLabel("Visit ID:");
    lblVisitId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblVisitId.setBounds(156, 69, 103, 26);
    panel.add(lblVisitId);
   
    JLabel lblObserbations = new JLabel("Observations:");
    lblObserbations.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblObserbations.setBounds(22, 689, 171, 35);
    panel.add(lblObserbations);
    
    JLabel lblTextObserbations =new JLabel (a.getVisit_observation());
    lblTextObserbations.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextObserbations.setBounds(32, 736, 1121, 143);
    panel.add(lblTextObserbations);
    
    JLabel lblDate = new JLabel("Date:");
    lblDate.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDate.setBounds(156, 134, 103, 62);
    panel.add(lblDate);
    
    JLabel lblTextVisitID = new JLabel(String.valueOf(a.getVisit_id()));
    lblTextVisitID.setBackground(new Color(255, 255, 224));
    lblTextVisitID.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextVisitID.setBounds(269, 59, 221, 46);
    panel.add(lblTextVisitID);
    
    JLabel lblTextDate = new JLabel(a.getVisit_date().toString());
    lblTextDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextDate.setBackground(new Color(255, 255, 224));
    lblTextDate.setBounds(269, 142, 263, 46);
    panel.add(lblTextDate);

    // Crear un botón de retorno
    JButton botonRetorno = new JButton("Return");
    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    botonRetorno.setBounds(10, 890, 243, 62);
    panel.add(botonRetorno);
 
    JLabel lblDoctor = new JLabel("Doctor:");
    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDoctor.setBounds(570, 46, 103, 53);
    panel.add(lblDoctor);
    
    JLabel lblHospital = new JLabel("Hospital :");
    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblHospital.setBounds(570, 124, 124, 46);
    panel.add(lblHospital);
    
    JLabel lblPatient = new JLabel("Patient :");
    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblPatient.setBounds(570, 210, 143, 46);
    panel.add(lblPatient);
    
    JLabel lblMedications = new JLabel("Medications:");
    lblMedications.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblMedications.setBounds(22, 290, 171, 35);
    panel.add(lblMedications);
    
    a.setMedications(medicationMan.showMedications(a.getVisit_id()));
    
    JPanel botonPanelMaications = new JPanel();
    botonPanelMaications.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

    if(a.getMedications()!=null) {
    // Añadir botones al panel
	    for (int i = 0; i < a.getMedications().size(); i++) {
	        JButton boton = new JButton("Madication ID: " + a.getMedications().get(i).getMedication_id() + " /Type: " + a.getMedications().get(i).getType()+" /Manufacturer:"+a.getMedications().get(i).getManufacturers());
	        botonPanelMaications.add(boton);
	        int l = i;
	        boton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new MedicationInfo(a.getMedications().get(l));
	            }
	        });
    }

    // Envuelve el panel en un JScrollPane
    JScrollPane scrollPane1 = new JScrollPane(botonPanelMaications);
    scrollPane1.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
    scrollPane1.setPreferredSize(new Dimension(700, 300)); 
    panel.add(scrollPane1);
	}else {
		JLabel medication=new JLabel("NO Treatments in this visit");
		botonPanelMaications.add(medication);
	    panel.add(botonPanelMaications);
	}
    
    a.setTreatments(treatmentMan.getTreatments(a.getVisit_id()));
    
    JLabel lblTreatments = new JLabel("Treatments");
    lblTreatments.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblTreatments.setBounds(22, 522, 124, 26);
    panel.add(lblTreatments);

    JPanel botonPanelTreatmentd = new JPanel();
    botonPanelTreatmentd.setLayout(new GridLayout(0, 1)); 
  
	if(a.getTreatments()!=null) {
   // Establecer un diseño de cuadrícula de una sola columna
    for (int i = 0; i < a.getTreatments().size(); i++) {
        JLabel treatment = new JLabel("Treatment ID: " + a.getTreatments().get(i).getTreatmentID() + " /Type: " + a.getTreatments().get(i).getTreatmentType());
        botonPanelTreatmentd.add(treatment);
        
    }
    JScrollPane scrollPane2 = new JScrollPane(botonPanelTreatmentd);
    scrollPane2.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
    scrollPane2.setPreferredSize(new Dimension(700, 300)); 
    panel.add(scrollPane2);
    }else {
    	JLabel treatment=new JLabel("NO Treatments in this visit");
    botonPanelTreatmentd.add(treatment);
    panel.add(botonPanelTreatmentd);
    }

    // Envuelve el panel en un JScrollPane
    
    
    DoctorText = new JLabel(a.getVisit_doctor().getName()+" "+a.getVisit_doctor().getSurname());
    DoctorText.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    DoctorText.setBounds(680, 42, 712, 61);
    panel.add(DoctorText);
    
    
    JLabel lblHospitalName = new JLabel(a.getHospital().getHospitalName());
    lblHospitalName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblHospitalName.setBounds(704, 124, 688, 46);
    panel.add(lblHospitalName);
    
    JLabel lblPatientName = new JLabel(a.getVisit_patient().getPatientName());
    lblPatientName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblPatientName.setBounds(723, 210, 415, 46);
    
    panel.add(lblPatientName);
   
    if(a.getVisit_test()!=null) {
    	 JButton botonTest = new JButton("See this test");
    	    botonTest.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    	    botonTest.setBounds(1213, 193, 340, 81);
    	    panel.add(botonTest);
       botonTest.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
        	  
              new TestInfoDoctores(a.getVisit_test(),u);
           }
       });
       }
 
    botonRetorno.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose(); // Cierra la ventana actual
        }
    });
    
    setVisible(true);
	}

}
