package medicalhistory.database.graphicInterface;

import javax.swing.*;

import medicalhistory.database.pojos.Manufacturer;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;
public class ManufacturerInfo extends JFrame{
	private JTextField MedicationsField;
	public ManufacturerInfo(Manufacturer a) {
		setAlwaysOnTop(true);
		setTitle("Manufacturers");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
		
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1586, 963);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel.setBounds(523, 77, 73, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 24));
		lblNewLabel_1.setBounds(860, 77, 88, 24);
		panel .add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("<dynamic>");
		lblNewLabel_2.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(949, 74, 136, 31);
		panel .add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("<dynamic>");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 24));
		lblNewLabel_2_1.setBounds(559, 74, 136, 31);
		panel .add(lblNewLabel_2_1);
		
		MedicationsField = new JTextField();
		MedicationsField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MedicationsField.setBounds(139, 238, 1226, 198);
		panel .add(MedicationsField);
		MedicationsField.setColumns(1);
		
		JLabel lblNewLabel_3 = new JLabel("Medications");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_3.setBounds(139, 197, 136, 31);
		panel .add(lblNewLabel_3);
		
		setVisible(true);
	}
}
