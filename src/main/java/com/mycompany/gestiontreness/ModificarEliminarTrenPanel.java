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

public class ModificarEliminarTrenPanel extends JPanel {
    private JFrame frame;
    private List<Tren> trenes;

    public ModificarEliminarTrenPanel(JFrame frame) {
        this.frame = frame;
        this.trenes = GestorTrenes.getInstance().getTrenes();
        setLayout(new BorderLayout());
        setBackground(new Color(247, 249, 252));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("ELIMINAR O MODIFICAR TRENES", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        if (trenes.isEmpty()) {
            JLabel emptyLabel = new JLabel("No hay trenes registrados");
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(emptyLabel);
        } else {
            for (Tren tren : trenes) {
                mainPanel.add(createTrenCard(tren));
                mainPanel.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // Footer with back button
        JPanel footer = new JPanel();
        footer.setBackground(new Color(247, 249, 252));
        JButton backButton = new JButton("Volver a Gestión de Trenes");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });
        footer.add(backButton);
        add(footer, BorderLayout.SOUTH);
    }

    private JPanel createTrenCard(Tren tren) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(0, 86, 179));
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        card.setMaximumSize(new Dimension(750, 100));

        // Info panel
        JPanel infoPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        infoPanel.setBackground(new Color(0, 86, 179));

        addInfoField(infoPanel, "Marca:", tren.getMarca());
        addInfoField(infoPanel, "ID:", tren.getIdentificador());
        addInfoField(infoPanel, "Capacidad:", tren.getCapacidad() + " pasajeros");
        addInfoField(infoPanel, "Salida:", tren.getHoraSalida());
        addInfoField(infoPanel, "Llegada:", tren.getHoraLlegada());
        addInfoField(infoPanel, "Vagones:", String.valueOf(tren.getVagones()));
        addInfoField(infoPanel, "Ruta:", tren.getRuta()); 

        card.add(infoPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(0, 86, 179));

        JButton deleteBtn = createActionButton("❌", Color.RED);
        deleteBtn.addActionListener(e -> confirmarEliminacion(tren));

        JButton editBtn = createActionButton("✏️", Color.YELLOW);
        editBtn.addActionListener(e -> abrirEditorTren(tren));

        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }

    private void addInfoField(JPanel panel, String label, String value) {
        JLabel field = new JLabel("<html><b>" + label + "</b> " + value + "</html>");
        field.setForeground(Color.WHITE);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(field);
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setForeground(color);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void confirmarEliminacion(Tren tren) {
        int option = JOptionPane.showConfirmDialog(
            frame, 
            "¿Está seguro de eliminar el tren " + tren.getIdentificador() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION) {
            boolean eliminado = GestorTrenes.getInstance().eliminarTren(tren.getIdentificador());
            if (eliminado) {
                JOptionPane.showMessageDialog(
                    frame, 
                    "Tren eliminado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                refreshPanel();
            } else {
                JOptionPane.showMessageDialog(
                    frame, 
                    "Error al eliminar el tren", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void abrirEditorTren(Tren tren) {
        frame.setContentPane(new EditarTrenPanel(frame, tren));
        frame.revalidate();
    }

    private void refreshPanel() {
        frame.setContentPane(new ModificarEliminarTrenPanel(frame));
        frame.revalidate();
    }
}