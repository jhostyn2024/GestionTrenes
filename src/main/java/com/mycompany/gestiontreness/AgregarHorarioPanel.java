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

public class AgregarHorarioPanel extends JPanel {
    private final JFrame frame;
    private final JTextField txtIdRuta, txtHoraSalida, txtHoraLlegada, txtDiasSemana;

    public AgregarHorarioPanel(JFrame frame) {
        this.frame = frame;
        this.txtIdRuta = new JTextField(20);
        this.txtHoraSalida = new JTextField(20);
        this.txtHoraLlegada = new JTextField(20);
        this.txtDiasSemana = new JTextField(20);
        
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
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        });
        
        JLabel title = new JLabel("AGREGAR HORARIO", SwingConstants.CENTER);
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

        JButton btnContinuar = new JButton("GUARDAR HORARIO");
        btnContinuar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnContinuar.setBackground(new Color(0, 86, 179));
        btnContinuar.setForeground(Color.WHITE);
        btnContinuar.setFont(new Font("Arial", Font.BOLD, 16));
        btnContinuar.setPreferredSize(new Dimension(250, 40));
        btnContinuar.addActionListener(this::guardarHorario);
        
        formPanel.add(Box.createVerticalStrut(30));
        formPanel.add(btnContinuar);

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

    private void guardarHorario(ActionEvent e) {
        String idRuta = txtIdRuta.getText().trim();
        String horaSalida = txtHoraSalida.getText().trim();
        String horaLlegada = txtHoraLlegada.getText().trim();
        String diasSemana = txtDiasSemana.getText().trim();

        if (idRuta.isEmpty() || horaSalida.isEmpty() || horaLlegada.isEmpty() || diasSemana.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Horario nuevoHorario = new Horario(idRuta, horaSalida, horaLlegada, diasSemana);
        GestorHorarios.getInstance().agregarHorario(nuevoHorario);

        JOptionPane.showMessageDialog(frame, "Horario guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        frame.setContentPane(new GestionHorariosPanel(frame));
        frame.revalidate();
    }
}