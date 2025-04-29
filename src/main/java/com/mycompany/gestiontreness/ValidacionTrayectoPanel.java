/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ValidacionTrayectoPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto, txtIdTren, txtIdRuta, txtIdHorario;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ValidacionTrayectoPanel(JFrame frame) {
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
        JLabel title = new JLabel("VALIDAR BOLETO EN TRAYECTO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();
        txtIdTren = new JTextField();
        txtIdRuta = new JTextField();
        txtIdHorario = new JTextField();

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);
        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "ID Horario:", txtIdHorario);

        JButton btnValidar = new JButton("VALIDAR BOLETO");
        btnValidar.setBackground(GOLD_COLOR);
        btnValidar.setForeground(Color.WHITE);
        btnValidar.setFont(new Font("Arial", Font.BOLD, 16));
        btnValidar.addActionListener(e -> validarBoleto());

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(btnValidar, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void validarBoleto() {
        String idBoleto = txtIdBoleto.getText().trim();
        String idTren = txtIdTren.getText().trim();
        String idRuta = txtIdRuta.getText().trim();
        String idHorario = txtIdHorario.getText().trim();

        if (idBoleto.isEmpty() || idTren.isEmpty() || idRuta.isEmpty() || idHorario.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean valido = GestorBoletos.getInstance().validarBoleto(idBoleto, idTren, idRuta, idHorario);
        if (valido) {
            JOptionPane.showMessageDialog(frame, "Boleto válido para el trayecto.");
            frame.setContentPane(new MenuPasajerosPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "Boleto inválido o ya usado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}