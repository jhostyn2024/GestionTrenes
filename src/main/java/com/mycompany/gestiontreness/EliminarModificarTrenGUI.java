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
import java.util.List;

public class EliminarModificarTrenGUI {
    private TrenManager trenManager;

    public EliminarModificarTrenGUI(TrenManager trenManager) {
        this.trenManager = trenManager;

        // Crear el marco de la GUI para eliminar o modificar tren
        JFrame frame = new JFrame("Eliminar o Modificar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel para el contenido
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Layout vertical
        panel.setBackground(new Color(244, 244, 244)); // Color de fondo

        // Título
        JLabel titleLabel = new JLabel("< ELIMINAR O MODIFICAR >");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre el título y el contenido

        // Obtener la lista de trenes
        List<Tren> trenes = trenManager.getTrenes();
        for (Tren tren : trenes) {
            JPanel trenPanel = new JPanel();
            trenPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            trenPanel.setBackground(Color.WHITE);
            trenPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            // Mostrar información del tren
            trenPanel.add(new JLabel("MARCA: " + tren.getMarca()));
            trenPanel.add(new JLabel("IDENTIFICADOR: " + tren.getIdentificador()));
            trenPanel.add(new JLabel("CAPACIDAD: " + tren.getCapacidad() + " PASAJEROS"));

            // Botones para eliminar y modificar
            JButton btnEliminar = new JButton("Eliminar");
            JButton btnModificar = new JButton("Modificar");

            btnEliminar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    trenManager.removeTren(tren);
                    JOptionPane.showMessageDialog(frame, "Tren eliminado exitosamente.");
                    refreshTrenList(); // Actualizar la lista de trenes
                }
            });

            btnModificar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí puedes implementar la lógica para modificar el tren
                    JOptionPane.showMessageDialog(frame, "Modificar tren: " + tren.getIdentificador());
                }
            });

            // Agregar botones al panel del tren
            trenPanel.add(btnEliminar);
            trenPanel.add(btnModificar);
            panel.add(trenPanel);
        }

        // Agregar el panel al marco
        JScrollPane scrollPane = new JScrollPane(panel);
        frame.add(scrollPane);
        frame.setVisible(true); // Hacer visible la ventana
    }

    private void refreshTrenList() {
        // Aquí puedes implementar la lógica para refrescar la lista de trenes
        trenManager = new TrenManager(); // Recargar trenes
        new EliminarModificarTrenGUI(trenManager); // Reabrir la ventana
    }
}