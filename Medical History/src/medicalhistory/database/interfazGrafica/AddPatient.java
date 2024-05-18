package medicalhistory.database.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

import medicalhistory.database.interfaces.*;

import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Allergies;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Patient;

public class AddPatient extends JFrame{
	private JTextField textName;
	private JTextField textDateofBith;
	private JTextField textBloodType;
	private JTextField textContact;
	private JLabel imageLabel;
	private byte[]  imageBytes;
	private static DoctorManager docMan;
	private static AllergiesManager allergyMan;
	private static PatientManager patientMan;
	private static ConnectionManager conMan;
	private JTextField textSex;

	public AddPatient(String username) {
		conMan = new ConnectionManager();
		patientMan=conMan.getPatientMan();
		allergyMan=conMan.getAllergiesMan();
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
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setBounds(864, 92, 95, 26);
        lblBloodType.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblBloodType);
        
        
        JLabel lblName = new JLabel("Full name:");
        lblName.setBounds(439, 92, 103, 26);
        lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblName);
        
        JLabel lblDateofBirth = new JLabel("Date of birth:");
        lblDateofBirth.setBounds(426, 46, 141, 26);
        lblDateofBirth.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblDateofBirth);
        
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
	    
	    textDateofBith = new JTextField();
	    textDateofBith.setBounds(566, 52, 288, 20);
	    panel.add(textDateofBith);
	    textDateofBith.setColumns(10);
	    
	    textBloodType = new JTextField();
	    textBloodType.setBounds(975, 98, 400, 20);
	    panel.add(textBloodType );
	    textBloodType .setColumns(10);
	    
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
        
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);

       panel.add(selectImageButton);
       
       List<Allergies> allergies= new ArrayList<Allergies>( );
       
       JButton selectAllergiesButton = new JButton("Select allergies of the patient");
       selectAllergiesButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       selectAllergiesButton.setBounds(43, 555, 647, 52);
       panel.add(selectAllergiesButton);
       
       JScrollPane allergiesSelectedPanel = new JScrollPane();
       allergiesSelectedPanel.setSize(1266, 200);
       allergiesSelectedPanel.setLocation(66, 619);
       allergiesSelectedPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       allergiesSelectedPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       
       selectAllergiesButton.addActionListener( new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
        	   
        	   boolean validInput = false;

               while (!validInput) {
                   // Crear un panel para la ventana emergente
            	   JPanel allergiesPanel = new JPanel();
                   allergiesPanel.setLayout(new BoxLayout(allergiesPanel, BoxLayout.Y_AXIS));
                   // Añadir una etiqueta y un campo de texto para ingresar el ID del hospital
                   JLabel lblAllergie = new JLabel("Allergie Id:");
                   lblAllergie.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                   allergiesPanel.add(lblAllergie);

                   JTextField textAllergie = new JTextField(10);
                   allergiesPanel.add(textAllergie);

                   // Mostrar una ventana emergente para seleccionar el hospital
                   int result = JOptionPane.showConfirmDialog(null, allergiesPanel,
                           "Select Hospital", JOptionPane.OK_CANCEL_OPTION);

                   // Si el usuario hizo clic en "OK" (aceptar)
                   if (result == JOptionPane.OK_OPTION) {
                       try { 
                           // Obtener el ID del hospital del campo de texto y convertirlo a entero
                           int allergieId = (Integer.parseInt(textAllergie.getText()));
                           // Obtener el hospital con el ID proporcionado
                          Allergies allergie= allergyMan.getAllergy(allergieId);

                           // Agregar el hospital a la lista de hospitales
                           allergies.add(allergie);

                           // Si llegamos aquí, la entrada es válida
                           validInput = true;
                       } catch (NullPointerException ex) {
                           // Manejar la excepción si el usuario no ingresó un número válido
                           JOptionPane.showMessageDialog(null, "Please enter a valid allergie ID.", "Error", JOptionPane.ERROR_MESSAGE);
                       }
                   } else {
                       // Si el usuario canceló la operación, salimos del bucle
                       break;
                   }
               }
           }
           
       });
       
       JPanel allergySelectedPanel = new JPanel();
       allergySelectedPanel.setSize(1148, 166);
       allergySelectedPanel.setLocation(24, 658);
       allergySelectedPanel.setLayout(new BorderLayout()); // Use BorderLayout for the JScrollPane

       // Create a JPanel to hold the labels
       JPanel labelPanel = new JPanel();
       labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for the labels

       JScrollPane scrollPane = new JScrollPane(labelPanel); // Add labelPanel to the JScrollPane
       scrollPane.setBounds(0, 25, 1148, 141);
       allergySelectedPanel.add(scrollPane, BorderLayout.CENTER); // Add the JScrollPane to the hospitalSelectedPanel

       if (!allergies.isEmpty()) {
           for (int i = 0; i < allergies.size(); i++) { // Iterate correctly through the list
               JLabel lblHospitalSelected = new JLabel("Name: " + allergies.get(i).getAllergiesName() );
               lblHospitalSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
               labelPanel.add(lblHospitalSelected); // Add the JLabel to the labelPanel
           }
           // Set preferred size of the labelPanel to fit all labels
           labelPanel.setPreferredSize(new Dimension(scrollPane.getWidth(), labelPanel.getComponentCount() * 30));
       }

       panel.add(allergySelectedPanel);

        
	List<Doctor> doctors= new ArrayList<Doctor>( );
	
	
	JButton selectDoctorsButton = new JButton("Select doctors of the patient");
	selectDoctorsButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	selectDoctorsButton.setBounds(10, 245, 647, 52);
	panel.add(selectDoctorsButton);
	selectDoctorsButton.addActionListener( new ActionListener() {
	    @Override
    public void actionPerformed(ActionEvent e) {
 	   
 	   boolean validInput = false;

        while (!validInput) {
            // Crear un panel para la ventana emergente
     	   JPanel doctorsPanel = new JPanel();
            doctorsPanel.setLayout(new BoxLayout(doctorsPanel, BoxLayout.Y_AXIS));
            // Añadir una etiqueta y un campo de texto para ingresar el ID del hospital
            JLabel lblDoctor = new JLabel("Doctor Id:");
            lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
            doctorsPanel.add(lblDoctor);

            JTextField textDoctor = new JTextField(10);
            doctorsPanel.add(textDoctor);

            // Mostrar una ventana emergente para seleccionar el hospital
            int result = JOptionPane.showConfirmDialog(null, doctorsPanel,
                    "Select Doctor", JOptionPane.OK_CANCEL_OPTION);

            // Si el usuario hizo clic en "OK" (aceptar)
            if (result == JOptionPane.OK_OPTION) {
                try { 
                    // Obtener el ID del hospital del campo de texto y convertirlo a entero
                    int doctorId = (Integer.parseInt(textDoctor.getText()));
                    // Obtener el hospital con el ID proporcionado
                   Doctor doctor=docMan.getDoctor(doctorId);

                    // Agregar el hospital a la lista de hospitales
                    doctors.add(doctor);

                    // Si llegamos aquí, la entrada es válida
                    validInput = true;
                } catch (NullPointerException ex) {
                    // Manejar la excepción si el usuario no ingresó un número válido
                    JOptionPane.showMessageDialog(null, "Please enter a valid doctor ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Si el usuario canceló la operación, salimos del bucle
                break;
            }
        }
    }
    
});

	JPanel doxtorSelectedPanel = new JPanel();
	doxtorSelectedPanel.setSize(1148, 166);
	doxtorSelectedPanel.setLocation(24, 658);
	doxtorSelectedPanel.setLayout(new BorderLayout()); // Use BorderLayout for the JScrollPane

	// Create a JPanel to hold the labels
	JPanel labelPanel1 = new JPanel();
	labelPanel1.setLayout(new BoxLayout(labelPanel1, BoxLayout.Y_AXIS)); // Use BoxLayout for the labels

	JScrollPane scrollPane1 = new JScrollPane(labelPanel1); // Add labelPanel to the JScrollPane
	scrollPane1.setBounds(0, 25, 1148, 141);
	doxtorSelectedPanel.add(scrollPane1, BorderLayout.CENTER); // Add the JScrollPane to the hospitalSelectedPanel

	if (!doctors.isEmpty()) {
	    for (int i = 0; i < doctors.size(); i++) { // Iterate correctly through the list
	        JLabel lblDoctorSelected = new JLabel("Name: " + doctors.get(i).getName() + " Surname: " + doctors.get(i).getSurname()+" Specialty: "+doctors.get(i).getSpecialty());
	        lblDoctorSelected.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	        labelPanel1.add(lblDoctorSelected); // Add the JLabel to the labelPanel
	    }
	    // Set preferred size of the labelPanel to fit all labels
	    labelPanel1.setPreferredSize(new Dimension(scrollPane1.getWidth(), labelPanel1.getComponentCount() * 30));
	}

	panel.add(doxtorSelectedPanel);


                imageLabel = new JLabel("Ninguna imagen seleccionada.");
                panel.add(imageLabel);
                imageLabel.setBounds(103, 56, 163, 137);
                
                JLabel lblSex = new JLabel("Sex:");
                lblSex.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                lblSex.setBounds(449, 129, 56, 26);
                panel.add(lblSex);
                
                textSex = new JTextField();
                textSex.setColumns(10);
                textSex.setBounds(531, 135, 330, 20);
                panel.add(textSex);
                JPanel doctorsSelectedPanel=new JPanel();
                panel.add(doctorsSelectedPanel);
                doctorsSelectedPanel.setSize(1266, 198);
                doctorsSelectedPanel.setLocation(66, 312);
                
                
                JScrollPane scrollPane_1 = new JScrollPane();
                doctorsSelectedPanel.add(scrollPane_1);
                scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                
                JButton createPatientButton = new JButton("Add patient to the database");
                //rodear de try Catch 
                 createPatientButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                 createPatientButton.setBounds(1261, 900, 315, 52);
                 createPatientButton.addActionListener( new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                     	try {
                     	 patientMan.addPatient(new Patient ( textName.toString(), Date.valueOf(textDateofBith.toString()),textBloodType.toString(),textContact.toString(),imageBytes,allergies,doctors,username));
                     } catch (NullPointerException a) {
                         // Manejar la excepción si el usuario no ingresó un número válido
                         JOptionPane.showMessageDialog(null, "Please enter a valid Doctor information."+a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                 }
                 }
                 });
              
                 panel.add(createPatientButton);

        setLocationRelativeTo(null); // Centrar la ventana principal en la pantalla
        setVisible(true);
	}
	private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            // Escalar la imagen si es necesario
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            // Refrescar el label para asegurarse de que se muestra la imagen
            imageLabel.revalidate();
            imageLabel.repaint();
            try {
                imageBytes = Files.readAllBytes(selectedFile.toPath());
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error trying to read the image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
	
}
