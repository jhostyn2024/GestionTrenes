/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ModificarVagonPanel extends JPanel {
    private JFrame frame;
    private JList<Vagon> listaVagones;
    private DefaultListModel<Vagon> listModel;

    public ModificarVagonPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("MODIFICAR/ELIMINAR VAGONES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        List<Vagon> vagones = GestorVagones.getInstance().getVagones();
        vagones.forEach(listModel::addElement);

        listaVagones = new JList<>(listModel);
        listaVagones.setCellRenderer(new VagonListRenderer());
        listaVagones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaVagones);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));
        
        JButton modificarBtn = new JButton("Modificar");
        modificarBtn.setBackground(new Color(52, 152, 219));
        modificarBtn.setForeground(Color.WHITE);
        modificarBtn.addActionListener(this::modificarVagon);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBackground(new Color(231, 76, 60));
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.addActionListener(this::eliminarVagon);

        buttonPanel.add(modificarBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(eliminarBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void modificarVagon(ActionEvent e) {
        Vagon seleccionado = listaVagones.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione un vagón para modificar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        frame.setContentPane(new EditarVagonPanel(frame, seleccionado));
        frame.revalidate();
    }

    private void eliminarVagon(ActionEvent e) {
        Vagon seleccionado = listaVagones.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione un vagón para eliminar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame, 
            "¿Está seguro de eliminar este vagón? (ID: " + seleccionado.getIdVagon() + ")", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Intentando eliminar vagón - ID: " + seleccionado.getIdVagon());
            GestorVagones.getInstance().printVagones(); // Depuración antes
            
            boolean removed = GestorVagones.getInstance().eliminarVagon(seleccionado.getIdVagon());

            GestorVagones.getInstance().printVagones(); // Depuración después
            
            if (removed) {
                listModel.removeElement(seleccionado);
                JOptionPane.showMessageDialog(frame, 
                    "Vagón eliminado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, 
                    "Error al eliminar el vagón", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static class VagonListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Vagon) {
                Vagon v = (Vagon) value;
                setText(String.format("ID: %s - Vagones: %d (Equipaje: %d) - Asientos: %d (E: %d, Ej: %d, P: %d)",
                    v.getIdVagon(), v.getTotalVagones(), v.getVagonesConEquipaje(),
                    v.getTotalAsientos(), v.getAsientosEstandar(),
                    v.getAsientosEjecutivo(), v.getAsientosPremium()));
            }
            return this;
        }
    }
}