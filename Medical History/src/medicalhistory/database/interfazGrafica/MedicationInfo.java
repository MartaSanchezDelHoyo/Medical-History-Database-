package medicalhistory.database.interfazGrafica;

import javax.swing.*;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;
import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;

public class MedicationInfo extends JFrame{
	private JTextField textField;
	public MedicationInfo() {
		setBackground(new Color(255, 255, 255));
		setTitle("MedicationInfo");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(138, 51, 73, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("<dynamic>");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(174, 48, 136, 31);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(496, 51, 67, 24);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<dynamic>");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(564, 48, 136, 31);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(1);
		textField.setBounds(34, 141, 757, 198);
		getContentPane().add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("Manufacturer");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(42, 100, 136, 31);
		getContentPane().add(lblNewLabel_3);
	}
	

}
