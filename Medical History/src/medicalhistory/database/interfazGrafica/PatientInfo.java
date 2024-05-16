package medicalhistory.database.interfazGrafica;

import javax.swing.*;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;

public class PatientInfo extends JFrame {
    private JPanel botonPanel;
	private JPanel botonPanelAllergies;
	private JPanel botonPanelVisits;
	private JPanel botonPanelDoctors;
	private AbstractButton botonRetorno;

	public PatientInfo(Patient a) {
        setTitle("Patient Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        
      
        //JLabel labelImagen = new JLabel(a.get);

        // Añadir el JLabel al contenido de la ventana
       // getContentPane().add(labelImagen, BorderLayout.SOUTH);

        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBloodType.setBounds(686, 34, 137, 33);
        panel.add(lblBloodType);
        
        JLabel lblAllergies = new JLabel("Allergies:");
        lblAllergies.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblAllergies.setBounds(686, 84, 137, 33);
        panel.add(lblAllergies);
        
        JLabel lblFullName = new JLabel("Full name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(226, 34, 137, 33);
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblContact.setBounds(226, 84, 137, 33);
        panel.add(lblContact);
        
        JLabel lblBirthDate = new JLabel("Birth date:");
        lblBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBirthDate.setBounds(226, 128, 137, 33);
        panel.add(lblBirthDate);
        
        JLabel lblSex = new JLabel("Sex:");
        lblSex.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblSex.setBounds(686, 140, 137, 33);
        panel.add(lblSex);
        
        JLabel lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatientId.setBounds(226, 170, 137, 33);
        panel.add(lblPatientId);
        
        JLabel lblDoctors = new JLabel("Doctors:");
        lblDoctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblDoctors.setBounds(33, 330, 137, 33);
        panel.add(lblDoctors);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(33, 653, 137, 33);
        panel.add(lblVisits);
        
        JLabel lblTextfullname = new JLabel("textFullName:");
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(340, 34, 312, 33);
        panel.add(lblTextfullname);
        
        JLabel lblTextcontact = new JLabel("textContact:");
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(340, 84, 312, 33);
        panel.add(lblTextcontact);
        
        JPanel principalPanel = new JPanel();
        principalPanel.setBounds(33, 374, 1543, 213);
        panel.add(principalPanel);
        principalPanel.setLayout(new BorderLayout());

        // Crear un panel para los botones con un layout vertical
        botonPanelAllergies = new JPanel();
        botonPanelAllergies.setBounds(62, 313, 1461, 72);
        panel.add(botonPanelAllergies);
        botonPanelAllergies.setLayout(null);
        
        botonPanelVisits = new JPanel();
        botonPanelVisits.setBounds(62, 313, 1461, 72);
        panel.add(botonPanelVisits);
        botonPanelVisits.setLayout(null);
        
        botonPanelDoctors = new JPanel();
        botonPanelDoctors.setBounds(62, 313, 1461, 72);
        panel.add( botonPanelDoctors);
        botonPanelDoctors.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 0, 1334, 72);
        botonPanelAllergies.add(scrollPane);
        
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane.setBounds(10, 0, 1334, 72);
        botonPanelVisits.add(scrollPane2);
        
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane.setBounds(10, 0, 1334, 72);
         botonPanelDoctors.add(scrollPane2);
     
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
    // Método para agregar un botón al panel de botones
	
    private void botonAllergies (Doctor a) {
    	for (int i=0; i<=a.getPatients().size();i++) {
        JButton boton = new JButton("Patient ID: "+a.getPatients().get(i).getPatientID()+" Name: "+a.getPatients().get(i).getPatientName());
        botonPanelAllergies.add(boton);
        int l =i;
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PatientInfo(a.getPatients().get(l));
            }
        });
        
        // Refrescar el panel para que los cambios se muestren correctamente
        botonPanelAllergies.revalidate();
        botonPanelAllergies.repaint();}
    	}
    	
    	private void botonVisit (Patient a) {
        	for (int i=0; i<=a.getVisits().size();i++) {
            JButton boton = new JButton("Visit ID: "+a.getVisits().get(i).getVisit_id()+" Date: "+a.getVisits().get(i).getVisit_date()+"Patient:"+a.getVisits().get(i).getVisit_patient());
            botonPanelVisits.add(boton);
            int l =i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l));
                }
            });
            
            // Refrescar el panel para que los cambios se muestren correctamente
            botonPanelVisits.revalidate();
            botonPanelVisits.repaint();}
        	}
     private void botonDoctors (Patient a) {
       for (int i=0; i<=a.getDoctors().size();i++) {
          JButton boton = new JButton("Hospital ID: "+a.getDoctors().get(i).getDoctor_id()+" Name: "+a.getDoctors().get(i).getName()+" Surname: "+a.getDoctors().get(i).getSurname()+" Specialty:"+a.getDoctors().get(i).getSpecialty());
          botonPanelDoctors.add(boton);
          int l =i;
          boton.addActionListener(new ActionListener() {
        	  public void actionPerformed(ActionEvent e) {
                    new DoctorInfoPatient(a.getDoctors().get(l),a);
                    }
          });
                
          // Refrescar el panel para que los cambios se muestren correctamente
          botonPanelDoctors.revalidate();
          botonPanelDoctors.repaint();
        }	
    }
}
    