package medicalhistory.database.interfazGrafica;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Patient;

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
	private static ConnectionManager conMan;

	public AddDoctor(String username) {
		conMan = new ConnectionManager();
		patientMan=conMan.getPatientMan();
		hospiMan=conMan.getHospitalMan();
		docMan=conMan.getDocMan();
		
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
        lblSpecialty.setBounds(864, 92, 95, 26);
        lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSpecialty);
        
        
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(464, 92, 103, 26);
        lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblName);
        
        JLabel lblSurname = new JLabel("Surname:");
        lblSurname.setBounds(464, 46, 103, 26);
        lblSurname.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblSurname);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setBounds(864, 46, 95, 26);
        lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblContact);
        
        JLabel lblImage = new JLabel("Photo:");
        lblImage.setBounds(24, 34, 95, 26);
        lblImage.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblImage);
       
        textName = new JTextField();
        textName.setBounds(556, 98, 298, 20);
	    panel.add(textName);
	    textName.setColumns(10);
	    
	    textSurname = new JTextField();
	    textSurname.setBounds(566, 52, 288, 20);
	    panel.add(textSurname);
	    textSurname.setColumns(10);
	    
	    textSpecialty = new JTextField();
	    textSpecialty.setBounds(975, 98, 400, 20);
	    panel.add(textSpecialty );
	    textSpecialty .setColumns(10);
	    
	    textContact = new JTextField();
	    textContact.setBounds(969, 52, 399, 20);
	    panel.add(textContact );
	    textContact.setColumns(10);
	    
	    JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);
        
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });

        JButton selectImageButton = new JButton("Select image");
        selectImageButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        selectImageButton.setBounds(24, 186, 242, 38);
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        
        imageLabel = new JLabel("Ninguna imagen seleccionada.");
        panel.add(imageLabel);
        imageLabel.setBounds(103, 56, 163, 137);


       panel.add(selectImageButton);
       JPanel hospitalSelectedPanel = new JPanel();
       hospitalSelectedPanel.setSize(1148, 166);
       hospitalSelectedPanel.setLocation(24, 658);
       hospitalSelectedPanel.setLayout(new BorderLayout()); // Use BorderLayout for the JScrollPane

       // Create a JPanel to hold the labels
       JPanel labelPanel = new JPanel();
       labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for the labels

       JScrollPane scrollPane = new JScrollPane(labelPanel); // Add labelPanel to the JScrollPane
       hospitalSelectedPanel.add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane to the hospitalSelectedPanel

       // Add hospitalSelectedPanel to the main panel
       panel.add(hospitalSelectedPanel);

       List<Hospital> hospitals = new ArrayList<Hospital>();

       JButton selectHospitalButton = new JButton("Select a Hospital for the doctor");
       selectHospitalButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       selectHospitalButton.setBounds(24, 578, 647, 52);

       selectHospitalButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               boolean validInput = false;

               while (!validInput) {
                   // Crear un panel para la ventana emergente
                   JPanel hospitalPanel = new JPanel();
                   hospitalPanel.setLayout(new BoxLayout(hospitalPanel, BoxLayout.Y_AXIS));

                   // Añadir una etiqueta y un campo de texto para ingresar el ID del hospital
                   JLabel lblHospital = new JLabel("Hospital ID:");
                   lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                   hospitalPanel.add(lblHospital);

                   JTextField textHospitalId = new JTextField(10);
                   hospitalPanel.add(textHospitalId);

                   // Mostrar una ventana emergente para seleccionar el hospital
                   int result = JOptionPane.showConfirmDialog(null, hospitalPanel,
                           "Select Hospital", JOptionPane.OK_CANCEL_OPTION);

                   // Si el usuario hizo clic en "OK" (aceptar)
                   if (result == JOptionPane.OK_OPTION) {
                       try {
                           // Obtener el ID del hospital del campo de texto y convertirlo a entero
                           int hospitalId = Integer.parseInt(textHospitalId.getText());
                           // Obtener el hospital con el ID proporcionado
                           Hospital hospital = hospiMan.getHospital(hospitalId);

                           // Agregar el hospital a la lista de hospitales
                           hospitals.add(hospital);
                          
                           JLabel lblHospitalSelected = new JLabel("  Name: " + hospital.getHospitalName() + " Address: " + hospital.getHospitalAddress());
                           lblHospitalSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                           labelPanel.add(lblHospitalSelected); // Add the JLabel to the labelPanel
                           labelPanel.revalidate(); // Revalidate to refresh the panel
                           labelPanel.repaint(); // Repaint to show changes

                           // Si llegamos aquí, la entrada es válida
                           validInput = true;
                       } catch (NullPointerException ex) {
                           // Manejar la excepción si el usuario no ingresó un número válido
                           JOptionPane.showMessageDialog(null, "Please enter a valid Hospital ID.", "Error", JOptionPane.ERROR_MESSAGE);
                       }
                   } else {
                       // Si el usuario canceló la operación, salimos del bucle
                       break;
                   }
               }
           }
       });

       panel.add(selectHospitalButton);
        
        
