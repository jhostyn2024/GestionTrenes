/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class EditarHorarioPanel extends JPanel {
    private JFrame frame;
    private JComboBox<String> cbHorarios;
    private JTextField txtIdRuta, txtHoraSalida, txtDiasSemana;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public EditarHorarioPanel(JFrame frame) {
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
        JLabel title = new JLabel("EDITAR HORARIO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        // Lista de horarios
        cbHorarios = new JComboBox<>(GestorHorarios.getInstance().getHorarios().stream()
                .map(h -> h.getIdHorario() + ": " + h.getIdRuta() + " - " + h.getHoraSalida() + " (" + h.getDiasSemana() + ")")
                .toArray(String[]::new));
        cbHorarios.addActionListener(e -> cargarDatosHorario());

        txtIdRuta = new JTextField();
        txtHoraSalida = new JTextField();
        txtDiasSemana = new JTextField();

        addFormField(formPanel, "Seleccionar Horario:", cbHorarios);
        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "Hora Salida (HH:mm):", txtHoraSalida);
        addFormField(formPanel, "Días Semana:", txtDiasSemana);

        JButton btnEditar = new JButton("GUARDAR CAMBIOS");
        btnEditar.setBackground(GOLD_COLOR);
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEditar.addActionListener(e -> editarHorario());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnEditar);
        formPanel.add(btnVolver);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // Cargar datos del primer horario (si existe)
        if (cbHorarios.getItemCount() > 0) {
            cbHorarios.setSelectedIndex(0);
            cargarDatosHorario();
        }
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 30));
        panel.add(lbl);
        panel.add(field);
    }

    private void cargarDatosHorario() {
        String selectedHorario = (String) cbHorarios.getSelectedItem();
        if (selectedHorario != null) {
            String idHorario = selectedHorario.split(":")[0];
            Horario horario = GestorHorarios.getInstance().getHorarios().stream()
                    .filter(h -> h.getIdHorario().equals(idHorario))
                    .findFirst()
                    .orElse(null);
            if (horario != null) {
                txtIdRuta.setText(horario.getIdRuta());
                txtHoraSalida.setText(horario.getHoraSalida());
                txtDiasSemana.setText(horario.getDiasSemana());
            }
        }
    }

    private void editarHorario() {
        String selectedHorario = (String) cbHorarios.getSelectedItem();
        if (selectedHorario == null) {
            JOptionPane.showMessageDialog(frame, "Seleccione un horario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idHorario = selectedHorario.split(":")[0];
        String idRuta = txtIdRuta.getText().trim();
        String horaSalida = txtHoraSalida.getText().trim();
        String diasSemana = txtDiasSemana.getText().trim();

        if (idRuta.isEmpty() || horaSalida.isEmpty() || diasSemana.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar formato de hora (HH:mm)
        if (!horaSalida.matches("\\d{2}:\\d{2}")) {
            JOptionPane.showMessageDialog(frame, "Formato de hora inválido (use HH:mm)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que la ruta exista
        boolean rutaExiste = GestorRutas.getInstance().getRutas().stream()
                .anyMatch(r -> r.getIdRuta().equals(idRuta));
        if (!rutaExiste) {
            JOptionPane.showMessageDialog(frame, "ID de ruta inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = GestorHorarios.getInstance().modificarHorario(idHorario, idRuta, horaSalida, diasSemana);
        if (exito) {
            JOptionPane.showMessageDialog(frame, "Horario editado exitosamente - ID: " + idHorario);
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo editar el horario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}