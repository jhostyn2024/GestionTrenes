/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JFrame frame;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public MainMenuPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("MENÚ PRINCIPAL", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        buttonPanel.setBackground(Color.WHITE);

        addButton(buttonPanel, "Gestionar Rutas", () -> {
            System.out.println("Navegando a GestionRutasPanel");
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });

        addButton(buttonPanel, "Modificar/Eliminar Ruta", () -> {
            System.out.println("Navegando a ModificarEliminarRutaPanel");
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
            frame.revalidate();
        });

        addButton(buttonPanel, "Comprar Boleto", () -> {
            System.out.println("Navegando a VentaBoletosPanel");
            frame.setContentPane(new VentaBoletosPanel(frame));
            frame.revalidate();
        });

        addButton(buttonPanel, "Revisión de Boleto (Abordaje)", () -> {
            System.out.println("Navegando a RevisionBoletoPanel");
            frame.setContentPane(new RevisionBoletoPanel(frame));
            frame.revalidate();
        });

        addButton(buttonPanel, "Validación de Boleto (Trayecto)", () -> {
            System.out.println("Navegando a ValidacionTrayectoPanel");
            frame.setContentPane(new ValidacionTrayectoPanel(frame));
            frame.revalidate();
        });

        addButton(buttonPanel, "Salir", () -> {
            System.out.println("Saliendo de la aplicación");
            System.exit(0);
        });

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.setBackground(GOLD_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(e -> action.run());
        panel.add(button);
    }
}