List<Patient> patients= new ArrayList<Patient>( );

JPanel patientSelectedPanel = new JPanel();
patientSelectedPanel.setSize(1148, 166);
patientSelectedPanel.setLocation(23, 337);
patientSelectedPanel.setLayout(new BorderLayout()); // Use BorderLayout for the JScrollPane

// Create a JPanel to hold the labels
JPanel labelPanel_1 = new JPanel();
labelPanel_1.setLayout(new BoxLayout(labelPanel_1, BoxLayout.Y_AXIS)); // Use BoxLayout for the labels

JScrollPane scrollPanel = new JScrollPane(labelPanel_1); // Add labelPanel to the JScrollPane
scrollPanel.setBounds(0, 25, 1148, 141);
patientSelectedPanel.add(scrollPanel, BorderLayout.CENTER); 


panel.add(patientSelectedPanel);
       
JButton selectPatientButton = new JButton("Select a patient for the doctor");
selectPatientButton.setBounds(42, 259, 502, 52);
panel.add(selectPatientButton);
selectPatientButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
selectPatientButton.addActionListener( new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
 	   
 	   boolean validInput = false;

        while (!validInput) {
            // Crear un panel para la ventana emergente
            JPanel patientPanel = new JPanel();
            patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));

            // Añadir una etiqueta y un campo de texto para ingresar el ID del hospital
            JLabel lblPatient = new JLabel("Patient ID:");
            lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
            patientPanel.add(lblPatient);

            JTextField textPatientId = new JTextField(10);
            patientPanel.add(textPatientId);


            // Mostrar una ventana emergente para seleccionar el hospital
            int result = JOptionPane.showConfirmDialog(null, patientPanel,
                    "Select patient", JOptionPane.OK_CANCEL_OPTION);

            // Si el usuario hizo clic en "OK" (aceptar)
            if (result == JOptionPane.OK_OPTION) {
                try {
                    
             	   // Obtener el ID del hospital del campo de texto y convertirlo a entero
                    int patientId = Integer.parseInt(textPatientId.getText());
                  
                    // Obtener el hospital con el ID proporcionado
                    Patient patient = patientMan.getPatient(patientId);
                    
                    // Agregar el hospital a la lista de hospitales
                   patients.add(patient);
                   JLabel lblPatientSelected = new JLabel("Name: " + patient.getPatientName() + " Adress: " + patient.getEmail());
                   lblPatientSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                   labelPanel_1.add(lblPatientSelected); 
                   labelPanel_1.revalidate(); // Revalidate to refresh the panel
                   labelPanel_1.repaint(); //
                    // Si llegamos aquí, la entrada es válida
                    validInput = true;
                } catch (NullPointerException |  NumberFormatException ex) {
                    // Manejar la excepción si el usuario no ingresó un número válido
                    JOptionPane.showMessageDialog(null, "Please enter a valid Patient ID."+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si el usuario canceló la operación, salimos del bucle
                break;
            }
        }
    }
    
});
      

       panel.add(patientSelectedPanel);

 
       JButton createDoctorButton = new JButton("Add Doctor to the database");
       //rodear de try Catch 
        createDoctorButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        createDoctorButton.setBounds(1261, 900, 315, 52);
        createDoctorButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	 docMan.addDoctor(new Doctor ( textName.getText(), textSurname.getText(),textSpecialty.getText(),textContact.getText(),imageBytes,patients,hospitals,username));
            	 JOptionPane.showInputDialog(
                         "doctor added correctly", JOptionPane.OK_CANCEL_OPTION);
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
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imageLabel.setText("Imagen seleccionada: " + selectedFile.getName());
            try {
                imageBytes = Files.readAllBytes(selectedFile.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error trying to read the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
