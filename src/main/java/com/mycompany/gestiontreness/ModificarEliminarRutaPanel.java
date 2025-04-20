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

public class ModificarEliminarRutaPanel extends JPanel {
    private final JFrame frame;

    public ModificarEliminarRutaPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));

        JButton backButton = new JButton("← VOLVER");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });

        JLabel title = new JLabel("ELIMINAR O MODIFICAR RUTAS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Ruta> rutas = GestorRutas.getInstance().getRutas();

        if (rutas.isEmpty()) {
            JLabel emptyLabel = new JLabel("No hay rutas registradas", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            contentPanel.add(emptyLabel);
        } else {
            for (Ruta ruta : rutas) {
                contentPanel.add(createRutaCard(ruta));
                contentPanel.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createRutaCard(Ruta ruta) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        card.setBackground(new Color(250, 250, 250));
        card.setMaximumSize(new Dimension(700, 120));

        // Panel de información
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        infoPanel.setBackground(new Color(250, 250, 250));

        addInfoField(infoPanel, "ID Ruta:", ruta.getIdRuta());
        addInfoField(infoPanel, "Origen:", ruta.getEstacionOrigen());
        addInfoField(infoPanel, "Destino:", ruta.getEstacionDestino());
        addInfoField(infoPanel, "Distancia:", ruta.getDistancia() + " km");
        addInfoField(infoPanel, "Estado:", ruta.getEstado());

        card.add(infoPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton deleteButton = createActionButton("❌", new Color(231, 76, 60));
        deleteButton.addActionListener(e -> confirmarEliminacion(ruta));

        JButton editButton = createActionButton("✏️", new Color(41, 128, 185));
        editButton.addActionListener(e -> abrirEditorRuta(ruta));

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }

    private void addInfoField(JPanel panel, String label, String value) {
        JLabel lbl = new JLabel("<html><b>" + label + "</b> " + value + "</html>");
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lbl);
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

    private void confirmarEliminacion(Ruta ruta) {
        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "¿Está seguro de eliminar la ruta " + ruta.getIdRuta() + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            GestorRutas.getInstance().getRutas().remove(ruta);
            JOptionPane.showMessageDialog(
                frame,
                "Ruta eliminada correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            refreshPanel();
        }
    }

    private void abrirEditorRuta(Ruta ruta) {
        frame.setContentPane(new EditarRutaPanel(frame, ruta));
        frame.revalidate();
    }

    private void refreshPanel() {
        frame.setContentPane(new ModificarEliminarRutaPanel(frame));
        frame.revalidate();
    }
}