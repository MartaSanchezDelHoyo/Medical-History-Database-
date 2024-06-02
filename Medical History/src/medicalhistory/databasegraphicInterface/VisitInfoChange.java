package medicalhistory.databasegraphicInterface;
import com.toedter.calendar.JCalendar;
import medicalhistory.database.interfaces.MedicationManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.Properties;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import medicalhistory.database.pojos.User;
import medicalhistory.database.pojos.Visit;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.*;
public class VisitInfoChange extends JFrame {
	private JPanel panel = new JPanel();
	
	private JLabel DoctorText;
	Date calendar1;
	private static String input;
	private static int response=0;
	private static ConnectionManager conMan;
	 private static MedicationManager medicationMan;
	 private static TreatmentManager treatmentMan;
	 private static VisitManager visitMan;
	
public  VisitInfoChange (Visit a) {	
	conMan = new ConnectionManager();
	medicationMan=conMan.getMedicationMan();
	treatmentMan= conMan.getTreatmentMan();
	visitMan=conMan.getVisitMan();
	
	setTitle("Visit Change Information");
    setSize(1600, 1000);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla

    getContentPane().add(panel);
    panel.setLayout(null);
    panel.setLayout(null);

    JLabel lblVisitId = new JLabel("Visit ID:");
    lblVisitId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblVisitId.setBounds(182, 37, 103, 35);
    panel.add(lblVisitId);
   
    JLabel lblObserbations = new JLabel("Observations:");
    lblObserbations.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblObserbations.setBounds(32, 690, 171, 35);
    panel.add(lblObserbations);
    
    JLabel lblTextObserbations =new JLabel (a.getVisit_observation());
    lblTextObserbations.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextObserbations.setBounds(32, 736, 1354, 143);
    panel.add(lblTextObserbations);
    
    JLabel lblDate = new JLabel("Date:");
    lblDate.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDate.setBounds(184, 107, 108, 35);
    panel.add(lblDate);
    
    JLabel lblTextVisitID = new JLabel(String.valueOf(a.getVisit_id()));
    lblTextVisitID.setBackground(new Color(255, 255, 224));
    lblTextVisitID.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblTextVisitID.setBounds(339, 46, 221, 26);
    panel.add(lblTextVisitID);
    
    JButton buttonCalendar = new JButton("Select date :");
    buttonCalendar.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    buttonCalendar.setBounds(271, 97, 253, 54);
    panel.add(buttonCalendar);
    
   buttonCalendar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	try {
				Calendar calendar =new Calendar();
				calendar1=(Date) calendar.getCalendar().getDate();
				a.setVisit_date(calendar1);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"There was an error adding changing the date", "Warning", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
        }	
   });
        
   
    // Crear un botón de retorno
    JButton botonRetorno = new JButton("Return");
    botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    botonRetorno.setBounds(10, 913, 163, 50);
    panel.add(botonRetorno);
 
    JLabel lblDoctor = new JLabel("Doctor:");
    lblDoctor.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblDoctor.setBounds(584, 46, 103, 26);
    panel.add(lblDoctor);
    
    JLabel lblHospital = new JLabel("Hospital :");
    lblHospital.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblHospital.setBounds(584, 95, 103, 26);
    panel.add(lblHospital);
    
    JLabel lblPatient = new JLabel("Patient :");
    lblPatient.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblPatient.setBounds(584, 144, 103, 26);
    panel.add(lblPatient);
    
    JLabel lblMedications = new JLabel("Medications:");
    lblMedications.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblMedications.setBounds(32, 291, 171, 35);
    panel.add(lblMedications);
    
    a.setMedications(medicationMan.showMedications(a.getVisit_id()));
    
    JButton addTMedications = new JButton("Add  new Medication ");
    addTMedications.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    addTMedications.setBounds(595, 221, 430, 55);
    panel.add(addTMedications);
    addTMedications.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	 try {
				input = JOptionPane.showInputDialog(null, "Enter the name of the new medication for the visit:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

				if (input != null) {
				    JOptionPane.showMessageDialog(null, "Medication added!", input, JOptionPane.INFORMATION_MESSAGE);
				} else {
				    JOptionPane.showMessageDialog(null, "Didn´t enter any treatment.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"There was an error adding this medication", "Warning", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
        }
    });
    JPanel botonPanelMaications = new JPanel();
    botonPanelMaications.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

    if(a.getMedications()!=null) {
    // Añadir botones al panel
	    for (int i = 0; i < a.getMedications().size(); i++) {
	        JButton boton = new JButton("Delete medication: ID: " + a.getMedications().get(i).getMedication_id() + " /Type: " + a.getMedications().get(i).getType()+" /Manufacturer:"+a.getMedications().get(i).getManufacturers());
	        botonPanelMaications.add(boton);
	        int l = i;
	        boton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	try {
	            	int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this treatment?", "Confirmación", JOptionPane.YES_NO_OPTION);

	                // Verificar la respuesta del usuario
	                if (response == JOptionPane.YES_OPTION) {
	                a.getMedications().remove(l);
	                botonPanelMaications.remove(boton);
	                panel.revalidate();
	                panel.repaint();
	            }
	        }catch(Exception ex) {
	        	 JOptionPane.showConfirmDialog(null, "There was an error deleting this treatment", "Confirmación", JOptionPane.YES_NO_OPTION);
	        }
	            }
	        });
    }

    // Envuelve el panel en un JScrollPane
    JScrollPane scrollPane = new JScrollPane(botonPanelMaications);
    scrollPane.setBounds(49, 319, 1332, 153); // Establecer el tamaño y posición del JScrollPane
    scrollPane.setPreferredSize(new Dimension(700, 300)); 
    panel.add(scrollPane);
	}else {
		JLabel medication=new JLabel("NO medications in this visit");
		botonPanelMaications.add(medication);
	    panel.add(botonPanelMaications);
	}
    
    a.setTreatments(treatmentMan.getTreatments(a.getVisit_id()));
    
    JButton addTreatments = new JButton("Add new Treatments");
    addTreatments.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    addTreatments.setBounds(1107, 219, 398, 63);
    panel.add(addTreatments);
    addTreatments.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	try {
        	 input = JOptionPane.showInputDialog(null, "Enter the name of the new tratment for the visit:", "Entrada de Datos", JOptionPane.QUESTION_MESSAGE);

            if (input != null) {
                JOptionPane.showMessageDialog(null, "Visit added!", input, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Didn´t enter any treatment.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }catch(Exception ex) {
        	JOptionPane.showMessageDialog(null,"There was an error adding this treatment", "Warning", JOptionPane.WARNING_MESSAGE);
        
        }
        }
    });
    
    JLabel lblTreatments = new JLabel("Treatments");
    lblTreatments.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    lblTreatments.setBounds(32, 470, 124, 26);
    panel.add(lblTreatments);

    JPanel botonPanelTreatmentd = new JPanel();
    botonPanelTreatmentd.setLayout(new GridLayout(0, 1)); 
  
	if(a.getTreatments()!=null) {
   // Establecer un diseño de cuadrícula de una sola columna
    for (int i = 0; i < a.getTreatments().size(); i++) {
        JButton treatment = new JButton("Delete treatment: ID: " + a.getTreatments().get(i).getTreatmentID() + " /Type: " + a.getTreatments().get(i).getTreatmentType());
        botonPanelTreatmentd.add(treatment);
        int l = i;
        treatment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this treatment?", "Confirmación", JOptionPane.YES_NO_OPTION);

					// Verificar la respuesta del usuario
					if (response == JOptionPane.YES_OPTION) {
					    // Eliminar el botón del panel
						  a.getTreatments().remove(l);
					      botonPanelTreatmentd.remove(treatment);
					      panel.revalidate();
					      panel.repaint();
					      }
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"There was an error deleting this treatment", "Warning", JOptionPane.WARNING_MESSAGE);
					ex.printStackTrace();
				}
              
            }
        });
    }
    JScrollPane scrollPane2 = new JScrollPane(botonPanelTreatmentd);
    scrollPane2.setBounds(49, 512, 1332, 158); // Establecer el tamaño y posición del JScrollPane
    scrollPane2.setPreferredSize(new Dimension(700, 300)); 
    panel.add(scrollPane2);
    }else {
    	JLabel treatment=new JLabel("NO Treatments in this visit");
    botonPanelTreatmentd.add(treatment);
    panel.add(botonPanelTreatmentd);}

    // Envuelve el panel en un JScrollPane
    
    
    DoctorText = new JLabel(a.getVisit_doctor().getName()+" "+a.getVisit_doctor().getSurname());
    DoctorText.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    DoctorText.setBounds(700, 46, 253, 26);
    panel.add(DoctorText);
    
    
    JLabel lblHospitalName = new JLabel(a.getHospital().getHospitalName());
    lblHospitalName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblHospitalName.setBounds(697, 97, 689, 23);
    panel.add(lblHospitalName);
    
    JLabel lblPatientName = new JLabel(a.getVisit_patient().getPatientName());
    lblPatientName.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
    lblPatientName.setBounds(697, 147, 351, 20);
    
    panel.add(lblPatientName);
    
    JButton deleteVisit = new JButton("Delete this visit");
    deleteVisit.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    deleteVisit.setBounds(71, 226, 437, 50);
    panel.add(deleteVisit);
    
   
    deleteVisit.addActionListener(new ActionListener() {
    	
        public void actionPerformed(ActionEvent e) {
        	try { int respuesta = JOptionPane.showConfirmDialog(null, "Are You sure you want to delete this visit from the database?", "Yes", JOptionPane.YES_NO_OPTION);
        	  if (respuesta == JOptionPane.YES_OPTION) {
        	    	visitMan.deleteVisit(a);
        	    	response=1;
        	    	dispose();
        	    	panel.revalidate();
                    panel.repaint();
        	    } else if (respuesta == JOptionPane.NO_OPTION) {
        	        System.out.println("El usuario ha seleccionado No.");
        	    }
         }
        catch(Exception ex) {
        	JOptionPane.showMessageDialog(panel, "there was an error deleting the visit"+ ex.getMessage());;
        }}
    });
    
    JButton changeVisit = new JButton("Change this visit");
    changeVisit.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
    changeVisit.setBounds(71, 226, 437, 50);
    panel.add(changeVisit);
    
   
    changeVisit.addActionListener(new ActionListener() {
    	
        public void actionPerformed(ActionEvent e) {
        	try { int respuesta = JOptionPane.showConfirmDialog(null, "Are You sure you want to change this visit in the database?", "Yes", JOptionPane.YES_NO_OPTION);
        	  if (respuesta == JOptionPane.YES_OPTION) {
        	    	visitMan.changeVisit(a);
        	    } else if (respuesta == JOptionPane.NO_OPTION) {
        	        System.out.println("El usuario ha seleccionado No.");
        	    }
         }
        catch(Exception ex) {
        	JOptionPane.showInputDialog("there was an error changing the visit", a);;
        }}
    });

    botonRetorno.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose(); 
        }
    });
    
    setVisible(true);
   
	}

public static int getResponse() {
	return response;
}



}
