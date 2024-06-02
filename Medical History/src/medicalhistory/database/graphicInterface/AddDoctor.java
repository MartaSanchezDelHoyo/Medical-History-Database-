package medicalhistory.databasegraphicInterface;

import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Test;
import medicalhistory.database.pojos.Visit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class AddDoctor extends JFrame {

	private JTextField textName;
	private JTextField textSurname;
	private JTextField textSpecialty;
	private JTextField textContact;
	private JLabel imageLabel;
	private byte[]  imageBytes;
	private static DoctorManager docMan;
	private static HospitalManager hospiMan;
	private static PatientManager patientMan;
	private static VisitManager visitMan;
	private static ConnectionManager conMan;

	public  AddDoctor(String username) {
		conMan = new ConnectionManager();
		patientMan=conMan.getPatientMan();
		hospiMan=conMan.getHospitalMan();
		docMan=conMan.getDocMan();
		visitMan=conMan.getVisitMan();
		setTitle("Doctor Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // añadir la photo desde la interfaz 
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 0, 0);
       

        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        JLabel lblSpecialty = new JLabel("Specialty:");
        lblSpecialty.setBounds(1124, 452, 135, 39);
        lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSpecialty);
        
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(482, 445, 113, 52);
        lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblName);
        
        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(482, 347, 113, 54);
        lblSurname.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSurname);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(1124, 347, 95, 54);
        lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblContact);
        
        JLabel lblImage = new JLabel("Photo:");
        lblImage.setBounds(34, 103, 103, 52);
        lblImage.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblImage);
       
        textName = new JTextField();
        textName.setBounds(605, 448, 460, 52);
	    panel.add(textName);
	    textName.setColumns(10);
	    
	    textSurname = new JTextField();
	    textSurname.setBounds(605, 345, 460, 52);
	    panel.add(textSurname);
	    textSurname.setColumns(10);
	    
	    textSpecialty = new JTextField();
	    textSpecialty.setBounds(1245, 448, 331, 52);
	    panel.add(textSpecialty );
	    textSpecialty .setColumns(10);
	    
	    textContact = new JTextField();
	    textContact.setBounds(1239, 356, 399, 43);
	    panel.add(textContact );
	    textContact.setColumns(10);
	    
	   
        JButton selectImageButton = new JButton("Select image");
        selectImageButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        selectImageButton.setBounds(58, 631, 460, 81);
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        
        imageLabel = new JLabel("Ninguna imagen seleccionada.");
        panel.add(imageLabel);
        imageLabel.setBounds(123, 192, 288, 349);


       panel.add(selectImageButton);
      
       JButton createDoctorButton = new JButton("Add Doctor to the database");
       //rodear de try Catch 
        createDoctorButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        createDoctorButton.setBounds(1261, 900, 315, 52);
        createDoctorButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	Doctor doc=new Doctor ( textName.getText(), textSurname.getText(),textSpecialty.getText(),textContact.getText(),imageBytes,new ArrayList<Patient>(),new ArrayList<Hospital>(),username);
            	 docMan.addDoctor(doc);
            	 dispose();
             
            } catch (NullPointerException | SQLException a) {
                // Manejar la excepción si el usuario no ingresó un número válido
                JOptionPane.showMessageDialog(null, "Please enter a valid Doctor information."+a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
        });
     
        panel.add(createDoctorButton);
    
        setLocationRelativeTo(null); // Centrar la ventana principal en la pantalla
        setVisible(true);
	}
	private void selectImage() {
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
        int returnValue = fileChooser.showOpenDialog(AddDoctor.this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                imageBytes = Files.readAllBytes(selectedFile.toPath());
                // Cargar la imagen y establecerla en el JLabel
                Image img = ImageIO.read(selectedFile);
                ImageIcon icon = new ImageIcon(img);
                imageLabel.setIcon(icon);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(AddDoctor.this, "Error trying to read the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

  }


