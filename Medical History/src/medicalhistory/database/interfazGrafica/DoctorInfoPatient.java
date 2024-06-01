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
	private JPanel panel;
	public DoctorInfoPatient(Doctor a,Patient b){
		
	panel = new JPanel();
	setTitle("Doctor Information");
    setSize(1600, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla

    ImageIcon imageIcon=null;
    if(a.getPhoto()!=null) {
    byte[] photo= a.getPhoto();
    imageIcon = new ImageIcon(photo);
    }
    
    JLabel lblTextPhoto = new JLabel(imageIcon);
    lblTextPhoto.setBounds(174, 11, 337, 276);
    lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    panel.add(lblTextPhoto);

    getContentPane().add(panel);
    panel.setLayout(null);
    panel.setLayout(null);
    
    JLabel lblSpecialty = new JLabel("Specialty:");
    lblSpecialty.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblSpecialty.setBounds(864, 450, 122, 58);
    panel.add(lblSpecialty);
    
    
    JLabel lblFullName = new JLabel("Full name:");
    lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblFullName.setBounds(266, 323, 144, 49);
    panel.add(lblFullName);
    
    JLabel lblContact = new JLabel("Contact:");
    lblContact.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblContact.setBounds(856, 318, 144, 58);
    panel.add(lblContact);
    
    JLabel lblDoctorId = new JLabel("Doctor ID:");
    lblDoctorId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDoctorId.setBounds(266, 455, 144, 49);
    panel.add(lblDoctorId);
    
    JButton btnVisit = new JButton("Plan new visit with this doctor");
    btnVisit.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    btnVisit.setBounds(361, 717, 735, 104);
    panel.add(btnVisit);
    btnVisit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	new NewVisit(a,b);
    	}
    });
    
    JLabel lblTextfullname = new JLabel(a.getName()+" "+a.getSurname());
    lblTextfullname.setBackground(new Color(255, 255, 224));
    lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextfullname.setBounds(420, 314, 422, 67);
    panel.add(lblTextfullname);
    
    JLabel lblTextcontact = new JLabel(a.getContact());
    lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextcontact.setBackground(new Color(255, 255, 224));
    lblTextcontact.setBounds(1031, 309, 422, 77);
    panel.add(lblTextcontact);
    
    JLabel lblTextDoctorId = new JLabel(String.valueOf(a.getDoctor_id()));
    lblTextDoctorId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextDoctorId.setBackground(new Color(255, 255, 224));
    lblTextDoctorId.setBounds(420, 446, 422, 67);
    panel.add(lblTextDoctorId);
    
    JLabel lblTextSpecialty = new JLabel(a.getSpecialty());
    lblTextSpecialty.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextSpecialty.setBackground(new Color(255, 255, 224));
    lblTextSpecialty.setBounds(1031, 441, 422, 77);
    panel.add(lblTextSpecialty);
    
    JLabel lblPhoto = new JLabel("Photo:");
    lblPhoto.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblPhoto.setBounds(30, 48, 103, 26);
    panel.add(lblPhoto);
    
    JButton buttonReturn = new JButton("Return");
    buttonReturn.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    buttonReturn.setBounds(10, 717, 184, 77);
    panel.add(buttonReturn);
    buttonReturn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	dispose();
    	}
    });

    setVisible(true);
	}
	
}
