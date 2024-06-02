package medicalhistory.database.graphicInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import medicalhistory.database.interfaces.HospitalManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.*;
import com.toedter.calendar.JCalendar;

public class NewVisit extends JFrame{
	private JPanel panel = new JPanel();
	private JCalendar calendar;
	private java.sql.Date sqlDate;
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
	
	    JLabel lblDate = new JLabel("Date:");
	    lblDate.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblDate.setBounds(166, 240, 85, 40);
	    panel.add(lblDate);
	    
	    
	 
	    JButton buttonCalendar = new JButton("Select date :");
	    buttonCalendar.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    buttonCalendar.setBounds(168, 60, 210, 45);
	    panel.add(buttonCalendar);
	    
	    

        buttonCalendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	JPanel panel1 = new JPanel();
                    getContentPane().add(panel1);

                    // Crear el calendario
                    calendar = new JCalendar();
                    panel1.add(calendar);
                    // Obtener la fecha seleccionada del calendario
                    java.util.Date selectedDate = calendar.getDate();
                    sqlDate = new java.sql.Date(selectedDate.getTime());

                    // Aquí puedes usar sqlDate para tus propósitos
                    JOptionPane.showMessageDialog(panel1, "Selected Date: " + sqlDate.toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "There was an error selecting this date", "Warning", JOptionPane.WARNING_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });
	 if(sqlDate!=null) {
        JLabel txtDate = new JLabel("  ");
	    txtDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
	    txtDate.setBounds(283, 234, 418, 53);
	    panel.add(txtDate);
	 }else {
		 JLabel txtDate = new JLabel(sqlDate.toString());
		    txtDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
		    txtDate.setBounds(283, 234, 418, 53);
		    panel.add(txtDate);
	 }
	    JLabel lblDoctor = new JLabel("Doctor:");
	    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblDoctor.setBounds(168, 349, 161, 61);
	    panel.add(lblDoctor);
	    
	    JLabel lblTextfullname = new JLabel(a.getName()+" "+a.getSurname());
        lblTextfullname.setBounds(259, 353, 440, 53);
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextfullname);
	    JLabel lblPatient = new JLabel("Patient :");
	    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblPatient.setBounds(709, 349, 151, 61);
	    panel.add(lblPatient);
	    
	    JLabel lblHospital = new JLabel("Hospital :");
	    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    lblHospital.setBounds(709, 165, 161, 61);
	    panel.add(lblHospital);
	    
	    JLabel txtHospital = new JLabel(selectedOption.getHospitalName());
	    txtHospital.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
	    txtHospital.setBounds(947, 153, 566, 75);
	    panel.add(txtHospital);
	    
	    openSmallWindow(hospitalMan.getHospitalByDoctor(a.getDoctor_id()));	    
	    
	    JLabel lblTextPatientname = new JLabel(b.getPatientName());
        lblTextPatientname.setBackground(new Color(255, 255, 224));
        lblTextPatientname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextPatientname.setBounds(899, 342, 498, 75);
        panel.add(lblTextPatientname);
	    
	     JButton botonRetorno = new JButton("Return");
	    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    botonRetorno.setBounds(10, 917, 192, 35);
	    panel.add(botonRetorno);
	    
	    
	     JButton botonCreation = new JButton("Create visit");
	    botonCreation.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	    botonCreation.setBounds(1033, 743, 508, 177);
	    panel.add(botonCreation);
	    botonCreation.addActionListener(new ActionListener() {
	    

			@Override
            public void actionPerformed(ActionEvent e)  {
	    		try {
	    		 visitMan.addVisit(new Visit(sqlDate, "planned visit ", b, a, null, selectedOption));
	    	}catch(Exception ex){
	    		JOptionPane.showMessageDialog(null, "Please enter valid information for a visit."+ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    		}
	    });
	    
	   
	    setVisible(true);
	}
	private void openSmallWindow(List<Hospital> options) {
        JDialog smallWindow = new JDialog(this, "Select your hospital", true);
        smallWindow.setSize(300, 200);
        smallWindow.getContentPane().setLayout(new FlowLayout());

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
           smallWindow.getContentPane().add(radioButton);
        }
        

        // Agregar componentes a la ventana pequeña
        
        smallWindow.getContentPane().add(confirmButton);

        smallWindow.setLocationRelativeTo(this); // Centrar la ventana pequeña respecto a la ventana principal
        smallWindow.setVisible(true);
    }
}