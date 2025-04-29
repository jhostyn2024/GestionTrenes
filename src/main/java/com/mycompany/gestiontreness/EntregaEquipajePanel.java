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
    private JTextArea txtResultado;
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
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);

        JButton btnEntregar = new JButton("ENTREGAR");
        btnEntregar.setBackground(GOLD_COLOR);
        btnEntregar.setForeground(Color.WHITE);
        btnEntregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntregar.addActionListener(e -> entregarEquipaje());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnEntregar);
        formPanel.add(btnVolver);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(new JScrollPane(txtResultado), BorderLayout.SOUTH);
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
            txtResultado.setText("Ingrese un ID de boleto");
            return;
        }

        Boleto boleto = GestorBoletos.getInstance().buscarBoletoPorId(idBoleto);
        if (boleto == null) {
            txtResultado.setText("No se encontró boleto con ID: " + idBoleto);
            return;
        }

        if (boleto.getEquipajes().isEmpty()) {
            txtResultado.setText("El boleto no tiene equipaje registrado");
            return;
        }

        StringBuilder resultado = new StringBuilder("Equipaje entregado:\n");
        for (Equipaje equipaje : boleto.getEquipajes()) {
            resultado.append("ID Equipaje: ").append(equipaje.getIdEquipaje()).append("\n");
            resultado.append("Peso: ").append(equipaje.getPeso()).append(" kg\n");
            resultado.append("Vagón de Carga: ").append(equipaje.getIdVagonCarga()).append("\n");
            resultado.append("Pasajero: ").append(boleto.getNombre()).append(" ").append(boleto.getApellido()).append("\n\n");
        }
        txtResultado.setText(resultado.toString());
    }
}