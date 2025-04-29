/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class GestionHorariosPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdHorario, txtIdRuta, txtIdTren, txtFechaSalida, txtFechaLlegada;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public GestionHorariosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("GESTIÓN DE HORARIOS", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdHorario = new JTextField("HORARIO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        txtIdHorario.setEditable(false);
        txtIdRuta = new JTextField();
        txtIdTren = new JTextField();
        txtFechaSalida = new JTextField("2025-05-01T08:00:00");
        txtFechaLlegada = new JTextField("2025-05-01T08:30:00");
        cbEstado = new JComboBox<>(new String[]{"Activo", "Inactivo"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Horario:", txtIdHorario);
        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "ID Tren:", txtIdTren);
        addFormField(formPanel, "Fecha Salida (YYYY-MM-DDTHH:MM:SS):", txtFechaSalida);
        addFormField(formPanel, "Fecha Llegada (YYYY-MM-DDTHH:MM:SS):", txtFechaLlegada);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnAgregar = new JButton("AGREGAR");
        btnAgregar.setBackground(GOLD_COLOR);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.addActionListener(e -> agregarHorario());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde GestionHorariosPanel");
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

    private void agregarHorario() {
        try {
            String idHorario = txtIdHorario.getText().trim();
            String idRuta = txtIdRuta.getText().trim();
            String idTren = txtIdTren.getText().trim();
            String fechaSalida = txtFechaSalida.getText().trim();
            String fechaLlegada = txtFechaLlegada.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (idRuta.isEmpty() || idTren.isEmpty() || fechaSalida.isEmpty() || fechaLlegada.isEmpty()) {
                txtResultado.setText("Error: Complete los campos obligatorios (ID Ruta, ID Tren, Fechas).");
                System.out.println("Campos incompletos en agregarHorario");
                return;
            }

            if (!fechaSalida.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}") ||
                !fechaLlegada.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                txtResultado.setText("Error: Formato de fecha inválido (use YYYY-MM-DDTHH:MM:SS).");
                System.out.println("Formato de fecha inválido en agregarHorario");
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

            Horario horario = new Horario(idHorario, idRuta, idTren, fechaSalida, fechaLlegada, estado);
            GestorHorarios.getInstance().agregarHorario(horario);
            txtResultado.setText("Horario agregado exitosamente:\nID: " + idHorario + "\nRuta: " + idRuta + "\nTren: " + idTren + "\nSalida: " + fechaSalida + "\nLlegada: " + fechaLlegada + "\nEstado: " + estado);
            System.out.println("Horario agregado: " + idHorario);

            txtIdHorario.setText("HORARIO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            txtIdRuta.setText("");
            txtIdTren.setText("");
            txtFechaSalida.setText("2025-05-01T08:00:00");
            txtFechaLlegada.setText("2025-05-01T08:30:00");
            cbEstado.setSelectedIndex(0);
        } catch (Exception e) {
            txtResultado.setText("Error al agregar horario: " + e.getMessage());
            System.err.println("Excepción en agregarHorario: " + e.getMessage());
            e.printStackTrace();
        }
    }
}