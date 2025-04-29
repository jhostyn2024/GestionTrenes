/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class EntregaEquipajePanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public EntregaEquipajePanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("ENTREGA DE EQUIPAJE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);

        JButton btnEntregar = new JButton("ENTREGAR EQUIPAJE");
        btnEntregar.setBackground(GOLD_COLOR);
        btnEntregar.setForeground(Color.WHITE);
        btnEntregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntregar.addActionListener(e -> entregarEquipaje());

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(btnEntregar, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void entregarEquipaje() {
        String idBoleto = txtIdBoleto.getText().trim();

        if (idBoleto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese el ID del boleto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Boleto boleto = GestorBoletos.getInstance().buscarBoleto(idBoleto);
        if (boleto == null) {
            JOptionPane.showMessageDialog(frame, "Boleto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Equipaje equipaje = boleto.getEquipaje();
        if (equipaje == null) {
            JOptionPane.showMessageDialog(frame, "No hay equipaje asociado al boleto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(frame, "Equipaje entregado - ID: " + equipaje.getIdEquipaje() +
                "\nPeso: " + equipaje.getPeso() + " kg\nVagón: " + equipaje.getIdVagonCarga());
        frame.setContentPane(new MenuPasajerosPanel(frame));
        frame.revalidate();
    }
}