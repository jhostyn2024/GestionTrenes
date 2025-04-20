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

public class EditarHorarioPanel extends JPanel {
    private final JFrame frame;
    private final Horario horarioOriginal;
    private final JTextField txtIdRuta, txtHoraSalida, txtHoraLlegada, txtDiasSemana;

    public EditarHorarioPanel(JFrame frame, Horario horario) {
        this.frame = frame;
        this.horarioOriginal = horario;
        this.txtIdRuta = new JTextField(horario.getIdRuta(), 20);
        this.txtHoraSalida = new JTextField(horario.getHoraSalida(), 20);
        this.txtHoraLlegada = new JTextField(horario.getHoraLlegada(), 20);
        this.txtDiasSemana = new JTextField(horario.getDiasSemana(), 20);
        
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(0, 86, 179));
        header.setPreferredSize(new Dimension(800, 80));
        
        JButton backButton = new JButton("← VOLVER");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
            frame.revalidate();
        });
        
        JLabel title = new JLabel("EDITAR HORARIO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        formPanel.setBackground(Color.WHITE);

        addFormField(formPanel, "ID RUTA:", txtIdRuta);
        addFormField(formPanel, "HORA SALIDA (HH:MM):", txtHoraSalida);
        addFormField(formPanel, "HORA LLEGADA (HH:MM):", txtHoraLlegada);
        addFormField(formPanel, "DÍAS SEMANA:", txtDiasSemana);

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(0, 86, 179));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setPreferredSize(new Dimension(250, 40));
        btnGuardar.addActionListener(this::guardarCambios);
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnGuardar);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
    }

    private void addFormField(JPanel panel, String label, JTextField textField) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        fieldPanel.setBackground(Color.WHITE);
        
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(200, 25));
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        fieldPanel.add(lbl);
        fieldPanel.add(textField);
        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(15));
    }

    private void guardarCambios(ActionEvent e) {
        String idRuta = txtIdRuta.getText().trim();
        String horaSalida = txtHoraSalida.getText().trim();
        String horaLlegada = txtHoraLlegada.getText().trim();
        String diasSemana = txtDiasSemana.getText().trim();

        if (idRuta.isEmpty() || horaSalida.isEmpty() || horaLlegada.isEmpty() || diasSemana.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Actualizar el horario
        horarioOriginal.setIdRuta(idRuta);
        horarioOriginal.setHoraSalida(horaSalida);
        horarioOriginal.setHoraLlegada(horaLlegada);
        horarioOriginal.setDiasSemana(diasSemana);

        JOptionPane.showMessageDialog(frame, "Horario actualizado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
        frame.revalidate();
    }
}