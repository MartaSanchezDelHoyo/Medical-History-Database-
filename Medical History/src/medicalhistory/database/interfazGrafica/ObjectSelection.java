package medicalhistory.database.interfazGrafica;

import javax.swing.*;

import medicalhistory.database.pojos.Doctor;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectSelection extends JFrame {
    private List< Object> objects;
    private Object selectedObject;

    public ObjectSelection(List<Object> objects) {
        this.objects = objects;
        setTitle("Selección de Doctores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear un panel para los botones
        JPanel panelBotones = new JPanel(new GridLayout(objects.size(), 1));

        // Crear un botón por cada doctor
        for (Object object : objects) {
            JButton botonDoctor = new JButton(object.toString());
            botonDoctor.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Obtener el doctor seleccionado
                    selectedObject = object;
                    // Cerrar la ventana
                    dispose();
                }
            });
            panelBotones.add(botonDoctor);
        }

        // Agregar el panel de botones a la ventana
        getContentPane().add(panelBotones);
        
        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    public Object getDoctorSeleccionado() {
        return selectedObject;
    }
}