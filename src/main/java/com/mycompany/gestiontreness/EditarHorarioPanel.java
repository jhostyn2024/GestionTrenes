/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditarHorarioPanel extends JPanel {
    private JFrame frame;
    private Horario horarioOriginal;
    
    private JTextField txtEstacionOrigen;
    private JTextField txtEstacionDestino;
    private JTextField txtHoraSalida;
    private JTextField txtHoraLlegada;
    private JTextField txtFecha;

    public EditarHorarioPanel(JFrame frame, Horario horario) {
        this.frame = frame;
        this.horarioOriginal = horario;
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

        JLabel title = new JLabel("EDITAR HORARIO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Cancelar");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
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

        txtEstacionOrigen = createFormField(formPanel, "Estación Origen:", horarioOriginal.getEstacionOrigen());
        txtEstacionDestino = createFormField(formPanel, "Estación Destino:", horarioOriginal.getEstacionDestino());
        txtHoraSalida = createFormField(formPanel, "Hora Salida:", horarioOriginal.getHoraSalida());
        txtHoraLlegada = createFormField(formPanel, "Hora Llegada:", horarioOriginal.getHoraLlegada());
        txtFecha = createFormField(formPanel, "Fecha:", horarioOriginal.getFecha());

        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.addActionListener(this::guardarCambios);

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

    private void guardarCambios(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            // Actualizar los campos de horarioOriginal directamente
            horarioOriginal.setEstacionOrigen(txtEstacionOrigen.getText());
            horarioOriginal.setEstacionDestino(txtEstacionDestino.getText());
            horarioOriginal.setHoraSalida(txtHoraSalida.getText());
            horarioOriginal.setHoraLlegada(txtHoraLlegada.getText());
            horarioOriginal.setFecha(txtFecha.getText());

            // Depuración
            System.out.println("Horario actualizado - ID: " + horarioOriginal.getIdHorario());
            System.out.println("Nuevos valores: Origen=" + horarioOriginal.getEstacionOrigen() +
                              ", Destino=" + horarioOriginal.getEstacionDestino() +
                              ", Fecha=" + horarioOriginal.getFecha());

            JOptionPane.showMessageDialog(frame, 
                "Horario actualizado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
            frame.setContentPane(new ModificarEliminarHorarioPanel(frame));
            frame.revalidate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, 
                "Error al guardar los cambios", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtEstacionOrigen.getText().trim().isEmpty() ||
            txtEstacionDestino.getText().trim().isEmpty() ||
            txtHoraSalida.getText().trim().isEmpty() ||
            txtHoraLlegada.getText().trim().isEmpty() ||
            txtFecha.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Todos los campos son obligatorios", 
                "Error de validación", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Aquí podrías añadir más validaciones (por ejemplo, formato de hora o fecha)
        return true;
    }
}