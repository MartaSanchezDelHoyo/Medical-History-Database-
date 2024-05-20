package medicalhistory.database.interfazGrafica;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.*;


public class NewVisit extends JFrame{
	private JPanel panel = new JPanel();
	private static HospitalManager hospitalMan;
    private static ConnectionManager conMan;
    private static VisitManager visitMan;
    Hospital selectedOption;
    
	public NewVisit (Doctor a,Patient b) {
		conMan = new ConnectionManager();
		 hospitalMan=conMan.getHospitalMan();
		  visitMan=conMan.getVisitMan();
		 
		setTitle("Visit Information");
	    setSize(1600, 1000);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	
	    getContentPane().add(panel);
	    panel.setLayout(null);
	    panel.setLayout(null);
	;
	    JLabel lblDate = new JLabel("Date:");
	    lblDate.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblDate.setBounds(213, 95, 72, 26);
	    panel.add(lblDate);
	 
	    JTextField lblTextDate = new JTextField();
	    lblTextDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
	    lblTextDate.setBackground(new Color(255, 255, 224));
	    lblTextDate.setBounds(320, 95, 107, 26);
	    panel.add(lblTextDate);
	
	    // Crear un botón de retorno
	   
	 
	    JLabel lblDoctor = new JLabel("Doctor:");
	    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblDoctor.setBounds(584, 46, 103, 26);
	    panel.add(lblDoctor);
	    
	    JLabel lblTextfullname = new JLabel(a.getName()+" "+a.getSurname());
        lblTextfullname.setBounds(579, 46, 221, 26);
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextfullname);
	    
	    JLabel lblHospital = new JLabel("Hospital :");
	    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblHospital.setBounds(584, 95, 103, 26);
	    panel.add(lblHospital);
	    
	    openSmallWindow(hospitalMan.getHospitalByDoctor(a.getDoctor_id()));	    
	    JLabel lblPatient = new JLabel("Patient :");
	    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblPatient.setBounds(584, 144, 103, 26);
	    panel.add(lblPatient);
	    
	    JLabel lblTextPatientname = new JLabel(b.getPatientName());
        lblTextPatientname.setBackground(new Color(255, 255, 224));
        lblTextPatientname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextPatientname.setBounds(534, 34, 312, 33);
        panel.add(lblTextPatientname);
	    
	     JButton botonRetorno = new JButton("Return");
	    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    botonRetorno.setBounds(10, 917, 95, 35);
	    panel.add(botonRetorno);
	    
	    visitMan.addVisit(new Visit(Date.valueOf(lblTextDate.getText()), "planned visit ", b, a, null, selectedOption));
	    setVisible(true);
	}
	private void openSmallWindow(List<Hospital> options) {
        JDialog smallWindow = new JDialog(this, "Select your hospital", true);
        smallWindow.setSize(300, 200);
        smallWindow.setLayout(new FlowLayout());

        // Crear botones de radio
        
        // Agrupar los botones de radio para que solo se pueda seleccionar uno
        ButtonGroup group = new ButtonGroup();
        
       
        
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cerrar la ventana
            }
        });
         
        for (Hospital option : options) {
        	
            JRadioButton radioButton = new JRadioButton(option.getHospitalName()+" Id:"+option.getHospitalID());
            radioButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	Pattern patron = Pattern.compile("\\b\\d+\\b");
                    Matcher matcher = patron.matcher(radioButton.getText());
                    int numero=0;
                   selectedOption=null;
                    // Buscar el primer número en la cadena
                    if (matcher.find()) {
                        String numeroTexto = matcher.group();
                         numero = Integer.parseInt(numeroTexto);}
                    selectedOption = hospitalMan.getHospital(numero);
                    
                		}
                });
            group.add(radioButton);
           smallWindow.add(radioButton);
        }
        

        // Agregar componentes a la ventana pequeña
        
        smallWindow.add(confirmButton);

        smallWindow.setLocationRelativeTo(this); // Centrar la ventana pequeña respecto a la ventana principal
        smallWindow.setVisible(true);
    }
}