package medicalhistory.database.interfazGrafica;

import javax.swing.*;

import medicalhistory.database.pojos.Medication;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;
import java.awt.BorderLayout;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;

public class MedicationInfo extends JFrame{
	private JTextField textField;
	public MedicationInfo(Medication m) {
		getContentPane().setForeground(new Color(255, 255, 255));
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 24));
		setBackground(new Color(255, 255, 255));
		setTitle("MedicationInfo");
		getContentPane().setLayout(null);
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(538, 95, 73, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2_1 = new JLabel("<dynamic>");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2_1.setBounds(574, 92, 136, 31);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setBounds(879, 95, 84, 24);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<dynamic>");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(964, 92, 136, 31);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(1);
		textField.setBounds(104, 232, 1333, 198);
		getContentPane().add(textField);
		
		JLabel lblNewLabel_3 = new JLabel("Manufacturer");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_3.setBounds(104, 191, 177, 31);
		getContentPane().add(lblNewLabel_3);
	}
	

}
