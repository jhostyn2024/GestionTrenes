/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ModificarEliminarHorarioPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdHorario, txtIdRuta, txtIdTren, txtFechaSalida, txtFechaLlegada;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ModificarEliminarHorarioPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("MODIFICAR/ELIMINAR HORARIO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdHorario = new JTextField();
        txtIdRuta = new JTextField();
        txtIdTren = new JTextField();
        txtFechaSalida = new JTextField();
        txtFechaLlegada = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Horario:", txtIdHorario);
        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "Fecha Salida:", txtFechaSalida);
        addFormField(formPanel, "Fecha Llegada:", txtFechaLlegada);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBackground(GOLD_COLOR);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        btnBuscar.addActionListener(e -> buscarHorario());

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarHorario());

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(new Color(200, 50, 50));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarHorario());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde ModificarEliminarHorarioPanel");
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnBuscar);
        formPanel.add(btnModificar);
        formPanel.add(btnEliminar);
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

    private void buscarHorario() {
        try {
            String idHorario = txtIdHorario.getText().trim();
            if (idHorario.isEmpty()) {
                txtResultado.setText("Error: Ingrese el ID del horario.");
                System.out.println("ID de horario vacío en buscarHorario");
                return;
            }

            GestorHorarios gestor = GestorHorarios.getInstance();
            Horario horario = gestor.getHorarios().stream()
                    .filter(h -> h.getIdHorario().equals(idHorario))
                    .findFirst()
                    .orElse(null);

            if (horario == null) {
                txtResultado.setText("Error: No se encontró un horario con ID: " + idHorario);
                System.out.println("Horario no encontrado: " + idHorario);
                return;
            }

            txtIdRuta.setText(horario.getIdRuta());
            txtIdTren.setText(horario.getIdTren());
            txtFechaSalida.setText(horario.getFechaSalida());
            txtFechaLlegada.setText(horario.getFechaLlegada());
            cbEstado.setSelectedItem(horario.getEstado());
            txtResultado.setText("Horario encontrado:\nID: " + idHorario + "\nRuta: " + horario.getIdRuta() + "\nTren: " + horario.getIdTren() + "\nSalida: " + horario.getFechaSalida() + "\nLlegada: " + horario.getFechaLlegada() + "\nEstado: " + horario.getEstado());
            System.out.println("Horario encontrado: " + idHorario);
        } catch (Exception e) {
            txtResultado.setText("Error al buscar horario: " + e.getMessage());
            System.err.println("Excepción en buscarHorario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void modificarHorario() {
        try {
            String idHorario = txtIdHorario.getText().trim();
            String idRuta = txtIdRuta.getText().trim();
            String idTren = txtIdTren.getText().trim();
            String fechaSalida = txtFechaSalida.getText().trim();
            String fechaLlegada = txtFechaLlegada.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (idHorario.isEmpty() || idRuta.isEmpty() || idTren.isEmpty() || fechaSalida.isEmpty() || fechaLlegada.isEmpty()) {
                txtResultado.setText("Error: Complete todos los campos.");
                System.out.println("Campos incompletos en modificarHorario");
                return;
            }

            if (!fechaSalida.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}") ||
                !fechaLlegada.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                txtResultado.setText("Error: Formato de fecha inválido (use YYYY-MM-DDTHH:MM:SS).");
                System.out.println("Formato de fecha inválido en modificarHorario");
                return;
            }

            GestorRutas gestorRutas = GestorRutas.getInstance();
            GestorTrenes gestorTrenes = GestorTrenes.getInstance();
            boolean rutaExiste = gestorRutas.getRutas().stream().anyMatch(r -> r.getIdRuta().equals(idRuta));
            boolean trenExiste = gestorTrenes.getTrenes().stream().anyMatch(t -> t.getIdTren().equals(idTren));
            if (!rutaExiste) {
                txtResultado.setText("Error: El ID de ruta no existe.");
                System.out.println("ID de ruta no encontrado: " + idRuta);
                return;
            }
            if (!trenExiste) {
                txtResultado.setText("Error: El ID de tren no existe.");
                System.out.println("ID de tren no encontrado: " + idTren);
                return;
            }

            GestorHorarios gestor = GestorHorarios.getInstance();
            boolean modificado = gestor.modificarHorario(idHorario, idRuta, idTren, fechaSalida, fechaLlegada, estado);
            if (modificado) {
                txtResultado.setText("Horario modificado exitosamente:\nID: " + idHorario + "\nRuta: " + idRuta + "\nTren: " + idTren + "\nSalida: " + fechaSalida + "\nLlegada: " + fechaLlegada + "\nEstado: " + estado);
                System.out.println("Horario modificado: " + idHorario);
                limpiarFormulario();
            } else {
                txtResultado.setText("Error: No se encontró un horario con ID: " + idHorario);
                System.out.println("Horario no encontrado para modificar: " + idHorario);
            }
        } catch (Exception e) {
            txtResultado.setText("Error al modificar horario: " + e.getMessage());
            System.err.println("Excepción en modificarHorario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void eliminarHorario() {
        try {
            String idHorario = txtIdHorario.getText().trim();
            if (idHorario.isEmpty()) {
                txtResultado.setText("Error: Ingrese el ID del horario.");
                System.out.println("ID de horario vacío en eliminarHorario");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar el horario con ID: " + idHorario + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                txtResultado.setText("Eliminación cancelada.");
                System.out.println("Eliminación cancelada para horario: " + idHorario);
                return;
            }

            GestorHorarios gestor = GestorHorarios.getInstance();
            boolean eliminado = gestor.eliminarHorario(idHorario);
            if (eliminado) {
                txtResultado.setText("Horario eliminado exitosamente: ID: " + idHorario);
                System.out.println("Horario eliminado: " + idHorario);
                limpiarFormulario();
            } else {
                txtResultado.setText("Error: No se encontró un horario con ID: " + idHorario);
                System.out.println("Horario no encontrado para eliminar: " + idHorario);
            }
        } catch (Exception e) {
            txtResultado.setText("Error al eliminar horario: " + e.getMessage());
            System.err.println("Excepción en eliminarHorario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        txtIdHorario.setText("");
        txtIdRuta.setText("");
        txtIdTren.setText("");
        txtFechaSalida.setText("");
        txtFechaLlegada.setText("");
        cbEstado.setSelectedIndex(0);
    }
}