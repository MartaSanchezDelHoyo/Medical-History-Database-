package medicalhistory.database.interfazGrafica;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

	public AddDoctor() {
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

        JButton selectImageButton = new JButton("Select image");
        selectImageButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        selectImageButton.setBounds(24, 186, 242, 38);
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });
        

       panel.add(selectImageButton);
       List<Hospital> hospitals= new ArrayList<Hospital>( );
       
       JButton selectHospitalButton = new JButton("Select a Hospital for the doctor");
       selectHospitalButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       selectHospitalButton.setBounds(10, 245, 647, 52);
       selectHospitalButton.addActionListener( new ActionListener() {
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
       JPanel hospitalSelectedPanel=new JPanel();
       hospitalSelectedPanel.setSize(1148, 166);
       hospitalSelectedPanel.setLocation(24, 658);
       if(!hospitals.isEmpty()) {
       for(int i=0;i<=hospitals.size()+1;i++) {
       JLabel lblHospitalSelected = new JLabel("Name: " +hospitals.get(i).getHospitalName()+"Adress: "+hospitals.get(i).getHospitalAddress());
       lblHospitalSelected.setBounds(24, 34, 95, 26);
       lblHospitalSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       hospitalSelectedPanel.add(lblHospitalSelected);
       }   
       }
       panel.add(hospitalSelectedPanel);
       hospitalSelectedPanel.setLayout(null);
       
       JLabel lblDoctorPatients = new JLabel("Doctor´s patients:");
       lblDoctorPatients.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       lblDoctorPatients.setBounds(483, 0, 179, 26);
       hospitalSelectedPanel.add(lblDoctorPatients);
       
       JScrollPane scrollPane_1 = new JScrollPane();
       scrollPane_1.setBounds(-52, 25, 1148, 141);
       hospitalSelectedPanel.add(scrollPane_1);
        panel.add(selectHospitalButton);
        
List<Patient> patients= new ArrayList<Patient>( );
       
       JButton selectPatientButton = new JButton("Select a patient for the doctor");
       selectPatientButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       selectPatientButton.setBounds(32, 562, 315, 52);
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
       
       JPanel patientSelectedPanel=new JPanel();
       patientSelectedPanel.setSize(1148, 166);
       patientSelectedPanel.setLocation(23, 337);
       
       JScrollPane scrollPane = new JScrollPane();
       scrollPane.setBounds(0, 25, 1148, 141);
       patientSelectedPanel.add(scrollPane);
       panel.add(selectPatientButton);
       
       if(!patients.isEmpty()) {
    	   for(int i=0;i<=patients.size()+1;i++) {
		       JLabel lblPatientSelected = new JLabel("Name: " +patients.get(i).getPatientName()+"Adress: "+patients.get(i).getEmail());
		       lblPatientSelected.setBounds(24, 34, 95, 26);
		       lblPatientSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
		       scrollPane.add(lblPatientSelected);
    	   }   
       }
       panel.add(patientSelectedPanel);
       patientSelectedPanel.setLayout(null);
       
       JLabel lblDoctorHospitals = new JLabel("Doctor´s hospitals:");
       lblDoctorHospitals.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       lblDoctorHospitals.setBounds(483, 0, 179, 26);
       patientSelectedPanel.add(lblDoctorHospitals);
 
       JButton createDoctorButton = new JButton("Add Doctor to the database");
       //rodear de try Catch 
        createDoctorButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        createDoctorButton.setBounds(1261, 900, 315, 52);
        createDoctorButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            	 docMan.addDoctor(new Doctor ( textName.toString(), textSurname.toString(),textSpecialty.toString(),textContact.toString(),imageBytes,patients,hospitals));
            } catch (NullPointerException a) {
                // Manejar la excepción si el usuario no ingresó un número válido
                JOptionPane.showMessageDialog(null, "Please enter a valid Doctor information."+a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
        });
      
       
       
  
        panel.add(createDoctorButton);
        
                imageLabel = new JLabel("Ninguna imagen seleccionada.");
                panel.add(imageLabel);
                imageLabel.setBounds(103, 56, 163, 137);

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
	public static void main(String[] args) {
        new AddDoctor();
    }
}
