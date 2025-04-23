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
import java.util.List;

public class GestionEmpleadosPanel extends JPanel {
    private JFrame frame;

    public GestionEmpleadosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        initializeUI();
    }

    private void initializeUI() {
        // Header
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

        // Obtener empleados del gestor
        List<Empleado> empleados = GestorEmpleados.getInstance().getEmpleados();
        
        if (empleados.isEmpty()) {
            contentPanel.add(new JLabel("No hay empleados registrados", SwingConstants.CENTER));
        } else {
            // Crear tabla
            JPanel tablePanel = new JPanel();
            tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
            
            // Cabecera
            JPanel headerPanel = new JPanel(new GridLayout(1, 5));
            headerPanel.setBackground(new Color(0, 86, 179));
            addHeaderCell(headerPanel, "ID");
            addHeaderCell(headerPanel, "Nombre");
            addHeaderCell(headerPanel, "DNI");
            addHeaderCell(headerPanel, "Cargo");
            addHeaderCell(headerPanel, "Teléfono");
            tablePanel.add(headerPanel);
            
            // Filas de datos
            for (Empleado emp : empleados) {
                JPanel rowPanel = new JPanel(new GridLayout(1, 5));
                rowPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
                addDataCell(rowPanel, emp.getIdEmpleado());
                addDataCell(rowPanel, emp.getNombre());
                addDataCell(rowPanel, emp.getDni());
                addDataCell(rowPanel, emp.getCargo());
                addDataCell(rowPanel, emp.getTelefono());
                tablePanel.add(rowPanel);
            }
            
            contentPanel.add(new JScrollPane(tablePanel));
        }

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(240, 240, 240));
        
        JButton backButton = new JButton("VOLVER AL MENÚ");
        backButton.setBackground(new Color(198, 168, 77));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });
        
        JButton addButton = new JButton("AGREGAR EMPLEADO");
        addButton.setBackground(new Color(0, 86, 179));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> {
            frame.setContentPane(new AgregarEmpleadoPanel(frame));
            frame.revalidate();
        });
        
        footer.add(backButton);
        footer.add(Box.createHorizontalStrut(20));
        footer.add(addButton);
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
}