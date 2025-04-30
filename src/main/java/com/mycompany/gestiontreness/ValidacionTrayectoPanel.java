/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ValidacionTrayectoPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdBoleto;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ValidacionTrayectoPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("VALIDACIÓN DE BOLETO (TRAYECTO)", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdBoleto = new JTextField();
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Boleto:", txtIdBoleto);

        JButton btnValidar = new JButton("VERIFICAR");
        btnValidar.setBackground(GOLD_COLOR);
        btnValidar.setForeground(Color.WHITE);
        btnValidar.setFont(new Font("Arial", Font.BOLD, 16));
        btnValidar.addActionListener(e -> {
            System.out.println("Botón VERIFICAR presionado en ValidacionTrayectoPanel");
            validarBoleto();
        });

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde ValidacionTrayectoPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnValidar);
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

    private void validarBoleto() {
        String idBoleto = txtIdBoleto.getText().trim().toUpperCase();
        System.out.println("Validando boleto con ID: " + idBoleto);
        if (idBoleto.isEmpty()) {
            txtResultado.setText("Ingrese un ID de boleto");
            System.out.println("ID de boleto vacío");
            return;
        }

        boolean valido = GestorBoletos.getInstance().validarBoleto(idBoleto);
        Boleto boleto = GestorBoletos.getInstance().buscarBoletoPorId(idBoleto);

        if (boleto == null) {
            txtResultado.setText("No se encontró boleto con ID: " + idBoleto + 
                "\nAsegúrese de ingresar el ID exactamente como se mostró al comprar");
            System.out.println("Boleto no encontrado: " + idBoleto);
            return;
        }

        StringBuilder resultado = new StringBuilder("Resultado de validación:\n");
        resultado.append("Boleto ID: ").append(boleto.getIdBoleto()).append("\n");
        resultado.append("Pasajero: ").append(boleto.getNombre()).append(" ").append(boleto.getApellido()).append("\n");
        resultado.append("Categoría: ").append(boleto.getCategoriaPasajero()).append("\n");
        resultado.append("Estado: ").append(valido ? "Válido en trayecto" : "No válido (usado o fecha inválida)").append("\n");
        txtResultado.setText(resultado.toString());
        txtResultado.repaint();
        System.out.println("Validación completada: " + (valido ? "Válido" : "No válido"));
    }
}