package medicalhistory.database.interfazGrafica;

import javax.swing.*;

import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;
import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class MedicationInfo extends JFrame{
	
	public MedicationInfo(Medication m) {
		
		setTitle("MedicationInfo");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        
        getContentPane().add(panel);
        panel.setLayout(null);
       
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(538, 95, 73, 24);
		panel .add(lblNewLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel(String.valueOf(m.getMedication_id()));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2_1.setBounds(574, 92, 136, 31);
		panel .add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Type:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setBounds(801, 69, 221, 76);
		panel .add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(m.getType());
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(982, 86, 1110, 43);
		panel .add(lblNewLabel_2);
		
		JPanel botonPanelManufacturers = new JPanel();
        botonPanelManufacturers.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < m.getManufacturers().size(); i++) {
            JButton boton = new JButton("Manufacturer ID: " + m.getManufacturers().get(i).getManufacturerID() + " /Name: " + m.getManufacturers().get(i).getManufacturerName() );
            botonPanelManufacturers.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new  ManufacturerInfo (m.getManufacturers().get(l));
                    
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelManufacturers);
        scrollPane.setBounds(49, 313, 1332, 361); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); 
        panel .add(scrollPane, BorderLayout.NORTH);
        
        JLabel lblMedication = new JLabel("Manufacturers:");
        lblMedication.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblMedication.setBounds(49, 271, 159, 31);
        panel.add(lblMedication);
        
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setBounds(10, 909, 221, 43);
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(botonRetorno);
       
      
         botonRetorno.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 dispose(); // Cierra la ventana actual
             }
         });
        
        setVisible(true);
	}
	

}
