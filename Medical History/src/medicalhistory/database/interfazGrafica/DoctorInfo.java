package medicalhistory.database.interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;

public class DoctorInfo extends JFrame {
	
	private JPanel botonPanelPatients;
	
	public DoctorInfo(Doctor a) {
		
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
        
        JLabel lblPatients = new JLabel("Patients:");
        lblPatients.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatients.setBounds(31, 276, 138, 26);
        panel.add(lblPatients);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(31, 483, 52, 26);
        panel.add(lblVisits);
        
        JLabel lblHospitals = new JLabel("Hospitals:");
        lblHospitals.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblHospitals.setBounds(31, 656, 89, 26);
        panel.add(lblHospitals);
        
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

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);
        
                // Crear un panel para los botones con un layout vertical
        botonPanelPatients = new JPanel();
        botonPanelPatients.setBounds(62, 313, 1461, 72);
        panel.add(botonPanelPatients);
        botonPanelPatients.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 0, 1334, 72);
        botonPanelPatients.add(scrollPane);
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(null);
        botonPanelVisits.setBounds(62, 520, 1461, 124);
        panel.add(botonPanelVisits);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 1461, 127);
        botonPanelVisits.add(scrollPane_1);
     
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
    // Método para agregar un botón al panel de botones
	
    private void agregarBoton(Doctor a) {
    	for (int i=0; i<=a.getPatients().size();i++) {
        JButton boton = new JButton("Patient ID: "+a.getPatients().get(i).getPatientID()+" Name: "+a.getPatients().get(i).getPatientName());
        botonPanelPatients.add(boton);
        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DoctorInfo.this, "You selected: " + ((JButton)e.getSource()).getText());
                
            }
        });
        // Refrescar el panel para que los cambios se muestren correctamente
        botonPanelPatients.revalidate();
        botonPanelPatients.repaint();
    }
    }
    }


