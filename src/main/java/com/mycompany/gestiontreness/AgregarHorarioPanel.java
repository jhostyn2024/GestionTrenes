/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AgregarHorarioPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdRuta;
    private JTextField txtHoraSalida;
    private JTextField txtHoraLlegada;
    private JTextField txtDiasSemana;

    public AgregarHorarioPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        initializeUI();
    }

    private void initializeUI() {
        createHeader();
        createFormPanel();
    }

    private void createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("AGREGAR HORARIO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame)); // Asumo este panel
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdRuta = createFormField(formPanel, "ID:", "");
        txtHoraSalida = createFormField(formPanel, "Hora Salida (HH:mm):", "");
        txtHoraLlegada = createFormField(formPanel, "Hora Llegada (HH:mm):", "");
        txtDiasSemana = createFormField(formPanel, "Días Semana:", "");

        JButton btnGuardar = new JButton("AGREGAR HORARIO");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(this::guardarHorario);

        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnGuardar);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private JTextField createFormField(JPanel panel, String label, String value) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setMaximumSize(new Dimension(500, 50));

        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(180, 25));

        JTextField textField = new JTextField(value, 20);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(10));

        return textField;
    }

    private void guardarHorario(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            String idRuta = txtIdRuta.getText().trim();
            String horaSalida = txtHoraSalida.getText().trim();
            String horaLlegada = txtHoraLlegada.getText().trim();
            String diasSemana = txtDiasSemana.getText().trim();

            Horario nuevoHorario = new Horario(idRuta, horaSalida, horaLlegada, diasSemana);
            GestorHorarios.getInstance().agregarHorario(nuevoHorario);

            System.out.println("Horario creado - ID: " + nuevoHorario.getIdHorario() +
                              ", Ruta: " + idRuta + ", Días: " + diasSemana);

            JOptionPane.showMessageDialog(frame, 
                "Horario agregado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);

            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        } catch (Exception ex) {
            System.out.println("Error al agregar horario: " + ex.getMessage());
            JOptionPane.showMessageDialog(frame, 
                "Error al agregar el horario", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtIdRuta.getText().trim().isEmpty() ||
            txtHoraSalida.getText().trim().isEmpty() ||
            txtHoraLlegada.getText().trim().isEmpty() ||
            txtDiasSemana.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Todos los campos son obligatorios", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validación básica de formato de hora (HH:mm)
        String horaSalida = txtHoraSalida.getText().trim();
        String horaLlegada = txtHoraLlegada.getText().trim();
        if (!horaSalida.matches("\\d{2}:\\d{2}") || !horaLlegada.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(frame, 
                "Las horas deben tener el formato HH:mm", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}