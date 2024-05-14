package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import medicalhistory.database.pojos.*;

public class TestInfo extends JFrame {
	private JPanel panel = new JPanel();
	private JPanel botonPanelPatients;

public TestInfo(Test a) {
		
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
