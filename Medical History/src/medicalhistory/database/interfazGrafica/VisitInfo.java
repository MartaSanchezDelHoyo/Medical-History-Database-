package medicalhistory.database.interfazGrafica;

import medicalhistory.database.jdbc.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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

import medicalhistory.database.pojos.Visit;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VisitInfo extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel botonPanelPatients;
	private JTextField DoctorText;
	private JPanel panelBotonesMedication;
	private Connection c;
	private ConnectionManager conMan;
	
public VisitInfo (Visit a) {	
	setTitle("Visit Information");
    setSize(1600, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla

    getContentPane().add(panel);
    panel.setLayout(null);
    panel.setLayout(null);

    JLabel lblVisitId = new JLabel("Visit ID:");
    lblVisitId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblVisitId.setBounds(213, 46, 103, 26);
    panel.add(lblVisitId);
   
    
    JLabel lblDate = new JLabel("Date:");
    lblDate.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDate.setBounds(213, 95, 72, 26);
    panel.add(lblDate);
    
    JLabel lblTextVisitID = new JLabel(String.valueOf(a.getVisit_id()));
    lblTextVisitID.setBackground(new Color(255, 255, 224));
    lblTextVisitID.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextVisitID.setBounds(339, 46, 221, 26);
    panel.add(lblTextVisitID);
    
    JLabel lblTextDate = new JLabel(a.getVisit_date().toString());
    lblTextDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextDate.setBackground(new Color(255, 255, 224));
    lblTextDate.setBounds(320, 95, 107, 26);
    panel.add(lblTextDate);

    // Crear un botón de retorno
    JButton botonRetorno = new JButton("Return");
    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    botonRetorno.setBounds(10, 917, 95, 35);
    panel.add(botonRetorno);
    
            // Crear un panel para los botones con un layout vertical
    botonPanelPatients = new JPanel();
    botonPanelPatients.setBounds(22, 670, 1480, 220);
    panel.add(botonPanelPatients);
    botonPanelPatients.setLayout(null);
    
    JScrollPane scrollPane_2 = new JScrollPane();
    scrollPane_2.setBounds(22, 670, 1480, 220);
    botonPanelPatients.add(scrollPane_2);
    
    JButton btnNewButton = new JButton("New button");
    btnNewButton.setBounds(0, 0, 1470, 162);
    botonPanelPatients.add(btnNewButton);
    
    JButton btnNewButton_2 = new JButton("New button");
    btnNewButton_2.setBounds(0, 113, 1470, 162);
    botonPanelPatients.add(btnNewButton_2);
    
    
    
    JLabel lblDoctor = new JLabel("Doctor:");
    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDoctor.setBounds(584, 46, 103, 26);
    panel.add(lblDoctor);
    
    JLabel lblHospital = new JLabel("Hospital :");
    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblHospital.setBounds(584, 95, 103, 26);
    panel.add(lblHospital);
    
    JLabel lblPatient = new JLabel("Patient :");
    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblPatient.setBounds(584, 144, 103, 26);
    panel.add(lblPatient);
    
    JLabel lblMedications = new JLabel("Medications:");
    lblMedications.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblMedications.setBounds(22, 290, 171, 35);
    panel.add(lblMedications);
    
    JLabel lblTreatments = new JLabel("Treatments");
    lblTreatments.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblTreatments.setBounds(22, 633, 124, 26);
    panel.add(lblTreatments);
    
    DoctorText = new JTextField(a.getVisit_doctor().getName()+" "+a.getVisit_doctor().getSurname());
    DoctorText.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    DoctorText.setBounds(700, 46, 253, 26);
    panel.add(DoctorText);
    DoctorText.setColumns(10);
    
    JLabel lblHospitalName = new JLabel(a.getHospital().getHospitalName());
    lblHospitalName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblHospitalName.setBounds(697, 97, 689, 23);
    panel.add(lblHospitalName);
    
    JLabel lblPatientName = new JLabel(a.getVisit_patient().getPatientName());
    lblPatientName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblPatientName.setBounds(697, 147, 351, 20);
    
    panel.add(lblPatientName);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setBounds(22, 325, 1480, 275);
    panel.add(scrollPane_1);
    
    JPanel botonPanelVisits = new JPanel();
    scrollPane_1.setViewportView(botonPanelVisits);
    botonPanelVisits.setLayout(null);
    
    JButton btnNewButton_1 = new JButton("New button");
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	}
    });
    btnNewButton_1.setBounds(10, 11, 89, 137);
    botonPanelVisits.add(btnNewButton_1);
    
 
    botonRetorno.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose(); // Cierra la ventana actual
        }
    });
    
   JButton botonTest= new JButton("Test ->");
	botonTest.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	TestInfo test =new TestInfo(a.getVisit_test());
            dispose(); // Cierra la ventana actual
        }
    });
	addBotonMedications( a);
	addBotonTreatments(a);
    setVisible(true);
}

    private void addBotonMedications(Visit a) {
        for (int i = 1; i <= a.getMedications().size(); i++) {
            JButton boton = new JButton("Medication:" + a.getMedications().get(i).toString());
            panelBotonesMedication.add(boton);
            boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //open new medication parameter
            
        panelBotonesMedication.revalidate();
        panelBotonesMedication.repaint();
    }
            });
        }
    }
    private void addBotonTreatments(Visit a) {
        for (int i = 1; i <= a.getTreatments().size(); i++) {
            JButton boton = new JButton("Treatment:" + a.getTreatments().get(i).toString());
            panelBotonesMedication.add(boton);
            boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //open new treatment parameter
        panelBotonesMedication.revalidate();
        panelBotonesMedication.repaint();
    }
            });
        }
    }
  
}
