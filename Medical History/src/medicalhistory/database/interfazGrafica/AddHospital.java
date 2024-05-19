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

import medicalhistory.database.interfaces.AllergiesManager;
import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Allergies;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Visit;

public class AddHospital extends JFrame {
	private JTextField textName;
	private JTextField textAddress;
	private JTextField textSpecialty;
	private JTextField textContact;
	private byte[]  imageBytes;
	private static DoctorManager docMan;
	private static HospitalManager hospiMan;
	private static ConnectionManager conMan;

	public AddHospital(String username) {
		conMan = new ConnectionManager();
		docMan=conMan.getDocMan();
		hospiMan=conMan.getHospitalMan();
		
		setTitle("Doctor Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 0, 0);
       

        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        
        
        JLabel lblName = new JLabel("Hospital name:");
        lblName.setBounds(775, 57, 162, 26);
        lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblName);
        
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(80, 46, 141, 37);
        lblAddress.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblAddress);

        textName = new JTextField();
        textName.setBounds(999, 57, 374, 37);
	    panel.add(textName);
	    textName.setColumns(10);
	    
	    textAddress = new JTextField();
	    textAddress.setBounds(231, 49, 288, 37);
	    panel.add(textAddress);
	    textAddress.setColumns(10);
	    
	  
       
	List<Doctor> doctors= new ArrayList<Doctor>( );
	
	
	JButton selectDoctorsButton = new JButton("Select doctors of the hospital");
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
	doxtorSelectedPanel.setSize(1148, 484);
	doxtorSelectedPanel.setLocation(24, 340);
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


    JButton createHospitalButton = new JButton("Add hospital to the database");
    //rodear de try Catch 
     createHospitalButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
     createHospitalButton.setBounds(1261, 900, 315, 52);
     createHospitalButton.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         	try {
         	 hospiMan.addHospital(new Hospital ( textName.getText(),textAddress.getText(),username,doctors));
         	 JOptionPane.showInputDialog(
                     "hospital added correctly", JOptionPane.OK_CANCEL_OPTION);
        	 dispose();
         } catch (NullPointerException a) {
             // Manejar la excepción si el usuario no ingresó un número válido
             JOptionPane.showMessageDialog(null, "Please enter a valid Doctor information."+a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
     }
     }
     });
  
     panel.add(createHospitalButton);
        setLocationRelativeTo(null); // Centrar la ventana principal en la pantalla
        setVisible(true);
	}

}
