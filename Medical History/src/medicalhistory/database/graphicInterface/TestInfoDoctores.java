package medicalhistory.database.graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import medicalhistory.database.interfaces.PatientManager;
import medicalhistory.database.interfaces.TestManager;
import medicalhistory.database.interfaces.TreatmentManager;
import medicalhistory.database.interfaces.VisitManager;
import medicalhistory.database.jdbc.ConnectionManager;
import medicalhistory.database.pojos.*;

public class TestInfoDoctores extends JFrame {
	 private static ConnectionManager conMan;
	   private static TreatmentManager treatmentMan;
	 private static PatientManager patientMan;
	 private static VisitManager visitMan;
	 private static TestManager testMan;
	private JPanel panel = new JPanel();
	private JPanel botonPanelPatients;

public TestInfoDoctores(Test a, User u) {
		conMan = new ConnectionManager();
		 patientMan=conMan.getPatientMan();
		 testMan=conMan.getTestMan();
		 visitMan=conMan.getVisitMan();
		 
        setTitle("Test Information");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        getContentPane().add(panel);
        panel.setLayout(null);
       
   
        JLabel lblTestId = new JLabel("Test ID:");
        lblTestId.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblTestId.setBounds(454, 119, 103, 26);
        panel.add(lblTestId);
       
        
        JLabel lblType = new JLabel("Type:");
        lblType.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        lblType.setBounds(464, 206, 72, 26);
        panel.add(lblType);
        
        JLabel lblTexttype = new JLabel(a.getType());
        lblTexttype.setBackground(new Color(255, 255, 224));
        lblTexttype.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTexttype.setBounds(564, 187, 452, 64);
        panel.add(lblTexttype);
        
        JLabel lblTextcontact = new JLabel(String.valueOf(a.getTest_id()));
        lblTextcontact.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        lblTextcontact.setBackground(new Color(255, 255, 224));
        lblTextcontact.setBounds(567, 100, 380, 64);
        panel.add(lblTextcontact);

        // Crear un botón de retorno
        JButton botonRetorno = new JButton("Return");
        botonRetorno.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        botonRetorno.setBounds(10, 875, 307, 77);
        panel.add(botonRetorno);
         a.setVisits(visitMan.getVisitByTest(a.getTest_id()));
         if (u.getRole().toString() =="Doctor") {  
        	 // Crear un panel para los botones con un layout vertical
        	 a.setVisits(visitMan.getVisitByTest(a.getTest_id()));
        JPanel botonPanelVisits = new JPanel();
        botonPanelVisits.setLayout(new GridLayout(0, 1)); // Establecer un diseño de cuadrícula de una sola columna

        // Añadir botones al panel
        for (int i = 0; i < a.getVisits().size(); i++) {
            JButton boton = new JButton("Visits ID: " + a.getVisits().get(i).getVisit_id() + " /Name: " +a.getVisits().get(i).getVisit_date()+ " /Specialty:"+ a.getVisits().get(i).getVisit_doctor().getSpecialty());
            botonPanelVisits.add(boton);
            int l = i;
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new VisitInfo(a.getVisits().get(l),u);
                }
            });
        }
         
        // Envuelve el panel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(botonPanelVisits);
        scrollPane.setBounds(49, 313, 1332, 159); // Establecer el tamaño y posición del JScrollPane
        scrollPane.setPreferredSize(new Dimension(700, 300)); 
       panel.add(scrollPane);
         }

       if(a.getArchivoPDF()!=null) {
    	   
           JButton openButton = new JButton("Open Document");
           openButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
           openButton.setSize(682, 102);
           openButton.setLocation(867, 717);
          
           openButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   try {
                	   
                	   File tempFile = File.createTempFile("tempfile", ".txt");
                      

                       try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                           fos.write(a.getArchivoPDF());
                       }
                      
                       Desktop.getDesktop().open(tempFile);
                   } catch (IOException ex) {
                       ex.printStackTrace();
                       JOptionPane.showMessageDialog(TestInfoDoctores.this, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
           });
           panel.add(openButton);     
             
	       JButton downloadButton = new JButton("Download test");
	       downloadButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
	       downloadButton.setBounds(32, 717, 735, 104);
	       panel.add(downloadButton);
       // Action for the download button
       downloadButton.addActionListener(new ActionListener() {
           @Override
               public void actionPerformed(ActionEvent e) {
                   try {
                       // Supongamos que "documentBytes" contiene los bytes del documento
                       byte[] documentBytes = a.getArchivoPDF();

                       // Crear un archivo temporal
                       File tempFile = File.createTempFile("temp-document", ".pdf");
                       // Escribir los bytes en el archivo temporal
                       try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                           fos.write(documentBytes);
                       }

                       // Mostrar mensaje de descarga exitosa
                       JOptionPane.showMessageDialog(TestInfoDoctores.this, "Documento descargado exitosamente.", "Success", JOptionPane.INFORMATION_MESSAGE);
                   } catch (IOException ex) {
                       ex.printStackTrace();
                       JOptionPane.showMessageDialog(TestInfoDoctores.this, "Error al descargar el documento.", "Error", JOptionPane.ERROR_MESSAGE);
                   }
               }
           });
           
    	  

        botonRetorno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
    	
 }
         setVisible(true);
 }
}
