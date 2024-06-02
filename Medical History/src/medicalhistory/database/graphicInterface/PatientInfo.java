package medicalhistory.databasegraphicInterface;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import medicalhistory.database.interfaces.AllergiesManager;
import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.User;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class PatientInfo extends JFrame {
    private JPanel botonPanel;
	private JPanel botonPanelAllergies;
	private JPanel botonPanelVisits;
	private JPanel botonPanelDoctors;
	private static AllergiesManager allergyMan;
	private AbstractButton botonRetorno;
	private static VisitManager visitMan;
    private static DoctorManager docMan;
    private static XMLManager xmlManager;
    
	private static ConnectionManager conMan;
	
	public PatientInfo(Patient a,User u) {

		conMan = new ConnectionManager();
		visitMan= conMan.getVisitMan();
		docMan= conMan.getDocMan();
		allergyMan=conMan.getAllergiesMan();
		xmlManager= conMan.getXMLMan();
	
		
		a.setAlergies(allergyMan.getAllergies(a.getPatientID()));
        a.setVisits(visitMan.getVisitByPatient(a.getPatientID()));
        a.setDoctors(docMan.getDoctorsByPatient(a.getPatientID()));
        setTitle("Patient Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        
        ImageIcon imageIcon=null;
        if(a.getPhoto()!=null) {
        byte[] photo= a.getPhoto();
        imageIcon = new ImageIcon(photo);
        }
        
        JLabel lblTextPhoto = new JLabel(imageIcon);
        lblTextPhoto.setBounds(80, 46, 181, 219);
        lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextPhoto);
        
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBloodType.setBounds(899, 34, 137, 33);
        panel.add(lblBloodType);
       
        JLabel lblFullName = new JLabel("Full name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(383, 34, 137, 33);
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblContact.setBounds(383, 84, 137, 33);
        panel.add(lblContact);
        
        JLabel lblBirthDate = new JLabel("Birth date:");
        lblBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBirthDate.setBounds(383, 131, 137, 33);
        panel.add(lblBirthDate);
        
        JLabel lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatientId.setBounds(383, 175, 137, 33);
        panel.add(lblPatientId);

        JLabel lblTextfullname = new JLabel(a.getPatientName());
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(534, 34, 312, 33);
        panel.add(lblTextfullname);
        
        JLabel lblTextcontact = new JLabel(a.getEmail());
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(544, 84, 312, 33);
        panel.add(lblTextcontact);
        
        
        JLabel lblAllergies = new JLabel("Allergies:");
        lblAllergies.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblAllergies.setBounds(33, 486, 137, 33);
        panel.add(lblAllergies);
        // Crear un panel para los botones con un layout vertical
        JPanel botonAllergiesPatients = new JPanel();
        botonAllergiesPatients.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getAllergies().size(); i++) {
            JLabel allergy = new JLabel("Allergie ID: " + a.getAllergies().get(i).getAllergiesID() + " Name: " + a.getAllergies().get(i).getAllergiesName());
            botonAllergiesPatients.add(allergy);
            
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(botonAllergiesPatients);
        scrollPane1.setBounds(49, 513, 1332, 140); // Establecer el tamaño y posición del JScrollPane
        scrollPane1.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane1);
     
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(33, 653, 137, 33);
        panel.add(lblVisits);
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getVisits().size(); i++) {
            JButton boton = new JButton("Visit ID: " + a.getVisits().get(i).getVisit_id() + " /Date: " + a.getVisits().get(i).getVisit_date()+" /Doctor:" + a.getVisits().get(i).getVisit_doctor().getName()+" /Specialty: "+ a.getVisits().get(i).getVisit_doctor().getSpecialty());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l),u);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(botonPanelVisits);
        scrollPane2.setBounds(49, 713, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane2.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane2);
     
      
        JLabel lblDoctors = new JLabel("Doctors:");
        lblDoctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblDoctors.setBounds(33, 269, 137, 33);
        panel.add(lblDoctors);
        
        JPanel botonPanelDoctors = new JPanel();
        botonPanelDoctors.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getDoctors().size(); i++) {
            JButton boton = new JButton("Doctor ID: " + a.getDoctors().get(i).getDoctor_id() + " /Name: " + a.getDoctors().get(i).getDoctor_id()+ " /Specialty:"+ a.getDoctors().get(i).getSpecialty());
            botonPanelDoctors.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DoctorInfoPatient(a.getDoctors().get(l),a);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelDoctors);
        scrollPane.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane);
       
       JLabel lblPhoto = new JLabel("Photo:");
       lblPhoto.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       lblPhoto.setBounds(0, 9, 103, 26);
       panel.add(lblPhoto);
       
       JLabel lblTextBirthDate = new JLabel(a.getDateofbirth().toString());
       lblTextBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextBirthDate.setBackground(new Color(255, 255, 224));
       lblTextBirthDate.setBounds(534, 131, 312, 33);
       panel.add(lblTextBirthDate);
       
       JLabel lblTextID = new JLabel(a.getPatientID().toString());
       lblTextID.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextID.setBackground(new Color(255, 255, 224));
       lblTextID.setBounds(515, 175, 312, 33);
       panel.add(lblTextID);
       
       JLabel lblTextBloodType = new JLabel(a.getBloodtype());
       lblTextBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextBloodType.setBackground(new Color(255, 255, 224));
       lblTextBloodType.setBounds(1027, 34, 312, 33);
       panel.add(lblTextBloodType);
        
       JButton botonXML = new JButton("Obtain Xml with this information until date");
       botonXML.setBounds(241, 873, 644, 90);
       botonXML.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       panel.add(botonXML);
      
        botonXML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new XmlPatient(a);
            }
            
        });
        
        JButton botonHTML = new JButton("Obtain HTML with this information until date");
        botonHTML.setBounds(893, 873, 644, 90);
        botonHTML.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(botonHTML);
       
         botonHTML.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             	new HTMLPatient(a);
             }
             
         });
         panel.add(botonHTML);
       JButton botonRetorno = new JButton("Return");
       botonRetorno.setBounds(10, 917, 114, 35);
       botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       panel.add(botonRetorno);
       
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
}
    