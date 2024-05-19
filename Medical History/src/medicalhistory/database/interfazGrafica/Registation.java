package medicalhistory.database.interfazGrafica;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.jpa.JPAUserManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Role;
import medicalhistory.database.pojos.User;

import java.awt.*;
import java.awt.event.*;

public class Registation extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;
    private static JPAUserManager userMan;
    boolean register = false;
    private String resultLabel;
    private static DoctorManager docMan;
    private static PatientManager patientMan;
    private static HospitalManager hospitalMan;
    private TestManager testMan;
    private VisitManager visitMan;
    private TreatmentManager treatmentMan;
    private MedicationManager medicationMan;
    private static ConnectionManager conMan;


    public Registation() {
    	conMan = new ConnectionManager();
	    userMan = new JPAUserManager();
	    docMan= conMan.getDocMan();
	    patientMan=conMan.getPatientMan();
	    hospitalMan=conMan.getHospitalMan();
	    
	            setTitle("Registro de Usuario");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();

        JLabel labelUsuario = new JLabel("Nombre de usuario:");
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        labelUsuario.setBounds(27, 315, 497, 64);
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        campoUsuario.setBounds(534, 318, 544, 64);
        
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        labelContraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
        labelContraseña.setSize(487, 64);
        labelContraseña.setLocation(51, 413);
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        labelUsuario.setBounds(41, 338, 497, 64);
        campoContraseña = new JPasswordField();
        campoContraseña.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        campoContraseña.setBounds(534, 413, 544, 64);

       
        JButton loginButon = new JButton("Log in ");
        loginButon.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
        loginButon.setBounds(878, 699, 211, 64);
        loginButon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//try {
	                String username = campoUsuario.getText();
	                String password = new String(campoContraseña.getPassword());
					// agregar la lógica para procesar el registro
	                User user = userMan.login(username, password);
	                try {
	  	              
		                if (user != null) {
		                    System.out.println("User found: " + user.toString());

		                    // Mostrar mensaje de bienvenida
		                    JOptionPane.showMessageDialog(null, "Welcome back " + username);

		                    Role role = user.getRole();
		                    switch (role.getName()) {
		                        case "Doctor":
		                            Doctor doc = docMan.getDoctorsbyUsername(user.getUsername());
		                            new DoctorInfo(doc);
		                            break;
		                        case "Patient":
		                            Patient patient = patientMan.getPatientssbyUsername(user.getUsername());
		                            new PatientInfo(patient);
		                            break;
		                        case "Hospital":
		                            Hospital hospi = hospitalMan.getHospitalbyUsername(user.getUsername());
		                            new HospitalInfo(hospi);
		                            break;
		                        default:
		                            JOptionPane.showMessageDialog(null, "Unknown role");
		                            break;
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "User not found");
		                }
		            } catch (Exception a) {
		                JOptionPane.showMessageDialog(null, "An error occurred: " + a.getMessage());
		                a.printStackTrace();
		            }
		        }
		    });

        JButton registerButton = new JButton("Sign in");
        registerButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
        registerButton.setBounds(534, 699, 211, 64);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = campoUsuario.getText();
                String password = new String(campoContraseña.getPassword());
               
				// agregar la lógica para procesar el registro
                openSmallWindow();
                User user = new User(username, password,userMan.getRole(resultLabel));

                if (user != null) {
                    register=true;
                } 
                switch (resultLabel.toString()) {
			    case "Doctor":
			    	new AddDoctor(user.getUsername());
			    	userMan.register(user);
			        break;
			    case "Patient":
			    	new AddPatient(user.getUsername());
			    	userMan.register(user);
			        break;
			    case "Administrator":
			    	new AddHospital(user.getUsername());
			    	userMan.register(user);
			        break;
			    default:
			        
			        break;
			}
            }
        });
        panel.setLayout(null);

        panel.add(labelUsuario);
        panel.add(campoUsuario);
        panel.add(labelContraseña);
        panel.add(campoContraseña);
        panel.add(registerButton);
        panel.add(loginButon);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Welcome to the Medical History Databse");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 40));
        lblNewLabel.setBounds(255, 73, 917, 99);
        panel.add(lblNewLabel);

        setVisible(true);
		
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Registation();
            }
        });
    }

	public boolean isRegister() {
		return register;
	}

	public JTextField getCampoUsuario() {
		return campoUsuario;
	}

	public void setCampoUsuario(JTextField campoUsuario) {
		this.campoUsuario = campoUsuario;
	}

	public JPasswordField getCampoContraseña() {
		return campoContraseña;
	}

	public void setCampoContraseña(JPasswordField campoContraseña) {
		this.campoContraseña = campoContraseña;
	}
	private void openSmallWindow() {
        JDialog smallWindow = new JDialog(this, "Select your role", true);
        smallWindow.setSize(300, 200);
        smallWindow.setLayout(new FlowLayout());

        // Crear botones de radio
        JRadioButton option1 = new JRadioButton("Doctor");
        JRadioButton option2 = new JRadioButton("Patient");
        JRadioButton option3 = new JRadioButton("Administrator");

        // Agrupar los botones de radio para que solo se pueda seleccionar uno
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setEnabled(false);
        // Botón para confirmar la selección
        
        ActionListener radioButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmButton.setEnabled(true);
            }
        };
        option1.addActionListener(radioButtonListener);
        option2.addActionListener(radioButtonListener);
        option3.addActionListener(radioButtonListener);
        confirmButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String selectedOption = null;
                if (option1.isSelected()) {
                    selectedOption = "Doctor";
                } else if (option2.isSelected()) {
                    selectedOption = "Patient";
                } else if (option3.isSelected()) {
                    selectedOption = "Administrator";
                }

				// Devolver la opción seleccionada a la clase original
                if (selectedOption != null) {
                    resultLabel=selectedOption;
                } 

                // Cerrar la ventana pequeña
                smallWindow.dispose();
            }
        });

        // Agregar componentes a la ventana pequeña
        smallWindow.add(option1);
        smallWindow.add(option2);
        smallWindow.add(option3);
        smallWindow.add(confirmButton);

        smallWindow.setLocationRelativeTo(this); // Centrar la ventana pequeña respecto a la ventana principal
        smallWindow.setVisible(true);
    }
}