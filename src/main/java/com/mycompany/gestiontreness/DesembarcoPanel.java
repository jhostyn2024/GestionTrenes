/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class DesembarcoPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public DesembarcoPanel(JFrame frame) {
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
        JLabel title = new JLabel("REGISTRAR DESEMBARCO", SwingConstants.CENTER);
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

        JButton btnRegistrar = new JButton("REGISTRAR DESEMBARCO");
        btnRegistrar.setBackground(GOLD_COLOR);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.addActionListener(e -> registrarDesembarco());

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(btnRegistrar, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void registrarDesembarco() {
        String idBoleto = txtIdBoleto.getText().trim();

        if (idBoleto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese el ID del boleto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = GestorBoletos.getInstance().marcarBoletoUsado(idBoleto);
        if (exito) {
            JOptionPane.showMessageDialog(frame, "Desembarco registrado exitosamente.");
            frame.setContentPane(new MenuPasajerosPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "Boleto no encontrado o ya usado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}