package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.*;

public class TestInfoDoctores extends JFrame {
	 private static ConnectionManager conMan;
	   private static TreatmentManager treatmentMan;
	 private static PatientManager patientMan;
	 private static VisitManager visitMan;
	 private static TestManager testMan;
	private JPanel panel = new JPanel();
	private JPanel botonPanelPatients;

public TestInfoDoctores(Test a) {
		conMan = new ConnectionManager();
		 patientMan=conMan.getPatientMan();
		 testMan=conMan.getTestMan();
		 visitMan=conMan.getVisitMan();
		 
        setTitle("Test Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setLayout(null);
   
        JLabel lblTestId = new JLabel("Test ID:");
        lblTestId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblTestId.setBounds(464, 46, 103, 26);
        panel.add(lblTestId);
       
        
        JLabel lblType = new JLabel("Type:");
        lblType.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblType.setBounds(464, 110, 72, 26);
        panel.add(lblType);
        
        JLabel lblTexttype = new JLabel(a.getType());
        lblTexttype.setBackground(new Color(255, 255, 224));
        lblTexttype.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTexttype.setBounds(579, 46, 221, 26);
        panel.add(lblTexttype);
        
        JLabel lblTextcontact = new JLabel(String.valueOf(a.getTest_id()));
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(579, 110, 107, 26);
        panel.add(lblTextcontact);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 917, 95, 35);
        panel.add(botonRetorno);
        
                // Crear un panel para los botones con un layout vertical
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getVisits().size(); i++) {
            JButton boton = new JButton("Visits ID: " + a.getVisits().get(i).getVisit_id() + " /Name: " +a.getVisits().get(i).getVisit_date()+ " /Specialty:"+ a.getVisits().get(i).getVisit_doctor().getSpecialty());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l));
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelVisits);
        scrollPane.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane);
       
     
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
	}

}
