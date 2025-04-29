/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ControlEquipajePanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdEquipaje;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ControlEquipajePanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("CONTROL DE EQUIPAJE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdEquipaje = new JTextField();
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Equipaje:", txtIdEquipaje);

        JButton btnVerificar = new JButton("VERIFICAR");
        btnVerificar.setBackground(GOLD_COLOR);
        btnVerificar.setForeground(Color.WHITE);
        btnVerificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnVerificar.addActionListener(e -> verificarEquipaje());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnVerificar);
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

    private void verificarEquipaje() {
        String idEquipaje = txtIdEquipaje.getText().trim();
        if (idEquipaje.isEmpty()) {
            txtResultado.setText("Ingrese un ID de equipaje");
            return;
        }

        List<Boleto> boletos = GestorBoletos.getInstance().buscarBoletosPorEquipaje(idEquipaje);
        if (boletos.isEmpty()) {
            txtResultado.setText("No se encontró equipaje con ID: " + idEquipaje);
            return;
        }

        StringBuilder resultado = new StringBuilder("Equipaje encontrado:\n");
        for (Boleto boleto : boletos) {
            for (Equipaje equipaje : boleto.getEquipajes()) {
                if (equipaje.getIdEquipaje().equals(idEquipaje)) {
                    resultado.append("ID Equipaje: ").append(equipaje.getIdEquipaje()).append("\n");
                    resultado.append("Peso: ").append(equipaje.getPeso()).append(" kg\n");
                    resultado.append("Vagón de Carga: ").append(equipaje.getIdVagonCarga()).append("\n");
                    resultado.append("Pasajero: ").append(boleto.getNombre()).append(" ").append(boleto.getApellido()).append("\n");
                    resultado.append("Boleto ID: ").append(boleto.getIdBoleto()).append("\n");
                    resultado.append("Estado: ").append(equipaje.getPeso() <= 80 ? "Válido" : "Excede límite de 80 kg").append("\n\n");
                }
            }
            if (boleto.getEquipajes().size() > 2) {
                resultado.append("Advertencia: El pasajero tiene más de 2 maletas\n");
            }
        }
        txtResultado.setText(resultado.toString());
    }
}