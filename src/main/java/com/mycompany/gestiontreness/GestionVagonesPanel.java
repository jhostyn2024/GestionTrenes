/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class GestionVagonesPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdVagon, txtIdTren, txtCapacidad;
    private JComboBox<String> cbTipo, cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionVagonesPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("GESTIÓN DE VAGONES", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdVagon = new JTextField("VAGON-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        txtIdVagon.setEditable(false);
        txtIdTren = new JTextField();
        txtCapacidad = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"Primera Clase", "Económico", "Carga"});
        cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Vagón:", txtIdVagon);
        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "Tipo:", cbTipo);
        addFormField(formPanel, "Capacidad:", txtCapacidad);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarVagon());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde GestionVagonesPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnAgregar);
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

    private void agregarVagon() {
        try {
            String idVagon = txtIdVagon.getText().trim();
            String idTren = txtIdTren.getText().trim();
            String tipo = (String) cbTipo.getSelectedItem();
            String capacidadStr = txtCapacidad.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (idTren.isEmpty() || capacidadStr.isEmpty()) {
                txtResultado.setText("Error: Complete los campos obligatorios (ID Tren, Capacidad).");
                System.out.println("Campos incompletos en agregarVagon");
                return;
            }

            GestorTrenes gestorTrenes = GestorTrenes.getInstance();
            boolean trenExiste = gestorTrenes.getTrenes().stream().anyMatch(t -> t.getIdTren().equals(idTren));
            if (!trenExiste) {
                txtResultado.setText("Error: El ID de tren no existe.");
                System.out.println("ID de tren no encontrado: " + idTren);
                return;
            }

            int capacidad;
            try {
                capacidad = Integer.parseInt(capacidadStr);
                if (capacidad <= 0) {
                    txtResultado.setText("Error: La capacidad debe ser mayor a 0.");
                    System.out.println("Capacidad inválida en agregarVagon");
                    return;
                }
            } catch (NumberFormatException e) {
                txtResultado.setText("Error: La capacidad debe ser un número válido.");
                System.out.println("Formato de capacidad inválido en agregarVagon");
                return;
            }

            Vagon vagon = new Vagon(idVagon, idTren, tipo, capacidad, estado);
            GestorVagones.getInstance().agregarVagon(vagon);
            txtResultado.setText("Vagón agregado exitosamente:\nID: " + idVagon + "\nTren: " + idTren + "\nTipo: " + tipo + "\nCapacidad: " + capacidad + "\nEstado: " + estado);
            System.out.println("Vagón agregado: " + idVagon);

            txtIdVagon.setText("VAGON-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            txtIdTren.setText("");
            txtCapacidad.setText("");
            cbTipo.setSelectedIndex(0);
            cbEstado.setSelectedIndex(0);
        } catch (Exception e) {
            txtResultado.setText("Error al agregar vagón: " + e.getMessage());
            System.err.println("Excepción en agregarVagon: " + e.getMessage());
            e.printStackTrace();
        }
    }
}