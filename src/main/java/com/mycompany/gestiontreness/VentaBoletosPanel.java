/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VentaBoletosPanel extends JPanel {
    private JFrame frame;
    private JComboBox<String> cbRutas;
    private JComboBox<String> cbHorarios;
    private JComboBox<String> cbTrenes;
    private JTextField txtNombre, txtApellido, txtIdentificacion, txtDireccion, txtTelefono, txtLugar;
    private JComboBox<String> cbTipoIdentificacion, cbCategoriaPasajero;
    private JTextField txtContactoNombre, txtContactoApellido, txtContactoTelefono;
    private JTextField txtEquipajeId1, txtEquipajePeso1, txtEquipajeId2, txtEquipajePeso2;
    private JComboBox<String> cbVagonCarga;
    private JButton btnRecomendarRuta;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public VentaBoletosPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("COMPRAR BOLETO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        cbRutas = new JComboBox<>(GestorRutas.getInstance().getRutasOptimas().stream()
                .filter(r -> !GestorHorarios.getInstance().getHorariosPorRuta(r.getIdRuta()).isEmpty())
                .map(r -> r.getIdRuta() + ": " + r.getEstacionOrigen() + " a " + r.getEstacionDestino() + " (" + r.getDistancia() + " km)")
                .toArray(String[]::new));
        cbHorarios = new JComboBox<>();
        cbTrenes = new JComboBox<>(GestorTrenes.getInstance().getTrenes().stream()
                .map(t -> t.getIdTren() + ": " + t.getNombre())
                .toArray(String[]::new));
        updateHorarios();
        cbRutas.addActionListener(e -> updateHorarios());

        btnRecomendarRuta = new JButton("Recomendar Ruta Más Corta");
        btnRecomendarRuta.setBackground(GOLD_COLOR);
        btnRecomendarRuta.setForeground(Color.WHITE);
        btnRecomendarRuta.addActionListener(e -> recomendarRutaMasCorta());

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtIdentificacion = new JTextField();
        cbTipoIdentificacion = new JComboBox<>(new String[]{"Cédula", "Pasaporte", "DNI"});
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        cbCategoriaPasajero = new JComboBox<>(new String[]{"Premium", "Ejecutiva", "Estándar"});
        txtLugar = new JTextField();

        txtContactoNombre = new JTextField();
        txtContactoApellido = new JTextField();
        txtContactoTelefono = new JTextField();

        txtEquipajeId1 = new JTextField("EQ1-" + UUID.randomUUID().toString().substring(0, 8));
        txtEquipajePeso1 = new JTextField();
        txtEquipajeId2 = new JTextField("EQ2-" + UUID.randomUUID().toString().substring(0, 8));
        txtEquipajePeso2 = new JTextField();
        List<String> vagonesCarga = GestorVagones.getInstance().getVagones().stream()
                .filter(v -> v.getTipo().equals("Carga"))
                .map(Vagon::getIdVagon)
                .collect(Collectors.toList());
        if (vagonesCarga.isEmpty()) {
            vagonesCarga.add("Sin vagones de carga disponibles");
            cbVagonCarga = new JComboBox<>(vagonesCarga.toArray(String[]::new));
            cbVagonCarga.setEnabled(false);
        } else {
            cbVagonCarga = new JComboBox<>(vagonesCarga.toArray(String[]::new));
            cbVagonCarga.setEnabled(true);
        }

        addFormField(formPanel, "Ruta:", cbRutas);
        addFormField(formPanel, "Horario:", cbHorarios);
        addFormField(formPanel, "Tren:", cbTrenes);
        addFormField(formPanel, "", btnRecomendarRuta);
        addFormField(formPanel, "Nombre:", txtNombre);
        addFormField(formPanel, "Apellido:", txtApellido);
        addFormField(formPanel, "Identificación:", txtIdentificacion);
        addFormField(formPanel, "Tipo Identificación:", cbTipoIdentificacion);
        addFormField(formPanel, "Dirección:", txtDireccion);
        addFormField(formPanel, "Teléfono:", txtTelefono);
        addFormField(formPanel, "Categoría Pasajero:", cbCategoriaPasajero);
        addFormField(formPanel, "Lugar (Asiento):", txtLugar);
        addFormField(formPanel, "Contacto Nombre:", txtContactoNombre);
        addFormField(formPanel, "Contacto Apellido:", txtContactoApellido);
        addFormField(formPanel, "Contacto Teléfono:", txtContactoTelefono);
        addFormField(formPanel, "ID Equipaje 1:", txtEquipajeId1);
        addFormField(formPanel, "Peso Equipaje 1 (kg):", txtEquipajePeso1);
        addFormField(formPanel, "ID Equipaje 2:", txtEquipajeId2);
        addFormField(formPanel, "Peso Equipaje 2 (kg):", txtEquipajePeso2);
        addFormField(formPanel, "Vagón de Carga:", cbVagonCarga);

        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.setBackground(GOLD_COLOR);
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 16));
        btnComprar.addActionListener(e -> {
            System.out.println("Botón COMPRAR presionado en VentaBoletosPanel");
            comprarBoleto();
        });

        add(new JScrollPane(formPanel), BorderLayout.CENTER);
        add(btnComprar, BorderLayout.SOUTH);
    }

    private void addFormField(JPanel panel, String label, JComponent field) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        if (field instanceof JTextField) {
            field.setPreferredSize(new Dimension(200, 30));
        }
        panel.add(lbl);
        panel.add(field);
    }

    private void updateHorarios() {
        cbHorarios.removeAllItems();
        String selectedRuta = (String) cbRutas.getSelectedItem();
        if (selectedRuta != null) {
            String idRuta = selectedRuta.split(":")[0];
            List<Horario> horarios = GestorHorarios.getInstance().getHorariosPorRuta(idRuta);
            if (horarios.isEmpty()) {
                cbHorarios.addItem("Sin horarios disponibles");
                cbHorarios.setEnabled(false);
            } else {
                horarios.forEach(h -> cbHorarios.addItem(h.getIdHorario() + ": " + h.getHoraSalida() + " (" + h.getDiasSemana() + ")"));
                cbHorarios.setEnabled(true);
            }
        } else {
            cbHorarios.addItem("Seleccione una ruta");
            cbHorarios.setEnabled(false);
        }
    }

    private void recomendarRutaMasCorta() {
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        Object[] message = {
                "Origen:", txtOrigen,
                "Destino:", txtDestino
        };
        int option = JOptionPane.showConfirmDialog(frame, message, "Recomendar Ruta Más Corta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Ruta ruta = GestorRutas.getInstance().encontrarRutaMasCorta(txtOrigen.getText(), txtDestino.getText());
            if (ruta != null) {
                for (int i = 0; i < cbRutas.getItemCount(); i++) {
                    if (cbRutas.getItemAt(i).startsWith(ruta.getIdRuta())) {
                        cbRutas.setSelectedIndex(i);
                        updateHorarios();
                        JOptionPane.showMessageDialog(frame, "Ruta más corta: " + ruta.getEstacionOrigen() + " a " + ruta.getEstacionDestino() + " (" + ruta.getDistancia() + " km)");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "No se encontró una ruta directa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprarBoleto() {
        System.out.println("Procesando compra de boleto...");
        // Lógica de compra ya existente...

        // Al final de la compra exitosa:
        JOptionPane.showMessageDialog(frame, "Boleto comprado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Boleto comprado exitosamente");
    }
}
