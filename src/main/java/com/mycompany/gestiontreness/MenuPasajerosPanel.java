/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class MenuPasajerosPanel extends JPanel {
    private JFrame frame;
    private final Color GOLD_COLOR = new Color(198, 168, 77);
    private final Color BLUE_COLOR = new Color(0, 86, 179);

    public MenuPasajerosPanel(JFrame frame) {
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
        JLabel title = new JLabel("MEDINET - PASAJEROS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Botones
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 150, 40, 150));
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton btnComprarBoleto = createMenuButton("COMPRAR BOLETO");
        btnComprarBoleto.addActionListener(e -> {
            frame.setContentPane(new VentaBoletosPanel(frame));
            frame.revalidate();
        });

        JButton btnRevisarBoleto = createMenuButton("REVISAR BOLETO PARA ABORDAR");
        btnRevisarBoleto.addActionListener(e -> {
            frame.setContentPane(new RevisionBoletoPanel(frame));
            frame.revalidate();
        });

        JButton btnControlEquipaje = createMenuButton("CONTROL DE EQUIPAJE");
        btnControlEquipaje.addActionListener(e -> {
            frame.setContentPane(new ControlEquipajePanel(frame));
            frame.revalidate();
        });

        JButton btnValidarTrayecto = createMenuButton("VALIDAR BOLETO EN TRAYECTO");
        btnValidarTrayecto.addActionListener(e -> {
            frame.setContentPane(new ValidacionTrayectoPanel(frame));
            frame.revalidate();
        });

        JButton btnDesembarco = createMenuButton("REGISTRAR DESEMBARCO");
        btnDesembarco.addActionListener(e -> {
            frame.setContentPane(new DesembarcoPanel(frame));
            frame.revalidate();
        });

        JButton btnEntregaEquipaje = createMenuButton("ENTREGA DE EQUIPAJE");
        btnEntregaEquipaje.addActionListener(e -> {
            frame.setContentPane(new EntregaEquipajePanel(frame));
            frame.revalidate();
        });

        JButton btnVerRutas = createMenuButton("VER RUTAS DISPONIBLES");
        btnVerRutas.addActionListener(e -> {
            frame.setContentPane(new DisponibilidadRutasPanel(frame));
            frame.revalidate();
        });

        buttonsPanel.add(btnComprarBoleto);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnRevisarBoleto);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnControlEquipaje);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnValidarTrayecto);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnDesembarco);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnEntregaEquipaje);
        buttonsPanel.add(Box.createVerticalStrut(20));
        buttonsPanel.add(btnVerRutas);

        add(buttonsPanel, BorderLayout.CENTER);

        // Footer
        JButton btnSalir = new JButton("CERRAR SESIÓN");
        btnSalir.setBackground(new Color(150, 40, 40));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.addActionListener(e -> {
            frame.setContentPane(new LoginPanel(frame));
            frame.revalidate();
        });
        JPanel footer = new JPanel();
        footer.add(btnSalir);
        add(footer, BorderLayout.SOUTH);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(GOLD_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(400, 50));
        return button;
    }
}