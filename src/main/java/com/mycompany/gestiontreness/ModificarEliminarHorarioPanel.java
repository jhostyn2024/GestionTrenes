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

public class ModificarEliminarHorarioPanel extends JPanel {
    private final JFrame frame;

    public ModificarEliminarHorarioPanel(JFrame frame) {
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
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        });

        JLabel title = new JLabel("ELIMINAR O MODIFICAR HORARIOS", SwingConstants.CENTER);
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

        List<Horario> horarios = GestorHorarios.getInstance().getHorarios();

        if (horarios.isEmpty()) {
            JLabel emptyLabel = new JLabel("No hay horarios registrados", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            contentPanel.add(emptyLabel);
        } else {
            for (Horario horario : horarios) {
                contentPanel.add(createHorarioEntry(horario));
                contentPanel.add(Box.createVerticalStrut(15));
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHorarioEntry(Horario horario) {
        JPanel entryPanel = new JPanel(new BorderLayout());
        entryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        entryPanel.setBackground(new Color(250, 250, 250));
        entryPanel.setMaximumSize(new Dimension(700, 100));

        // Panel de información
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1, 5, 5));
        infoPanel.setBackground(new Color(250, 250, 250));

        addInfoField(infoPanel, "ID RUTA: " + horario.getIdRuta());
        addInfoField(infoPanel, "HORA DE SALIDA: " + horario.getHoraSalida());
        addInfoField(infoPanel, "HORA DE LLEGADA: " + horario.getHoraLlegada());
        addInfoField(infoPanel, "DÍAS: " + horario.getDiasSemana());

        entryPanel.add(infoPanel, BorderLayout.CENTER);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(250, 250, 250));

        JButton deleteButton = createActionButton("❌", new Color(231, 76, 60));
        deleteButton.addActionListener(e -> confirmarEliminacion(horario));

        JButton editButton = createActionButton("✏️", new Color(41, 128, 185));
        editButton.addActionListener(e -> abrirEditorHorario(horario));

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        entryPanel.add(buttonPanel, BorderLayout.EAST);

        return entryPanel;
    }

    private void addInfoField(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label);
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

    private void confirmarEliminacion(Horario horario) {
        int confirm = JOptionPane.showConfirmDialog(
            frame,
            "¿Está seguro de eliminar el horario de la ruta " + horario.getIdRuta() + "?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            GestorHorarios.getInstance().getHorarios().remove(horario);
            JOptionPane.showMessageDialog(
                frame,
                "Horario eliminado correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
            );
            refreshPanel();
        }
    }

    private void abrirEditorHorario(Horario horario) {
        frame.setContentPane(new EditarHorarioPanel(frame, horario));
        frame.revalidate();
    }

    private void refreshPanel() {
        frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
        frame.revalidate();
    }
}