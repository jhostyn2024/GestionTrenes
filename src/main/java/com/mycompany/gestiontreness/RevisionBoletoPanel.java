/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class RevisionBoletoPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public RevisionBoletoPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("REVISIÓN DE BOLETO (ABORDAJE)", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);

        JButton btnRevisar = new JButton("REVISAR");
        btnRevisar.setBackground(GOLD_COLOR);
        btnRevisar.setForeground(Color.WHITE);
        btnRevisar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRevisar.addActionListener(e -> revisarBoleto());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde RevisionBoletoPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnRevisar);
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

    private void revisarBoleto() {
        try {
            String idBoleto = txtIdBoleto.getText().trim();
            if (idBoleto.isEmpty()) {
                txtResultado.setText("Error: Ingrese el ID del boleto.");
                System.out.println("ID de boleto vacío en revisarBoleto");
                return;
            }

            GestorBoletos gestor = GestorBoletos.getInstance();
            Boleto boleto = gestor.getBoletos().stream()
                    .filter(b -> b.getIdBoleto().equals(idBoleto))
                    .findFirst()
                    .orElse(null);

            if (boleto == null) {
                txtResultado.setText("Error: No se encontró un boleto con ID: " + idBoleto);
                System.out.println("Boleto no encontrado: " + idBoleto);
                return;
            }

            if (!boleto.getEstado().equals("Pendiente")) {
                txtResultado.setText("Error: El boleto ya ha sido abordado o validado.\nEstado actual: " + boleto.getEstado());
                System.out.println("Boleto no está en estado Pendiente: " + idBoleto);
                return;
            }

            boleto.setEstado("Abordado");
            gestor.modificarBoleto(boleto.getIdBoleto(), boleto.getIdHorario(), boleto.getIdVagon(), boleto.getCedula(), boleto.getNombre(), boleto.getApellido(), "Abordado");
            txtResultado.setText("Boleto revisado exitosamente:\nID: " + idBoleto + "\nPasajero: " + boleto.getNombre() + " " + boleto.getApellido() + "\nEstado: Abordado");
            System.out.println("Boleto abordado: " + idBoleto);
            txtIdBoleto.setText("");
        } catch (Exception e) {
            txtResultado.setText("Error al revisar boleto: " + e.getMessage());
            System.err.println("Excepción en revisarBoleto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}