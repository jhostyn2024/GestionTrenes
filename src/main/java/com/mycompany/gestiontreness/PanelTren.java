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
import java.util.List;

public class PanelTren extends JPanel {
    public PanelTren(Tren tren, List<Tren> lista, JPanel contenedor, Runnable refrescar) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBackground(new Color(0x003366));
        setPreferredSize(new Dimension(750, 80));
        setMaximumSize(getPreferredSize());

        // Datos
        JLabel label = new JLabel("<html><font color='white'>MARCA: " + tren.getMarca() +
                "<br>IDENTIFICADOR: " + tren.getIdentificador() +
                "<br>CAPACIDAD: " + tren.getCapacidad() + " PASAJEROS</font></html>");
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setIcon(new ImageIcon("train_icon.png"));

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botones.setOpaque(false);
        JButton btnEliminar = new JButton("🚫");
        JButton btnEditar = new JButton("✏️");

        // Acción eliminar
        btnEliminar.addActionListener((ActionEvent e) -> {
            lista.remove(tren);
            TrenService.guardarTrenes(lista);
            refrescar.run();
        });

        // Acción editar
        btnEditar.addActionListener((ActionEvent e) -> {
            String nuevaMarca = JOptionPane.showInputDialog("Nueva marca:", tren.getMarca());
            if (nuevaMarca != null) tren.setMarca(nuevaMarca);

            String nuevoId = JOptionPane.showInputDialog("Nuevo identificador:", tren.getIdentificador());
            if (nuevoId != null) tren.setIdentificador(nuevoId);

            String nuevaCapacidad = JOptionPane.showInputDialog("Nueva capacidad:", tren.getCapacidad());
            if (nuevaCapacidad != null) {
                try {
                    tren.setCapacidad(Integer.parseInt(nuevaCapacidad));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Capacidad no válida");
                }
            }

            TrenService.guardarTrenes(lista);
            refrescar.run();
        });

        botones.add(btnEditar);
        botones.add(btnEliminar);

        add(label, BorderLayout.CENTER);
        add(botones, BorderLayout.EAST);
    }
}

