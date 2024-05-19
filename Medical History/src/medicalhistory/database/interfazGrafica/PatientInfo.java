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
        
        byte[] photo= a.getPhoto();
        ImageIcon imageIcon = new ImageIcon(photo);
        
        JLabel lblTextPhoto = new JLabel(imageIcon);
        lblTextPhoto.setBounds(79, 46, 181, 219);
        lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextPhoto);
        
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBloodType.setBounds(686, 34, 137, 33);
        panel.add(lblBloodType);
       
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
        lblSex.setBounds(686, 84, 137, 33);
        panel.add(lblSex);
        
        JLabel lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatientId.setBounds(226, 170, 137, 33);
        panel.add(lblPatientId);

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
        
        
        JLabel lblAllergies = new JLabel("Allergies:");
        lblAllergies.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblAllergies.setBounds(33, 486, 137, 33);
        panel.add(lblAllergies);
        // Crear un panel para los botones con un layout vertical
        botonPanelAllergies = new JPanel();
        botonPanelAllergies.setBounds(33, 518, 1461, 128);
        panel.add(botonPanelAllergies);
        botonPanelAllergies.setLayout(null);
        
        for (int i=0; i<=a.getAlergies().size();i++) {
	        JButton boton = new JButton("Allergy ID: "+a.getAlergies().get(i).getAllergiesID()+" Name: "+a.getAlergies().get(i).getAllergiesName());
	        botonPanelAllergies.add(boton);
	        int l =i;
	        boton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                //new AllergieInfo (a.getAlergies().get(l));
	            }
	        });
	        JScrollPane scrollPane = new JScrollPane(botonPanelAllergies);
	        scrollPane.setPreferredSize(new Dimension(200, 300)); // Establece el tamaño preferido del JScrollPane
	        panel.add(scrollPane);
	        // Refrescar el panel para que los cambios se muestren correctamente
	        botonPanelAllergies.revalidate();
	        botonPanelAllergies.repaint();
	        
        }
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(33, 653, 137, 33);
        panel.add(lblVisits);
        
        botonPanelVisits = new JPanel();
        botonPanelVisits.setBounds(33, 697, 1461, 167);
        panel.add(botonPanelVisits);
        botonPanelVisits.setLayout(null);
        
        for (int i=0; i<=a.getVisits().size();i++) {
	        JButton boton = new JButton("Visit ID: "+a.getVisits().get(i).getVisit_id()+" Date: "+a.getVisits().get(i).getVisit_date()+ " Doctor:"+a.getVisits().get(i).getVisit_doctor());
	        botonPanelVisits.add(boton);
	        int l =i;
	        boton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new VisitInfo (a.getVisits().get(l));
	            }
	        });
	        JScrollPane scrollPane = new JScrollPane(botonPanelVisits);
	        scrollPane.setPreferredSize(new Dimension(200, 300)); // Establece el tamaño preferido del JScrollPane
	        panel.add(scrollPane);
	        // Refrescar el panel para que los cambios se muestren correctamente
	        botonPanelVisits.revalidate();
	        botonPanelVisits.repaint();
	        
        }
        
        JLabel lblDoctors = new JLabel("Doctors:");
        lblDoctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblDoctors.setBounds(33, 241, 137, 33);
        panel.add(lblDoctors);
        
        botonPanelDoctors = new JPanel();
        botonPanelDoctors.setBounds(33, 312, 1461, 148);
        panel.add( botonPanelDoctors);
        botonPanelDoctors.setLayout(null);
        
        for (int i=0; i<=a.getDoctors().size();i++) {
	        JButton boton = new JButton("Doctor ID: "+a.getDoctors().get(i).getDoctor_id()+" Specialty: "+a.getDoctors().get(i).getSpecialty()+" Name: "+a.getDoctors().get(i).getName());
	        botonPanelDoctors.add(boton);
	        int l =i;
	        boton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                new DoctorInfoPatient (a.getDoctors().get(l),a);
	            }
	        });
	        JScrollPane scrollPane = new JScrollPane(botonPanelDoctors);
	        scrollPane.setPreferredSize(new Dimension(200, 300)); // Establece el tamaño preferido del JScrollPane
	        panel.add(scrollPane);
	        // Refrescar el panel para que los cambios se muestren correctamente
	        botonPanelDoctors.revalidate();
	        botonPanelDoctors.repaint();
	        
        }
        
     
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
    // Método para agregar un botón al panel de botones
	

}
    