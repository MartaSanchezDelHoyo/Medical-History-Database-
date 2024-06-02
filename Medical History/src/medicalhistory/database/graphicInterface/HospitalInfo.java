package medicalhistory.databasegraphicInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.bind.JAXBException;

import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.UserManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.jpa.JPAUserManager;
import medicalhistory.database.pojos.*;


public class HospitalInfo extends JFrame{
	JPanel panel = new JPanel();

	protected String input;

	protected String input2;

    private static DoctorManager docMan;
    private static PatientManager patientMan;
    private static VisitManager visitMan;
    private static UserManager userMan;
    private static XMLManager xmlMan;
    private static ConnectionManager conMan;
    
public HospitalInfo(Hospital a, User u ) {
		conMan = new ConnectionManager();
		visitMan=conMan.getVisitMan();
		docMan= conMan.getDocMan();
		userMan=new JPAUserManager();
		xmlMan=conMan.getXMLMan();
		patientMan= conMan.getPatientMan();
		
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
        lblFullName.setBounds(243, 46, 103, 63);
        panel.add(lblFullName);
        
        JLabel Address = new JLabel("Address:");
        Address.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        Address.setBounds(818, 46, 116, 63);
        panel.add(Address);
        
        JLabel HospitalId = new JLabel("Hospital ID:");
        HospitalId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        HospitalId.setBounds(240, 130, 185, 72);
        panel.add(HospitalId);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(16, 601, 52, 26);
        panel.add(lblVisits);
        
        JLabel Doctors = new JLabel("Doctors:");
        Doctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        Doctors.setBounds(16, 212, 89, 26);
        panel.add(Doctors);
        
        JLabel lblTextfullname = new JLabel(a.getHospitalName());
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(356, 46, 414, 63);
        panel.add(lblTextfullname);
        
        JLabel lblTextaddress = new JLabel(a.getHospitalAddress());
        lblTextaddress.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextaddress.setBackground(new Color(255, 255, 224));
        lblTextaddress.setBounds(939, 46, 577, 63);
        panel.add(lblTextaddress);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 889, 278, 63);
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
                    new DoctorInfoHospial(a.getHospital_doctors().get(l),a,u);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(botonPanelDoctors);
        scrollPane1.setBounds(49, 240, 1332, 300); // Establecer el tamaño y posición del JScrollPane
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
                    new VisitInfo(a.getHospital_visits().get(l),u);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(botonPanelVisits);
        scrollPane2.setBounds(49, 631, 1332, 241); // Establecer el tamaño y posición del JScrollPane
        scrollPane2.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane2);
      
       
       JLabel lblHospitalId = new JLabel(a.getHospitalID().toString());
       lblHospitalId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblHospitalId.setBounds(435, 135, 286, 63);
       panel.add(lblHospitalId);
       
       JButton btnImportDoctor = new JButton("Import new doctor from Xml ");
       btnImportDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       btnImportDoctor.setBounds(992, 880, 539, 80);
       panel.add(btnImportDoctor);
       
       btnImportDoctor.addActionListener(new ActionListener() {

      		public void actionPerformed(ActionEvent e) {
      			JFileChooser fileChooser = new JFileChooser();
      	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

      	        int returnValue = fileChooser.showOpenDialog(btnImportDoctor);
      	        if (returnValue == JFileChooser.APPROVE_OPTION) {
      	            File selectedFile = fileChooser.getSelectedFile();
      	            Doctor doctor;
					try {
						
						doctor = xmlMan.xml2Doctor(selectedFile);
						 new DoctorInfoHospial(doctor,a,u);
						 if (docMan.getDoctor(doctor.getDoctor_id())!=null) {
						 JOptionPane.showMessageDialog(null,"Doctor added succesfully! ", "Message", JOptionPane.INFORMATION_MESSAGE);
						 }else {
							 JOptionPane.showMessageDialog(null,"The doctor can´t be added to the database, please check if the information is correct! ", "Message", JOptionPane.WARNING_MESSAGE);
						 }
					} catch (JAXBException  e1) {
						JOptionPane.showMessageDialog(null,"This document does not follow the needed requirements, please select another document", "Warning", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
					}
      	                 	        }
      		}
          });
       
       JButton btnImportPatient = new JButton("Import new patient from Xml ");
       btnImportPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       btnImportPatient.setBounds(406, 880, 539, 80);
       panel.add(btnImportPatient);
       
       btnImportPatient.addActionListener(new ActionListener() {

   		public void actionPerformed(ActionEvent e) {
   			JFileChooser fileChooser = new JFileChooser();
   	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

   	        int returnValue = fileChooser.showOpenDialog(btnImportPatient);
   	        if (returnValue == JFileChooser.APPROVE_OPTION) {
   	            File selectedFile = fileChooser.getSelectedFile();
   	            Patient patient;
				try {
					patient = xmlMan.xml2Patient(selectedFile);
					new PatientInfoHospital(patient,u);
					if (patientMan.getPatient(patient.getPatientID())!=null) {
						 JOptionPane.showMessageDialog(null,"Patient added succesfully! ", "Message", JOptionPane.INFORMATION_MESSAGE);
						 }else {
							 JOptionPane.showMessageDialog(null,"The patient can´t be added to the database, please check if the information is correct! ", "Message", JOptionPane.WARNING_MESSAGE);
						 }
				} catch (JAXBException e1) {
					JOptionPane.showMessageDialog(null,"This document does not follow the needed requirements, please select another document", "Warning", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
   	            
   	        }
   		}
       });
       
       JButton changeUsername = new JButton("Change Username");
       changeUsername.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       changeUsername.setBounds(775, 118, 286, 55);
       panel.add(changeUsername);
       
       changeUsername.addActionListener(new ActionListener() {
          

		public void actionPerformed(ActionEvent e) {
           	 try {
   				input = JOptionPane.showInputDialog(null, "Enter the new Username:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

   				if (input != null) {
   					input2 = JOptionPane.showInputDialog(null, "Confirm the new Username:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);
   					
   					if(input.toString().equals(input2.toString())) {
   						userMan.ChangeUser(userMan.getUserByUsername(a.getUsername()),input2,  userMan.getUserByUsername(a.getUsername()).getPassword());
   						JOptionPane.showMessageDialog(null, "Username changed successfully.", "Message", JOptionPane.WARNING_MESSAGE);
   					}else {
   						JOptionPane.showMessageDialog(null, "The username confirmation failed", "Warning", JOptionPane.WARNING_MESSAGE);
   					}
   				} else {
   				    JOptionPane.showMessageDialog(null, "Didn´t enter any new userame.", "Warning", JOptionPane.WARNING_MESSAGE);
   				}
   			} catch (Exception e1) {
   				JOptionPane.showMessageDialog(null,"There was an error changing this username, please try again", "Warning", JOptionPane.WARNING_MESSAGE);
   				e1.printStackTrace();
   			}
           }
       });
       
       JButton changePassword = new JButton("Change password");
       changePassword.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       changePassword.setBounds(1195, 118, 286, 55);
       panel.add(changePassword);
       
       changePassword.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           	 try {
   				input = JOptionPane.showInputDialog(null, "Enter the new password:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

   				if (input != null) {
   					input2 = JOptionPane.showInputDialog(null, "Confirm the new password:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);
   					if(input.toString().equals(input2.toString())) {
   						userMan.ChangeUser(userMan.getUserByUsername(a.getUsername()), a.getUsername(), input2);
   						JOptionPane.showMessageDialog(null, "Password changed successfully.", "Message", JOptionPane.WARNING_MESSAGE);
   					}else {
   						JOptionPane.showMessageDialog(null, "The password confirmation failed", "Warning", JOptionPane.WARNING_MESSAGE);
   					}
   				} else {
   				    JOptionPane.showMessageDialog(null, "Didn´t enter any new password.", "Warning", JOptionPane.WARNING_MESSAGE);
   				}
   			} catch (Exception e1) {
   				JOptionPane.showMessageDialog(null,"There was an error changing this password, please try again", "Warning", JOptionPane.WARNING_MESSAGE);
   				e1.printStackTrace();
   			}
           }
       });
        
        setVisible(true);
	}
}