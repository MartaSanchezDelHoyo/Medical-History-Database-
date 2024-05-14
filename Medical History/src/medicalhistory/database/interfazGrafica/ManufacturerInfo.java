package medicalhistory.database.interfazGrafica;

import javax.swing.*;


import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;
public class ManufacturerInfo extends JFrame{
	private JTextField MedicationsField;
	public ManufacturerInfo() {
		setAlwaysOnTop(true);
		setTitle("Manufacturers");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(130, 54, 73, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(488, 54, 67, 24);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<dynamic>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(556, 51, 136, 31);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("<dynamic>");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(166, 51, 136, 31);
		getContentPane().add(lblNewLabel_2_1);
		
		MedicationsField = new JTextField();
		MedicationsField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MedicationsField.setBounds(39, 158, 757, 198);
		getContentPane().add(MedicationsField);
		MedicationsField.setColumns(1);
		
		JLabel lblNewLabel_3 = new JLabel("Medications");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(47, 117, 136, 31);
		getContentPane().add(lblNewLabel_3);
	}
}
