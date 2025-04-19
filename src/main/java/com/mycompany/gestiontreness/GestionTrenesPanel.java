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
import java.util.List;

public class GestionTrenesPanel extends JPanel {
    public GestionTrenesPanel(JFrame frame) {
        setLayout(new BorderLayout());
        createHeader(frame);
        createButtonsPanel(frame);
    }

    private void createHeader(JFrame frame) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 58, 138));
        
        JLabel title = new JLabel("GESTIÓN DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        
        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> frame.setContentPane(new GestionGeneralPanel(frame)));
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createButtonsPanel(JFrame frame) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBackground(new Color(244, 244, 244));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        String[] options = {"AGREGAR TREN", "VER LISTA DE TRENES", "MODIFICAR TREN", "ELIMINAR TREN"};
        for (String text : options) {
            JButton button = createMenuButton(text);
            button.addActionListener(e -> handleButtonAction(frame, text));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createVerticalStrut(20));
        }

        add(buttonsPanel, BorderLayout.CENTER);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(205, 163, 74));
        button.setForeground(Color.WHITE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 50));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }

    private void handleButtonAction(JFrame frame, String action) {
        switch (action) {
            case "AGREGAR TREN":
                frame.setContentPane(new AgregarTrenPanel(frame));
                break;
            case "VER LISTA DE TRENES":
                mostrarListaTrenes(frame);
                break;
            case "MODIFICAR TREN":
                // Implementar modificación
                JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "ELIMINAR TREN":
                // Implementar eliminación
                JOptionPane.showMessageDialog(frame, "Funcionalidad en desarrollo", "Información", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
        frame.revalidate();
    }

    private void mostrarListaTrenes(JFrame frame) {
        JPanel listaPanel = new JPanel(new BorderLayout());
        listaPanel.setBackground(Color.WHITE);
        
        // Cabecera
        JPanel header = new JPanel();
        header.setBackground(new Color(30, 58, 138));
        JLabel title = new JLabel("LISTA DE TRENES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        // Cuerpo con lista
        JTextArea listaText = new JTextArea();
        listaText.setEditable(false);
        listaText.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        List<Tren> trenes = GestorTrenes.getInstance().getTrenes();
        if (trenes.isEmpty()) {
            listaText.append("No hay trenes registrados.\n");
        } else {
            listaText.append(String.format("%-10s %-15s %-10s %-12s %-12s %-8s %-15s\n", 
                "ID", "Marca", "Capacidad", "Salida", "Llegada", "Vagones", "Ruta"));
            listaText.append("-----------------------------------------------------------------\n");
            
            for (Tren tren : trenes) {
                listaText.append(String.format("%-10s %-15s %-10d %-12s %-12s %-8d %-15s\n", 
                    tren.getIdentificador(),
                    tren.getMarca(),
                    tren.getCapacidad(),
                    tren.getHoraSalida(),
                    tren.getHoraLlegada(),
                    tren.getVagones(),
                    tren.getRuta()));
            }
        }
        
        // Botón de regreso
        JButton backBtn = new JButton("Volver");
        backBtn.setBackground(new Color(205, 163, 74));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> {
            frame.setContentPane(new GestionTrenesPanel(frame));
            frame.revalidate();
        });
        
        listaPanel.add(header, BorderLayout.NORTH);
        listaPanel.add(new JScrollPane(listaText), BorderLayout.CENTER);
        listaPanel.add(backBtn, BorderLayout.SOUTH);
        
        frame.setContentPane(listaPanel);
        frame.revalidate();
    }
}


