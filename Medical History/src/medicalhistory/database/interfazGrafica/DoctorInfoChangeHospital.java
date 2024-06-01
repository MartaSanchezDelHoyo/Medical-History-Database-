package medicalhistory.database.interfazGrafica;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.File;
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
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;

public class DoctorInfoChangeHospital extends JFrame {
	
	private JPanel botonPanelPatients;
	private JPanel botonPanelVisits;
	private JPanel botonPanelHospiatls;
	private static DoctorManager docMan;
	private Container botonPaneHospitals;
	private JPanel panel;
	private byte[] photo;
	private static  ImageIcon imageIcon;
	private static PatientManager patientMan;
    private static HospitalManager hospitalMan;
    private static VisitManager visitMan;
    private static ConnectionManager conMan;
	public DoctorInfoChangeHospital(Doctor a) {
		conMan = new ConnectionManager();
		 patientMan=conMan.getPatientMan();
		 hospitalMan=conMan.getHospitalMan();
		 visitMan=conMan.getVisitMan();
		 docMan=conMan.getDocMan();
		    
		a.setPatients(patientMan.getPatientsByDoctor(a.getDoctor_id()));
        a.setHospitals(hospitalMan.getHospitalByDoctor(a.getDoctor_id()));
        a.setVisits(visitMan.getVisitByDoctor(a.getDoctor_id()));
		panel = new JPanel();
        setTitle("Doctor Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
        imageIcon=null;
        if(a.getPhoto()!=null) {
       photo= a.getPhoto();
        imageIcon = new ImageIcon(photo);
        }
       
        JLabel lblTextPhoto = new JLabel(imageIcon);
        lblTextPhoto.setBounds(108, 46, 181, 219);
        lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextPhoto);
        
        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        JLabel lblSpecialty = new JLabel("Specialty:");
        lblSpecialty.setBounds(816, 467, 131, 63);
        lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSpecialty);
        
        
        JLabel lblFullName = new JLabel("Name:");
        lblFullName.setBounds(108, 484, 131, 63);
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(816, 351, 150, 41);
        lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblContact);
        
        JLabel lblDoctorId = new JLabel("Doctor ID:");
        lblDoctorId.setBounds(108, 350, 131, 42);
        lblDoctorId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblDoctorId);

        JTextField lblTextName = new JTextField (a.getName());
        lblTextName.setBounds(309, 470, 402, 57);
        lblTextName.setBackground(new Color(255, 255, 224));
        lblTextName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextName);
        
        JTextField lblTextSurname = new JTextField (a.getSurname());
        lblTextSurname.setBounds(309, 594, 402, 57);
        lblTextSurname.setBackground(new Color(255, 255, 224));
        lblTextSurname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextSurname);
        
        JTextField lblTextcontact = new JTextField(a.getContact());
        lblTextcontact.setBounds(1031, 340, 439, 63);
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        panel.add(lblTextcontact);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setBounds(10, 854, 364, 77);
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(botonRetorno);
       
              
        JTextField lblTextDoctorId = new JTextField(String.valueOf(a.getDoctor_id()));
        lblTextDoctorId.setBounds(309, 343, 402, 57);
        lblTextDoctorId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextDoctorId.setBackground(new Color(255, 255, 224));
        panel.add(lblTextDoctorId);
        
        JTextField lblTextSpecialty = new JTextField(a.getSpecialty());
        lblTextSpecialty.setBounds(1031, 467, 402, 63);
        lblTextSpecialty.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextSpecialty.setBackground(new Color(255, 255, 224));
        panel.add(lblTextSpecialty);
        
        JLabel lblPhoto = new JLabel("Photo:");
        lblPhoto.setBounds(21, 46, 103, 26);
        lblPhoto.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblPhoto);
        
        JButton changeImage=new JButton("Change photo");
        changeImage.setBounds(463, 854, 439, 77);
        changeImage.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(changeImage);
        
        changeImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null); // Completa la llamada al método showOpenDialog

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                       
                    	 photo = Files.readAllBytes(selectedFile.toPath());
                        imageIcon = new ImageIcon(photo );
                       
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error trying to read the image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JButton changeDoctor=new JButton("Change doctor data");
        changeDoctor.setBounds(1152, 854, 402, 77);
        changeDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(changeDoctor);
        
        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblSurname.setBounds(108, 607, 131, 63);
        panel.add(lblSurname);
        changeDoctor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                docMan.changeDoctor(new Doctor(Integer.parseInt(lblTextDoctorId.getText()),lblTextName.getText(),lblTextSurname.getText(),a.getUsername(),lblTextSpecialty.getText(),lblTextcontact.getText(),photo ));
            }
        });
        

        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
}


