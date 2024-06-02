package medicalhistory.databasegraphicInterface;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import medicalhistory.database.interfaces.AllergiesManager;
import medicalhistory.database.interfaces.DoctorManager;
import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.jdbc.JDBCXMLManager;
import medicalhistory.database.jpa.JPAUserManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.User;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class PatientInfoUserChange extends JFrame {
    private JPanel botonPanel;
	private JPanel botonPanelAllergies;
	private JPanel botonPanelVisits;
	private JPanel botonPanelDoctors;
	private static AllergiesManager allergyMan;
	private AbstractButton botonRetorno;
	protected String input;
	protected byte[] photo;
	private ImageIcon imageIcon;
	private static JPAUserManager userMan;
	protected String input2;
	
	private static VisitManager visitMan;
    private static DoctorManager docMan;
    private static PatientManager patientMan;
    
	private static ConnectionManager conMan;
	
	public PatientInfoUserChange(Patient a,User u) {
		 userMan = new JPAUserManager();
		conMan = new ConnectionManager();
		visitMan= conMan.getVisitMan();
		docMan= conMan.getDocMan();
		allergyMan=conMan.getAllergiesMan();
		patientMan=conMan.getPatientMan();
		
		
		a.setAlergies(allergyMan.getAllergies(a.getPatientID()));
        a.setVisits(visitMan.getVisitByPatient(a.getPatientID()));
        a.setDoctors(docMan.getDoctorsByPatient(a.getPatientID()));
        setTitle("Patient Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        
         imageIcon=null;
        if(a.getPhoto()!=null) {
        photo= a.getPhoto();
        imageIcon = new ImageIcon(photo);
        }
        
        JButton changeImage=new JButton("Change photo");
        changeImage.setBounds(1222, 221, 272, 55);
        changeImage.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        panel.add(changeImage);
        
        changeImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null); // Completa la llamada al método showOpenDialog

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                       
                    	 photo = Files.readAllBytes(selectedFile.toPath());
                         imageIcon = new ImageIcon(photo );
                       
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error trying to read the image.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        JLabel lblTextPhoto = new JLabel(imageIcon);
        lblTextPhoto.setBounds(93, 34, 181, 219);
        lblTextPhoto.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        panel.add(lblTextPhoto);
        
        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBloodType.setBounds(899, 34, 137, 33);
        panel.add(lblBloodType);
       
        JLabel lblFullName = new JLabel("Full name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(383, 34, 137, 33);
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblContact.setBounds(383, 84, 137, 33);
        panel.add(lblContact);
        
        JLabel lblBirthDate = new JLabel("Birth date:");
        lblBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBirthDate.setBounds(383, 131, 137, 33);
        panel.add(lblBirthDate);
        
        
        JLabel lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatientId.setBounds(383, 175, 137, 33);
        panel.add(lblPatientId);

        JTextField lblTextfullname = new JTextField(a.getPatientName());
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(534, 34, 312, 33);
        panel.add(lblTextfullname);
        a.setPatientName(lblTextfullname.getText());
        
        JTextField lblTextcontact = new JTextField(a.getEmail());
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(544, 84, 312, 33);
        panel.add(lblTextcontact);
        a.setEmail(lblTextcontact.getText());
        
        JButton changePassword = new JButton("Change password");
        changePassword.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        changePassword.setBounds(297, 221, 286, 55);
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
        
        JButton changeUsername = new JButton("Change Username");
        changeUsername.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        changeUsername.setBounds(597, 221, 286, 55);
        panel.add(changeUsername);
        
        changeUsername.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 try {
    				input = JOptionPane.showInputDialog(null, "Enter the new Username:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

    				if (input != null) {
    					input2 = JOptionPane.showInputDialog(null, "Confirm the new Username:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);
    					userMan.ChangeUser(userMan.getUserByUsername(a.getUsername()),input2,  userMan.getUserByUsername(a.getUsername()).getPassword());
    					if(input.toString().equals(input2.toString())) {
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
        
        JLabel lblAllergies = new JLabel("Allergies:");
        lblAllergies.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblAllergies.setBounds(33, 486, 137, 33);
        panel.add(lblAllergies);
        
        JButton addAllergies = new JButton("Add  new Allergies ");
        addAllergies.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        addAllergies.setBounds(927, 221, 261, 55);
        panel.add(addAllergies);
        addAllergies.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 try {
    				input = JOptionPane.showInputDialog(null, "Enter the name of the new allerg of the patient:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

    				if (input != null) {
    				    JOptionPane.showMessageDialog(null, "Allergy added!", input, JOptionPane.INFORMATION_MESSAGE);
    				} else {
    				    JOptionPane.showMessageDialog(null, "Didn´t enter any allergy.", "Warning", JOptionPane.WARNING_MESSAGE);
    				}
    			} catch (Exception e1) {
    				JOptionPane.showMessageDialog(null,"There was an error adding this allergy", "Warning", JOptionPane.WARNING_MESSAGE);
    				e1.printStackTrace();
    			}
            }
        });
        // Crear un panel para los botones con un layout vertical
        JPanel botonAllergiesPatients = new JPanel();
        botonAllergiesPatients.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getAllergies().size(); i++) {
            try {
				JButton allergy = new JButton("Delete Allergie : ID: " + a.getAllergies().get(i).getAllergiesID() + " Name: " + a.getAllergies().get(i).getAllergiesName());
				botonAllergiesPatients.add(allergy);
				int l=i;
         allergy.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	int response =JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this allergy?", input, JOptionPane.YES_NO_OPTION);
				    	if (response == JOptionPane.YES_OPTION) {
			                a.getAllergies().remove(l);
			                botonPanelAllergies.remove(allergy);
			                panel.revalidate();
			                panel.repaint();
			            }
				    }
				});
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(null, "There was an error deleting this allergy", "Confirmación", JOptionPane.YES_NO_OPTION);
				e.printStackTrace();
			}
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane1 = new JScrollPane(botonAllergiesPatients);
        scrollPane1.setBounds(49, 513, 1332, 140); // Establecer el tamaño y posición del JScrollPane
        scrollPane1.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane1);
     
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(33, 653, 137, 33);
        panel.add(lblVisits);
        
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getVisits().size(); i++) {
            JButton boton = new JButton("Visit ID: " + a.getVisits().get(i).getVisit_id() + " /Date: " + a.getVisits().get(i).getVisit_date()+" /Doctor:" + a.getVisits().get(i).getVisit_doctor().getName()+" /Specialty: "+ a.getVisits().get(i).getVisit_doctor().getSpecialty());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l),u);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane2 = new JScrollPane(botonPanelVisits);
        scrollPane2.setBounds(49, 713, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane2.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane2);
     
      
        JLabel lblDoctors = new JLabel("Doctors:");
        lblDoctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblDoctors.setBounds(33, 269, 137, 33);
        panel.add(lblDoctors);
        
        JPanel botonPanelDoctors = new JPanel();
        botonPanelDoctors.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getDoctors().size(); i++) {
            JButton boton = new JButton("Doctor ID: " + a.getDoctors().get(i).getDoctor_id() + " /Name: " + a.getDoctors().get(i).getDoctor_id()+ " /Specialty:"+ a.getDoctors().get(i).getSpecialty());
            botonPanelDoctors.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DoctorInfoPatient(a.getDoctors().get(l),a);
                }
            });
        }

        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelDoctors);
        scrollPane.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane);
       
       JLabel lblPhoto = new JLabel("Photo:");
       lblPhoto.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       lblPhoto.setBounds(0, 9, 103, 26);
       panel.add(lblPhoto);
       
       JLabel lblTextBirthDate = new JLabel(a.getDateofbirth().toString());
       lblTextBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextBirthDate.setBackground(new Color(255, 255, 224));
       lblTextBirthDate.setBounds(534, 131, 312, 33);
       panel.add(lblTextBirthDate);
       
       
       JLabel lblTextID = new JLabel(a.getPatientID().toString());
       lblTextID.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextID.setBackground(new Color(255, 255, 224));
       lblTextID.setBounds(511, 175, 312, 33);
       panel.add(lblTextID);
       
       JLabel lblTextBloodType = new JLabel(a.getBloodtype());
       lblTextBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
       lblTextBloodType.setBackground(new Color(255, 255, 224));
       lblTextBloodType.setBounds(1027, 34, 312, 33);
       panel.add(lblTextBloodType);
       
       JButton changepatient = new JButton("Change information");
       changepatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       changepatient.setBounds(890, 122, 437, 50);
       panel.add(changepatient);
       
      
       changepatient.addActionListener(new ActionListener() {
       	
           public void actionPerformed(ActionEvent e) {
           	try { int respuesta = JOptionPane.showConfirmDialog(null, "Are You sure you want to change this information in the database?", "Yes", JOptionPane.YES_NO_OPTION);
           	  if (respuesta == JOptionPane.YES_OPTION) {
           	    	patientMan.changePatient(a);
           	    } else if (respuesta == JOptionPane.NO_OPTION) {
           	        System.out.println("El usuario ha seleccionado No.");
           	    }
            }
           catch(Exception ex) {
           	JOptionPane.showInputDialog("there was an error changing the visit", a);;
           }}
       });

       JButton botonXML = new JButton("Obtain Xml with your information until date");
       botonXML.setBounds(893, 873, 644, 90);
       botonXML.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
       panel.add(botonXML);
      
        botonXML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new XmlPatient(a);
            }
            
        }); 
       
       JButton botonRetorno = new JButton("Return");
       botonRetorno.setBounds(10, 888, 249, 64);
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

    