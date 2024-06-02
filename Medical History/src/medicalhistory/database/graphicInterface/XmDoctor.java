package medicalhistory.database.graphicInterface;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.xml.bind.JAXBException;

import medicalhistory.database.interfaces.XMLManager;
import medicalhistory.database.jdbc.JDBCXMLManager;
import medicalhistory.database.pojos.Doctor;
import medicalhistory.database.pojos.Patient;

public class XmDoctor extends JFrame {

    private JButton botonXML;
    private JPanel panel=new JPanel();
    public static XMLManager xmlMan;
    
    public XmDoctor(Doctor a) {
    	xmlMan=new JDBCXMLManager();
    	
        setTitle("XML patient");
       
        setSize(1600, 1000);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
        
        

	    getContentPane().add(panel);
	    panel.setLayout(null);

                try {
                    // Simulate generating an XML file
                    File file = xmlMan.doctor2Xml(a);

                    JTextArea textArea = new JTextArea();
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setBounds(59, 11, 1492, 684);
                    JButton downloadButton = new JButton("Download XML");
                    downloadButton.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                    downloadButton.setBounds(32, 717, 735, 104);
                    panel.add(downloadButton);
                    panel.add(scrollPane);
                    JButton open = new JButton("Open XML ");
                    open.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
                    open.setBounds(804, 717, 735, 104);
                    panel.add(open);
                    open.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                            	if (Desktop.isDesktopSupported()) {
                                    Desktop.getDesktop().open(file);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Desktop is not supported, cannot open the file.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    // Read the content of the file and display it in the text area
                    FileReader reader = new FileReader(file);
                    textArea.read(reader, "XML File");
                    reader.close();

                    // Action for the download button
                    downloadButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                JFileChooser fileChooser = new JFileChooser();
                                fileChooser.setDialogTitle("Save XML File");
                                int userSelection = fileChooser.showSaveDialog(null);
                                if (userSelection == JFileChooser.APPROVE_OPTION) {
                                    File saveFile = fileChooser.getSelectedFile();
                                    FileWriter writer = new FileWriter(saveFile);
                                    writer.write(textArea.getText());
                                    writer.close();
                                    JOptionPane.showMessageDialog(null, "File saved successfully!");
                                }
                            } catch (IOException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error saving the file.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                    
                } catch (IOException | JAXBException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error generating or reading the XML file.", "Error", JOptionPane.ERROR_MESSAGE);
                }setVisible(true);
            }

}