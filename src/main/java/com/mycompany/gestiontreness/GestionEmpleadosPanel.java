/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GestionEmpleadosPanel extends JPanel {
    private JFrame frame;

    public GestionEmpleadosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header (mismo estilo que otros paneles)
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JLabel title = new JLabel("GESTIÓN DE EMPLEADOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        header.add(title);
        
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        List<Empleado> empleados = GestorEmpleados.getInstance().getEmpleados();
        
        if (empleados.isEmpty()) {
            contentPanel.add(new JLabel("No hay empleados registrados", SwingConstants.CENTER));
        } else {
            // Cabecera de la tabla
            JPanel headerPanel = new JPanel(new GridLayout(1, 5));
            headerPanel.setBackground(new Color(0, 86, 179));
            
            addHeaderCell(headerPanel, "ID Empleado");
            addHeaderCell(headerPanel, "Nombre");
            addHeaderCell(headerPanel, "DNI");
            addHeaderCell(headerPanel, "Cargo");
            addHeaderCell(headerPanel, "Teléfono");
            
            contentPanel.add(headerPanel);

            // Filas de datos
            for (Empleado empleado : empleados) {
                JPanel dataRow = new JPanel(new GridLayout(1, 5));
                dataRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                
                addDataCell(dataRow, empleado.getIdEmpleado());
                addDataCell(dataRow, empleado.getNombre());
                addDataCell(dataRow, empleado.getDni());
                addDataCell(dataRow, empleado.getCargo());
                addDataCell(dataRow, empleado.getTelefono());
                
                contentPanel.add(dataRow);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Footer con botones (mismo estilo)
        JPanel footer = new JPanel(new GridLayout(1, 3, 10, 0));
        footer.setBackground(new Color(240, 240, 240));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        
        JButton btnAgregar = createActionButton("AGREGAR", new Color(198, 168, 77));
        btnAgregar.addActionListener(this::agregarEmpleado);
        
        JButton btnEditar = createActionButton("EDITAR", new Color(52, 152, 219));
        btnEditar.addActionListener(this::editarEmpleado);
        
        JButton btnVolver = createActionButton("VOLVER", new Color(205, 163, 74));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });
        
        footer.add(btnVolver);
        footer.add(btnEditar);
        footer.add(btnAgregar);
        
        add(footer, BorderLayout.SOUTH);
    }

    private void addHeaderCell(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        panel.add(label);
    }

    private void addDataCell(JPanel panel, String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        panel.add(label);
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }

    private void agregarEmpleado(ActionEvent e) {
        frame.setContentPane(new AgregarEmpleadoPanel(frame));
        frame.revalidate();
    }

    private void editarEmpleado(ActionEvent e) {
        // Implementar lógica de edición
        JOptionPane.showMessageDialog(frame, "Seleccione un empleado para editar", "Editar", JOptionPane.INFORMATION_MESSAGE);
    }
}