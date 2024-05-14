package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;

public class HospitalInfo extends JFrame{
	JPanel panel = new JPanel();
	private JPanel botonPanelPatients;

public HospitalInfo(Hospital a) {
		
        setTitle("Hospital Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        
        
        JLabel lblFullName = new JLabel("Name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(464, 46, 103, 26);
        panel.add(lblFullName);
        
        JLabel Address = new JLabel("Address:");
        Address.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        Address.setBounds(864, 46, 95, 26);
        panel.add(Address);
        
        JLabel HospitalId = new JLabel("Hospital ID:");
        HospitalId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        HospitalId.setBounds(464, 92, 143, 26);
        panel.add(HospitalId);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(31, 299, 52, 26);
        panel.add(lblVisits);
        
        JLabel Doctors = new JLabel("Doctors:");
        Doctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        Doctors.setBounds(31, 656, 89, 26);
        panel.add(Doctors);
        
        JLabel lblTextfullname = new JLabel(a.getHospitalName());
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(579, 46, 221, 26);
        panel.add(lblTextfullname);
        
        JLabel lblTextaddress = new JLabel(a.getHospitalAddress());
        lblTextaddress.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextaddress.setBackground(new Color(255, 255, 224));
        lblTextaddress.setBounds(969, 46, 107, 26);
        panel.add(lblTextaddress);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);
        
                // Crear un panel para los botones con un layout vertical
        botonPanelPatients = new JPanel();
        botonPanelPatients.setBounds(43, 709, 1480, 169);
        panel.add(botonPanelPatients);
        botonPanelPatients.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(-30, 0, 1491, 169);
        botonPanelPatients.add(scrollPane);
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(null);
        botonPanelVisits.setBounds(41, 336, 1461, 275);
        panel.add(botonPanelVisits);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(-10, 0, 1471, 275);
        botonPanelVisits.add(scrollPane_1);
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
	}
}