package medicalhistory.databasegraphicInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.pojos.*;
import javax.swing.JTextField;

public class VisitReservation extends JFrame  {
	private JPanel panel = new JPanel();
	private JTextField TextDate;
	private JTextField textHospital;
	public HospitalManager hospitalMan;
 public VisitReservation(Doctor d, Patient p ) {
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
	    
	    JLabel lblDoctor = new JLabel("Doctor:");
	    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblDoctor.setBounds(470, 108, 72, 26);
	    panel.add(lblDoctor );
	    
	    JLabel lblDoctorName = new JLabel(d.getName()+d.getSurname());
	    lblDoctorName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
	    lblDoctorName.setBounds(566, 108, 72, 26);
	    panel.add(lblDoctorName);
	    
	    JLabel lblPatient = new JLabel("Patient:");
	    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblPatient.setBounds(470, 46, 72, 26);
	    panel.add(lblPatient);
	    
	    JLabel lblPatientName = new JLabel(p.getPatientName());
	    lblPatientName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
	    lblPatientName.setBounds(566, 46, 72, 26);
	    panel.add(lblPatientName);
	    
	    JLabel lblHospital = new JLabel("Hospital:");
	    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblHospital.setBounds(687, 108, 72, 26);
	    panel.add(lblHospital);

	    // Crear un botón de retorno
	    JButton botonRetorno = new JButton("Return");
	    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    botonRetorno.setBounds(10, 917, 95, 35);
	    panel.add(botonRetorno);
	    
	    TextDate = new JTextField();
	    TextDate.setBounds(315, 101, 96, 20);
	    panel.add(TextDate);
	    TextDate.setColumns(10);
	    //Date date =new Date(Parse.date(TextDate.getText()));
	    
	    textHospital = new JTextField();
	    textHospital.setBounds(787, 107, 96, 35);
	    panel.add(textHospital);
	    textHospital.setColumns(10);
	    
	   // Hospital hospital=hospitalMan.get
 }
}
