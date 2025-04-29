/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GestionHorariosPanel extends JPanel {
    private JFrame frame;
    private JTable tablaHorarios;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionHorariosPanel(JFrame frame) {
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
        JLabel title = new JLabel("GESTIÓN DE HORARIOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Tabla de horarios
        String[] columnas = {"ID Horario", "ID Ruta", "Hora Salida", "Días Semana"};
        List<Horario> horarios = GestorHorarios.getInstance().getHorarios();
        String[][] datos = new String[horarios.size()][4];
        for (int i = 0; i < horarios.size(); i++) {
            Horario horario = horarios.get(i);
            datos[i][0] = horario.getIdHorario();
            datos[i][1] = horario.getIdRuta();
            datos[i][2] = horario.getHoraSalida();
            datos[i][3] = horario.getDiasSemana();
        }
        tablaHorarios = new JTable(datos, columnas);
        tablaHorarios.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaHorarios.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tablaHorarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(240, 240, 240));

        JButton btnAgregar = new JButton("AGREGAR HORARIO");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarHorario());

        JButton btnModificar = new JButton("MODIFICAR HORARIO");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarHorario());

        JButton btnEliminar = new JButton("ELIMINAR HORARIO");
        btnEliminar.setBackground(GOLD_COLOR);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarHorario());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        buttonsPanel.add(btnAgregar);
        buttonsPanel.add(btnModificar);
        buttonsPanel.add(btnEliminar);
        buttonsPanel.add(btnVolver);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void agregarHorario() {
        JTextField txtIdRuta = new JTextField();
        JTextField txtHoraSalida = new JTextField("HH:mm");
        JTextField txtDiasSemana = new JTextField();
        Object[] message = {
                "ID Ruta:", txtIdRuta,
                "Hora Salida (HH:mm):", txtHoraSalida,
                "Días Semana:", txtDiasSemana
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Agregar Horario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
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

            String idHorario = "HORARIO-" + (GestorHorarios.getInstance().getHorarios().size() + 1);
            Horario horario = new Horario(idHorario, idRuta, horaSalida, diasSemana);
            GestorHorarios.getInstance().agregarHorario(horario);
            JOptionPane.showMessageDialog(frame, "Horario agregado exitosamente");
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        }
    }

    private void modificarHorario() {
        int selectedRow = tablaHorarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un horario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idHorario = (String) tablaHorarios.getValueAt(selectedRow, 0);
        JTextField txtIdRuta = new JTextField((String) tablaHorarios.getValueAt(selectedRow, 1));
        JTextField txtHoraSalida = new JTextField((String) tablaHorarios.getValueAt(selectedRow, 2));
        JTextField txtDiasSemana = new JTextField((String) tablaHorarios.getValueAt(selectedRow, 3));
        Object[] message = {
                "ID Ruta:", txtIdRuta,
                "Hora Salida (HH:mm):", txtHoraSalida,
                "Días Semana:", txtDiasSemana
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Modificar Horario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
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
                JOptionPane.showMessageDialog(frame, "Horario modificado exitosamente");
                frame.setContentPane(new GestionHorariosPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró el horario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarHorario() {
        int selectedRow = tablaHorarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Seleccione un horario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idHorario = (String) tablaHorarios.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Eliminar horario " + idHorario + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = GestorHorarios.getInstance().eliminarHorario(idHorario);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Horario eliminado exitosamente");
                frame.setContentPane(new GestionHorariosPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró el horario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}