package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Hospital;

public class HospitalInfo extends JFrame{
	JPanel panel = new JPanel();

    private static DoctorManager docMan;
    private static VisitManager visitMan;
    private static ConnectionManager conMan;
    
public HospitalInfo(Hospital a) {
		conMan = new ConnectionManager();
		 visitMan=conMan.getVisitMan();
		 docMan= conMan.getDocMan();
		 
		 a.setHospital_doctors(docMan.getDoctorsbyHospital(a.getHospitalID()));
	     a.setHospital_visits(visitMan.getVisitByHospital(a.getHospitalID()));	
	
        setTitle("Hospital Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
        
        
        
        JLabel lblFullName = new JLabel("Name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(293, 46, 103, 26);
        panel.add(lblFullName);
        
        JLabel Address = new JLabel("Address:");
        Address.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        Address.setBounds(864, 46, 95, 26);
        panel.add(Address);
        
        JLabel HospitalId = new JLabel("Hospital ID:");
        HospitalId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        HospitalId.setBounds(282, 95, 143, 26);
        panel.add(HospitalId);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(16, 670, 52, 26);
        panel.add(lblVisits);
        
        JLabel Doctors = new JLabel("Doctors:");
        Doctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        Doctors.setBounds(16, 274, 89, 26);
        panel.add(Doctors);
        
        JLabel lblTextfullname = new JLabel(a.getHospitalName());
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(406, 46, 264, 26);
        panel.add(lblTextfullname);
        
        JLabel lblTextaddress = new JLabel(a.getHospitalAddress());
        lblTextaddress.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextaddress.setBackground(new Color(255, 255, 224));
        lblTextaddress.setBounds(969, 46, 270, 26);
        panel.add(lblTextaddress);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);
        
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
                // Crear un panel para los botones con un layout vertical
        JPanel botonPanelDoctors = new JPanel();
        botonPanelDoctors.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getHospital_doctors().size(); i++) {
            JButton boton = new JButton("Doctor ID: " + +a.getHospital_doctors().get(i).getDoctor_id()+ " Name: " + a.getHospital_doctors().get(i).getName()+" "+a.getHospital_doctors().get(i).getSurname()+" Surname: "+a.getHospital_doctors().get(i).getSpecialty());
            botonPanelDoctors.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DoctorInfoHospial(a.getHospital_doctors().get(l),a);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(botonPanelDoctors);
        scrollPane1.setBounds(49, 332, 1332, 208); // Establecer el tamaño y posición del JScrollPane
        scrollPane1.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane1);
       
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getHospital_visits().size(); i++) {
            JButton boton = new JButton("Visit ID: " + a.getHospital_visits().get(i).getVisit_id() + " /Date: " + a.getHospital_visits().get(i).getVisit_date()+" /Doctor:" + a.getHospital_visits().get(i).getVisit_doctor().getName()+" /Specialty: "+ a.getHospital_visits().get(i).getVisit_doctor().getSpecialty());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getHospital_visits().get(l));
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(botonPanelVisits);
        scrollPane2.setBounds(49, 713, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane2.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane2);
      
       
       JLabel lblHospitalId = new JLabel(a.getHospitalID().toString());
       lblHospitalId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblHospitalId.setBounds(435, 95, 218, 26);
       panel.add(lblHospitalId);
       
        
        setVisible(true);
	}
}