package medicalhistory.database.interfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Registation extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;

    public Registation() {
        setTitle("Registro de Usuario");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();

        JLabel labelUsuario = new JLabel("Nombre de usuario:");
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        labelUsuario.setBounds(27, 315, 497, 64);
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        campoUsuario.setBounds(534, 318, 544, 64);
        
        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setHorizontalAlignment(SwingConstants.CENTER);
        labelContraseña.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 23));
        labelContraseña.setSize(487, 64);
        labelContraseña.setLocation(51, 413);
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setFont(new Font("Tw Cen MT", Font.BOLD, 23));
        labelUsuario.setBounds(41, 338, 497, 64);
        campoContraseña = new JPasswordField();
        campoContraseña.setFont(new Font("Tw Cen MT", Font.PLAIN, 23));
        campoContraseña.setBounds(534, 413, 544, 64);

        JButton botonRegistrar = new JButton("Log in ");
        botonRegistrar.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 29));
        botonRegistrar.setBounds(702, 603, 211, 64);
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String contraseña = new String(campoContraseña.getPassword());
                // agregar la lógica para procesar el registro
                JOptionPane.showMessageDialog(Registation.this, "Usuario registrado:\nUsuario: " + usuario + "\nContraseña: " + contraseña);
            }
        });
        panel.setLayout(null);

        panel.add(labelUsuario);
        panel.add(campoUsuario);
        panel.add(labelContraseña);
        panel.add(campoContraseña);
        panel.add(botonRegistrar);

        getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Welcome to the Medical History Databse");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 40));
        lblNewLabel.setBounds(255, 73, 917, 99);
        panel.add(lblNewLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Registation();
            }
        });
    }

	public JTextField getCampoUsuario() {
		return campoUsuario;
	}

	public void setCampoUsuario(JTextField campoUsuario) {
		this.campoUsuario = campoUsuario;
	}

	public JPasswordField getCampoContraseña() {
		return campoContraseña;
	}

	public void setCampoContraseña(JPasswordField campoContraseña) {
		this.campoContraseña = campoContraseña;
	}
    
}