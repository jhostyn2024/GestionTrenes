/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ModificarEliminarRutaPanel extends JPanel {
    private JFrame frame;
    private JList<Ruta> listaRutas;
    private DefaultListModel<Ruta> listModel;

    public ModificarEliminarRutaPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("MODIFICAR/ELIMINAR RUTAS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        System.out.println("Cargando rutas en ModificarEliminarRutaPanel - Total: " + rutas.size());
        rutas.forEach(listModel::addElement);

        listaRutas = new JList<>(listModel);
        listaRutas.setCellRenderer(new RutaListRenderer());
        listaRutas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaRutas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));

        JButton modificarBtn = new JButton("Modificar");
        modificarBtn.setBackground(new Color(52, 152, 219));
        modificarBtn.setForeground(Color.WHITE);
        modificarBtn.addActionListener(this::modificarRuta);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBackground(new Color(231, 76, 60));
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.addActionListener(this::eliminarRuta);

        buttonPanel.add(modificarBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(eliminarBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void modificarRuta(ActionEvent e) {
        Ruta seleccionada = listaRutas.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione una ruta para modificar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        frame.setContentPane(new EditarRutaPanel(frame, seleccionada));
        frame.revalidate();
    }

    private void eliminarRuta(ActionEvent e) {
        Ruta seleccionada = listaRutas.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione una ruta para eliminar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame, 
            "¿Está seguro de eliminar esta ruta? (ID: " + seleccionada.getIdRuta() + 
            ", " + seleccionada.getEstacionOrigen() + " - " + seleccionada.getEstacionDestino() + ")", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Intentando eliminar ruta - ID: " + seleccionada.getIdRuta());
            GestorRutas.getInstance().printRutas(); // Depuración antes
            
            boolean removed = GestorRutas.getInstance().eliminarRuta(seleccionada.getIdRuta());

            GestorRutas.getInstance().printRutas(); // Depuración después
            
            if (removed) {
                listModel.removeElement(seleccionada);
                System.out.println("Ruta eliminada de JList - Total elementos: " + listModel.size());
                JOptionPane.showMessageDialog(frame, 
                    "Ruta eliminada correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Fallo al eliminar ruta de GestorRutas");
                JOptionPane.showMessageDialog(frame, 
                    "Error al eliminar la ruta", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static class RutaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Ruta) {
                Ruta r = (Ruta) value;
                setText(String.format("ID: %s - %s a %s, Distancia: %.2f km, Estado: %s", 
                    r.getIdRuta(), r.getEstacionOrigen(), r.getEstacionDestino(), 
                    r.getDistancia(), r.getEstado()));
            }
            return this;
        }
    }
}