package medicalhistory.databasegraphicInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
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
        lblName.setBounds(778, 392, 162, 26);
        lblName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblName);
        
        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(104, 387, 141, 37);
        lblAddress.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(lblAddress);

        textName = new JTextField();
        textName.setBounds(950, 380, 626, 56);
	    panel.add(textName);
	    textName.setColumns(10);
	    
	    textAddress = new JTextField();
	    textAddress.setBounds(208, 371, 552, 56);
	    panel.add(textAddress);
	    textAddress.setColumns(10);


    JButton createHospitalButton = new JButton("Add hospital to the database");
    //rodear de try Catch 
     createHospitalButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
     createHospitalButton.setBounds(1143, 779, 433, 173);
     createHospitalButton.addActionListener( new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
         	try {
         	 hospiMan.addHospital(new Hospital ( textName.getText(),textAddress.getText(),username,new ArrayList<Doctor>()));
         	 JOptionPane.showInputDialog(
                     "hospital added correctly", JOptionPane.OK_CANCEL_OPTION);
        	 dispose();
         } catch (NullPointerException | SQLException a) {
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
