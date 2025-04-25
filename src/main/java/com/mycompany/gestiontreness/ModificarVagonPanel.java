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

public class ModificarVagonPanel extends JPanel {
    private final JFrame frame;
    private final DefaultListModel<Vagon> listModel;

    public ModificarVagonPanel(JFrame frame) {
        this.frame = frame;
        this.listModel = new DefaultListModel<>();
        cargarVagones();
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        initializeUI();
    }

    private void cargarVagones() {
        listModel.clear();
        GestorVagones.getInstance().getVagones().forEach(listModel::addElement);
    }

    private void initializeUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("MODIFICAR/ELIMINAR VAGONES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backButton = new JButton("← VOLVER");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionVagonesPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Lista de vagones
        JList<Vagon> listaVagones = new JList<>(listModel);
        listaVagones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaVagones.setCellRenderer(new VagonListRenderer());
        JScrollPane scrollPane = new JScrollPane(listaVagones);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(new Color(52, 152, 219));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.addActionListener(e -> modificarVagon(listaVagones));

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(new Color(231, 76, 60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.addActionListener(e -> eliminarVagon(listaVagones));

        buttonPanel.add(btnModificar);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnEliminar);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void modificarVagon(JList<Vagon> listaVagones) {
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

    private void eliminarVagon(JList<Vagon> listaVagones) {
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
            "¿Está seguro de eliminar este vagón?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = GestorVagones.getInstance().eliminarVagon(seleccionado.getIdVagon());
            if (eliminado) {
                listModel.removeElement(seleccionado); // Actualizar modelo
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
                setText(String.format("Vagón %s - Asientos: %d", v.getIdVagon(), v.getTotalAsientos()));
            }
            return this;
        }
    }
}