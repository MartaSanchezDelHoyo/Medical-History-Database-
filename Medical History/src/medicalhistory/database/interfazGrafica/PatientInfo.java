package medicalhistory.database.interfazGrafica;

import javax.swing.*;

import medicalhistory.database.pojos.Patient;
import medicalhistory.database.pojos.Visit;

import java.awt.*;
import java.awt.event.*;

public class PatientInfo extends JFrame {
    private JPanel botonPanel;
	private Container panelBotonesMedication;

	public PatientInfo(Patient a) {
        setTitle("Patient Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        
      
        //JLabel labelImagen = new JLabel(a.get);

        // Añadir el JLabel al contenido de la ventana
       // getContentPane().add(labelImagen, BorderLayout.SOUTH);

        getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblBloodType = new JLabel("Blood type:");
        lblBloodType.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBloodType.setBounds(686, 34, 137, 33);
        panel.add(lblBloodType);
        
        JLabel lblAllergies = new JLabel("Allergies:");
        lblAllergies.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblAllergies.setBounds(686, 84, 137, 33);
        panel.add(lblAllergies);
        
        JLabel lblFullName = new JLabel("Full name:");
        lblFullName.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblFullName.setBounds(226, 34, 137, 33);
        panel.add(lblFullName);
        
        JLabel lblContact = new JLabel("Contact:");
        lblContact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblContact.setBounds(226, 84, 137, 33);
        panel.add(lblContact);
        
        JLabel lblBirthDate = new JLabel("Birth date:");
        lblBirthDate.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblBirthDate.setBounds(226, 128, 137, 33);
        panel.add(lblBirthDate);
        
        JLabel lblSex = new JLabel("Sex:");
        lblSex.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblSex.setBounds(686, 140, 137, 33);
        panel.add(lblSex);
        
        JLabel lblPatientId = new JLabel("Patient ID:");
        lblPatientId.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblPatientId.setBounds(226, 170, 137, 33);
        panel.add(lblPatientId);
        
        JLabel lblDoctors = new JLabel("Doctors:");
        lblDoctors.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblDoctors.setBounds(33, 330, 137, 33);
        panel.add(lblDoctors);
        
        JLabel lblVisits = new JLabel("Visits:");
        lblVisits.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblVisits.setBounds(33, 653, 137, 33);
        panel.add(lblVisits);
        
        JLabel lblTextfullname = new JLabel("textFullName:");
        lblTextfullname.setBackground(new Color(255, 255, 224));
        lblTextfullname.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextfullname.setBounds(340, 34, 312, 33);
        panel.add(lblTextfullname);
        
        JLabel lblTextcontact = new JLabel("textContact:");
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(340, 84, 312, 33);
        panel.add(lblTextcontact);
        
        JPanel principalPanel = new JPanel();
        principalPanel.setBounds(33, 374, 1543, 213);
        panel.add(principalPanel);
        principalPanel.setLayout(new BorderLayout());

        // Crear un panel para los botones con un layout vertical
       botonPanel = new JPanel();
        botonPanel.setLayout(new BoxLayout(botonPanel, BoxLayout.Y_AXIS));

        // Agregar el panel de botones a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanel);
        principalPanel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel principalPanel_1 = new JPanel();
        principalPanel_1.setBounds(33, 681, 1543, 213);
        panel.add(principalPanel_1);
        principalPanel_1.setLayout(new BorderLayout());

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(0, 908, 137, 44);
        panel.add(botonRetorno);
        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        setVisible(true);
    }
    // Método para agregar un botón al panel de botones
	private void addBotonTreatments(Visit a) {
        for (int i = 1; i <= a.getTreatments().size(); i++) {
            JButton boton = new JButton("Treatment:" + a.getTreatments().get(i).toString());
            panelBotonesMedication.add(boton);
            boton.addActionListener(new ActionListener() {
            	
            public void actionPerformed(ActionEvent e) {
            //open new treatment parameter
        panelBotonesMedication.revalidate();
        panelBotonesMedication.repaint();
    }
            });
        }

        // Refrescar el panel para que los cambios se muestren correctamente
	panelBotonesMedication.revalidate();
	panelBotonesMedication.repaint();
    }
}
    