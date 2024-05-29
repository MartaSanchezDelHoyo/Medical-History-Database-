package medicalhistory.database.interfazGrafica;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;

public class DoctorInfoHospial extends JFrame {
	
	private JPanel botonPanelPatients;
	private JPanel botonPanelVisits;
	private JPanel botonPanelHospiatls;
	
	private Container botonPaneHospitals;
	private JPanel panel;
	
	private static PatientManager patientMan;
    private static HospitalManager hospitalMan;
    private static VisitManager visitMan;
    private static ConnectionManager conMan;
	public DoctorInfoHospial(Doctor a) {
		conMan = new ConnectionManager();
		 patientMan=conMan.getPatientMan();
		    hospitalMan=conMan.getHospitalMan();
		    visitMan=conMan.getVisitMan();
		a.setPatients(patientMan.getPatientsByDoctor(a.getDoctor_id()));
        a.setHospitals(hospitalMan.getHospitalByDoctor(a.getDoctor_id()));
        a.setVisits(visitMan.getVisitByDoctor(a.getDoctor_id()));
		panel = new JPanel();
        setTitle("Doctor Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
        ImageIcon imageIcon=null;
        if(a.getPhoto()!=null) {
        byte[] photo= a.getPhoto();
        imageIcon = new ImageIcon(photo);
        }
       
        JLabel lblTextPhoto = new JLabel(imageIcon);
        lblTextPhoto.setBounds(79, 46, 181, 219);
        lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextPhoto);
        
        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        JLabel lblSpecialty = new JLabel("Specialty:");
        lblSpecialty.setBounds(864, 92, 95, 26);
        lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSpecialty);
        
        
        JLabel lblFullName = new JLabel("Full name:");
        lblFullName.setBounds(464, 46, 103, 26);
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(864, 46, 95, 26);
        lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblContact);
        
        JLabel lblDoctorId = new JLabel("Doctor ID:");
        lblDoctorId.setBounds(464, 92, 103, 26);
        lblDoctorId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblDoctorId);
        
        JLabel lblPatients = new JLabel("Patients:");
        lblPatients.setBounds(31, 276, 138, 26);
        lblPatients.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblPatients);
        
        JPanel botonPanelPatients = new JPanel();
        botonPanelPatients.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna
        JLabel NullPatients;
        if(a.getPatients()!=null) {
        // Añadir botones al panel
        for (int i = 0; i < a.getPatients().size(); i++) {
            JButton boton = new JButton("Patient ID: " + a.getPatients().get(i).getPatientID() + " Name: " + a.getPatients().get(i).getPatientName());
            botonPanelPatients.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new PatientInfo(a.getPatients().get(l));
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(botonPanelPatients);
        scrollPane1.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane1.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane1);
        }else {
        	NullPatients= new JLabel("this doctor has no patients yet");
        	NullPatients.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        	botonPanelPatients.add(NullPatients);
        	panel.add(botonPanelPatients);
        }	
        
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setBounds(31, 483, 52, 26);
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblVisits);
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Disposición en una sola columna
        JLabel NullVisits;
        if(a.getVisits()!=null) {
        // Añadir botones al panel
        for (int i = 0; i < a.getVisits().size(); i++) {
            JButton boton = new JButton("Visit ID: " + a.getVisits().get(i).getVisit_id() + " Patient: " + a.getVisits().get(i).getVisit_patient().getPatientName());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l));
                }
            });
        }
        }else {
        	NullVisits= new JLabel("this doctor has no visits yet");
        	NullVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        	botonPanelVisits.add(NullVisits);
        	panel.add(botonPanelVisits);
        }	
        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelVisits);
        scrollPane.setBounds(49, 513, 1332, 137); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); // Establece el tamaño preferido del JScrollPane

		panel.add(scrollPane);
        
        
        JLabel lblHospitals = new JLabel("Hospitals:");
        lblHospitals.setBounds(31, 656, 89, 26);
        lblHospitals.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblHospitals);
        
        JPanel botonPaneHospitals = new JPanel();
        botonPaneHospitals.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna
        
        JLabel NullHospitals;
        if(a.getHospitals()!=null) {
        for (int i = 0; i < a.getHospitals().size(); i++) {
            JButton boton = new JButton("Hospital ID: " + a.getHospitals().get(i).getHospitalID() + " Name: " + a.getHospitals().get(i).getHospitalName() + " Address: " + a.getHospitals().get(i).getHospitalAddress());
            botonPaneHospitals.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new HospitalInfoPatient(a.getHospitals().get(l));
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(botonPaneHospitals);
        scrollPane2.setBounds(49, 719, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane2.setPreferredSize(new Dimension(1332, 300)); // Establece el tamaño preferido del JScrollPane
        panel.add(scrollPane2);
        }else {
        	NullHospitals= new JLabel("this doctor has no visits yet");
        	NullHospitals.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        	botonPaneHospitals.add(NullHospitals);
        	panel.add(botonPaneHospitals);
        }	
        
        JLabel lblTextfullname = new JLabel(a.getName()+" "+a.getSurname());
        lblTextfullname.setBounds(579, 46, 221, 26);
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextfullname);
        
        JLabel lblTextcontact = new JLabel(a.getContact());
        lblTextcontact.setBounds(969, 46, 284, 26);
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        panel.add(lblTextcontact);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setBounds(10, 917, 114, 35);
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(botonRetorno);
       
              
        JLabel lblTextDoctorId = new JLabel(String.valueOf(a.getDoctor_id()));
        lblTextDoctorId.setBounds(579, 92, 221, 26);
        lblTextDoctorId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextDoctorId.setBackground(new Color(255, 255, 224));
        panel.add(lblTextDoctorId);
        
        JLabel lblTextSpecialty = new JLabel(a.getSpecialty());
        lblTextSpecialty.setBounds(979, 92, 221, 26);
        lblTextSpecialty.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextSpecialty.setBackground(new Color(255, 255, 224));
        panel.add(lblTextSpecialty);
        
        JLabel lblPhoto = new JLabel("Photo:");
        lblPhoto.setBounds(10, 29, 103, 26);
        lblPhoto.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblPhoto);
        
       
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
    
}


