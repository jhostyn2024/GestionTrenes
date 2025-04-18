/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarTrenGUI {
    private TrenManager trenManager;

    public AgregarTrenGUI(TrenManager trenManager) {
        this.trenManager = trenManager;

        // Crear el marco de la GUI para agregar tren
        JFrame frame = new JFrame("Agregar Tren");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBackground(new Color(245, 245, 245)); // Color de fondo

        // Título
        JLabel titleLabel = new JLabel("AGREGAR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y el contenido

        // Campos del formulario
        String[] labels = {"MARCA:", "IDENTIFICADOR:", "CAPACIDAD:", "HORA SALIDA Y LLEGADA:", "VAGONES:", "RUTA:"};
        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JPanel formGroup = new JPanel();
            formGroup.setLayout(new BoxLayout(formGroup, BoxLayout.Y_AXIS));
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            textFields[i] = new JTextField();
            formGroup.add(label);
            formGroup.add(textFields[i]);
            panel.add(formGroup);
            panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre campos
        }

        // Botón de continuar
        JButton btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setBackground(new Color(0, 123, 255)); // Color de fondo
        btnContinuar.setForeground(Color.WHITE); // Color del texto
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setFocusPainted(false); // Quitar el borde de enfoque
        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del tren
                String marca = textFields[0].getText();
                String identificador = textFields[1].getText();
                String capacidad = textFields[2].getText();
                String horaSalidaLlegada = textFields[3].getText();
                                String vagones = textFields[4].getText();
                String ruta = textFields[5].getText();

                // Crear un nuevo tren y agregarlo al gestor
                Tren nuevoTren = new Tren(marca, identificador, capacidad, horaSalidaLlegada, vagones, ruta);
                trenManager.addTren(nuevoTren);

                // Mostrar un mensaje de confirmación
                JOptionPane.showMessageDialog(frame, "Tren agregado exitosamente.");

                // Limpiar los campos
                for (JTextField textField : textFields) {
                    textField.setText("");
                }
            }
        });
        panel.add(btnContinuar);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el botón y el final

        // Agregar el panel al marco
        frame.add(panel);
        frame.setVisible(true); // Hacer visible la ventana
    }
}