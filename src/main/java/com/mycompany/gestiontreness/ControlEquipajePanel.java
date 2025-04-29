/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ControlEquipajePanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto, txtIdEquipaje, txtPeso, txtIdVagonCarga;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ControlEquipajePanel(JFrame frame) {
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
        JLabel title = new JLabel("CONTROL DE EQUIPAJE", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();
        txtIdEquipaje = new JTextField("EQ-" + System.currentTimeMillis());
        txtPeso = new JTextField();
        txtIdVagonCarga = new JTextField();

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);
        addFormField(formPanel, "ID Equipaje:", txtIdEquipaje);
        addFormField(formPanel, "Peso (kg):", txtPeso);
        addFormField(formPanel, "ID Vagón Carga:", txtIdVagonCarga);

        JButton btnRegistrar = new JButton("REGISTRAR EQUIPAJE");
        btnRegistrar.setBackground(GOLD_COLOR);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.addActionListener(e -> registrarEquipaje());

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

    private void registrarEquipaje() {
        String idBoleto = txtIdBoleto.getText().trim();
        String idEquipaje = txtIdEquipaje.getText().trim();
        String pesoStr = txtPeso.getText().trim();
        String idVagonCarga = txtIdVagonCarga.getText().trim();

        if (idBoleto.isEmpty() || idEquipaje.isEmpty() || pesoStr.isEmpty() || idVagonCarga.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(pesoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Peso inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar boleto
        Boleto boleto = GestorBoletos.getInstance().buscarBoleto(idBoleto);
        if (boleto == null) {
            JOptionPane.showMessageDialog(frame, "Boleto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar vagón
        Vagon vagon = GestorVagones.getInstance().getVagones().stream()
                .filter(v -> v.getIdVagon().equals(idVagonCarga))
                .findFirst()
                .orElse(null);
        if (vagon == null) {
            JOptionPane.showMessageDialog(frame, "Vagón de carga inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear equipaje
        Equipaje equipaje = new Equipaje(idEquipaje, peso, idVagonCarga);
        boleto.setEquipaje(equipaje); // Nota: Esto no persiste en GestorBoletos, solo en el objeto local
        JOptionPane.showMessageDialog(frame, "Equipaje registrado exitosamente - ID: " + idEquipaje);
        frame.setContentPane(new MenuPasajerosPanel(frame));
        frame.revalidate();
    }
}
