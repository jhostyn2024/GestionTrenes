/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class VentaBoletosPanel extends JPanel {
    private JFrame frame;
    private JComboBox<String> cbRutas;
    private JTextField txtNombre, txtIdentificacion;

    public VentaBoletosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.add(new JLabel("COMPRAR BOLETO", SwingConstants.CENTER));

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        cbRutas = new JComboBox<>(GestorRutas.getInstance().getRutas().stream()
            .map(r -> r.getEstacionOrigen() + " a " + r.getEstacionDestino())
            .toArray(String[]::new));

        txtNombre = new JTextField();
        txtIdentificacion = new JTextField();

        formPanel.add(new JLabel("Ruta:"));
        formPanel.add(cbRutas);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Identificación:"));
        formPanel.add(txtIdentificacion);

        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.addActionListener(e -> comprarBoleto());

        add(header, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);
    }

    private void comprarBoleto() {
        String ruta = (String) cbRutas.getSelectedItem();
        String nombre = txtNombre.getText();
        String id = txtIdentificacion.getText();

        if (nombre.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos");
            return;
        }

        // Crear boleto
        Boleto boleto = new Boleto(
            "TREN-" + System.currentTimeMillis(),
            nombre,
            id,
            50.00 // Precio fijo por simplicidad
        );

        GestorBoletos.getInstance().agregarBoleto(boleto);
        JOptionPane.showMessageDialog(frame, "Boleto comprado exitosamente");
        frame.setContentPane(new MenuPasajerosPanel(frame));
        frame.revalidate();
    }
}
