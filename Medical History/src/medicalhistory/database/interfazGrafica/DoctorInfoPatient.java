package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;

import javax.swing.JButton;

public class DoctorInfoPatient extends JFrame {
	public DoctorInfoPatient(Doctor a,Patient b){
		
	setTitle("Doctor Information");
    setSize(1600, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla

    JPanel panel = new JPanel();
    byte[] photo= a.getPhoto();
    ImageIcon imageIcon = new ImageIcon(photo);

    getContentPane().add(panel);
    panel.setLayout(null);
    panel.setLayout(null);
    
    JLabel lblSpecialty = new JLabel("Specialty:");
    lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblSpecialty.setBounds(864, 92, 95, 26);
    panel.add(lblSpecialty);
    
    
    JLabel lblFullName = new JLabel("Full name:");
    lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblFullName.setBounds(464, 46, 103, 26);
    panel.add(lblFullName);
    
    JLabel lblContact = new JLabel("Contact:");
    lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblContact.setBounds(864, 46, 95, 26);
    panel.add(lblContact);
    
    JLabel lblDoctorId = new JLabel("Doctor ID:");
    lblDoctorId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDoctorId.setBounds(464, 92, 103, 26);
    panel.add(lblDoctorId);
    
    JButton btnVisit = new JButton("New visit");
    btnVisit.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    btnVisit.setBounds(245, 417, 163, 35);
    panel.add(btnVisit);
    btnVisit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	//new 
    	}
    });
    
    JLabel lblTextfullname = new JLabel(a.getName()+" "+a.getSurname());
    lblTextfullname.setBackground(new Color(255, 255, 224));
    lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextfullname.setBounds(579, 46, 221, 26);
    panel.add(lblTextfullname);
    
    JLabel lblTextcontact = new JLabel(a.getContact());
    lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextcontact.setBackground(new Color(255, 255, 224));
    lblTextcontact.setBounds(969, 46, 107, 26);
    panel.add(lblTextcontact);

    
	}
}
