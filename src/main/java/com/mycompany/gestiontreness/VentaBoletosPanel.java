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
            List<Horario> horarios = (List<Horario>) GestorHorarios.getInstance().getHorariosPorRuta(idRuta);
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
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() || txtLugar.getText().isEmpty() ||
                txtContactoNombre.getText().isEmpty() || txtContactoApellido.getText().isEmpty() ||
                txtContactoTelefono.getText().isEmpty() || cbVagonCarga.getSelectedItem() == null ||
                cbHorarios.getSelectedItem() == null || cbTrenes.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos, incluyendo ruta, horario y tren", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Campos incompletos en comprarBoleto");
            return;
        }

        String selectedVagon = (String) cbVagonCarga.getSelectedItem();
        if (selectedVagon.equals("Sin vagones de carga disponibles")) {
            JOptionPane.showMessageDialog(frame, "No hay vagones de carga disponibles", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Sin vagones de carga disponibles");
            return;
        }

        String selectedHorario = (String) cbHorarios.getSelectedItem();
        if (selectedHorario.equals("Sin horarios disponibles") || selectedHorario.equals("Seleccione una ruta")) {
            JOptionPane.showMessageDialog(frame, "No hay horarios disponibles para la ruta seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Sin horarios disponibles");
            return;
        }

        String idRuta = ((String) cbRutas.getSelectedItem()).split(":")[0];
        String idHorario = selectedHorario.split(":")[0];
        String idTren = ((String) cbTrenes.getSelectedItem()).split(":")[0];
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String idPasajero = txtIdentificacion.getText();
        String tipoId = (String) cbTipoIdentificacion.getSelectedItem();
        String direccion = txtDireccion.getText();
        List<String> telefonos = List.of(txtTelefono.getText());
        String lugar = txtLugar.getText();
        String categoria = (String) cbCategoriaPasajero.getSelectedItem();
        String contactoNombre = txtContactoNombre.getText();
        String contactoApellido = txtContactoApellido.getText();
        List<String> contactoTelefonos = List.of(txtContactoTelefono.getText());
        String idEquipaje1 = txtEquipajeId1.getText();
        String idEquipaje2 = txtEquipajeId2.getText();
        double pesoEquipaje1, pesoEquipaje2;
        try {
            pesoEquipaje1 = txtEquipajePeso1.getText().isEmpty() ? 0 : Double.parseDouble(txtEquipajePeso1.getText());
            pesoEquipaje2 = txtEquipajePeso2.getText().isEmpty() ? 0 : Double.parseDouble(txtEquipajePeso2.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Peso de equipaje inválido", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Peso de equipaje inválido: " + e.getMessage());
            return;
        }
        String idVagonCarga = (String) cbVagonCarga.getSelectedItem();

        if (pesoEquipaje1 > 80 || pesoEquipaje2 > 80) {
            JOptionPane.showMessageDialog(frame, "Cada maleta no puede exceder 80 kg", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Peso de equipaje excede 80 kg");
            return;
        }
        if (!idEquipaje1.isEmpty() && pesoEquipaje1 <= 0) {
            JOptionPane.showMessageDialog(frame, "Especifique el peso de la maleta 1", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Peso de maleta 1 no especificado");
            return;
        }
        if (!idEquipaje2.isEmpty() && pesoEquipaje2 <= 0) {
            JOptionPane.showMessageDialog(frame, "Especifique el peso de la maleta 2", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Peso de maleta 2 no especificado");
            return;
        }

        Vagon vagon = GestorVagones.getInstance().getVagones().stream()
                .filter(v -> v.getIdVagon().equals(idVagonCarga))
                .findFirst()
                .orElse(null);
        if (vagon == null || !vagon.getTipo().equals("Carga")) {
            JOptionPane.showMessageDialog(frame, "Vagón de carga inválido", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Vagón de carga inválido: " + idVagonCarga);
            return;
        }

        if (!GestorVagones.getInstance().validarProporcionVagones(idTren)) {
            JOptionPane.showMessageDialog(frame, "No hay suficientes vagones de carga para este tren", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Proporción de vagones inválida para tren: " + idTren);
            return;
        }

        List<Vagon> vagonesPasajeros = GestorVagones.getInstance().getVagonesPorTren(idTren).stream()
                .filter(v -> v.getTipo().equals("Pasajeros"))
                .collect(Collectors.toList());
        int capacidadTotal = vagonesPasajeros.stream().mapToInt(Vagon::getCapacidadPasajeros).sum();
        int boletosVendidos = GestorBoletos.getInstance().getBoletos().stream()
                .filter(b -> b.getIdTren().equals(idTren) && b.getIdHorario().equals(idHorario))
                .toList().size();
        if (boletosVendidos >= capacidadTotal) {
            JOptionPane.showMessageDialog(frame, "No hay asientos disponibles en este tren", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No hay asientos disponibles en tren: " + idTren);
            return;
        }

        int disponibles;
        switch (categoria) {
            case "Premium":
                disponibles = vagonesPasajeros.stream().mapToInt(Vagon::getLugaresPremium).sum();
                break;
            case "Ejecutiva":
                disponibles = vagonesPasajeros.stream().mapToInt(Vagon::getLugaresEjecutiva).sum();
                break;
            default:
                disponibles = vagonesPasajeros.stream().mapToInt(Vagon::getLugaresEstandar).sum();
                break;
        }
        int ocupados = GestorBoletos.getInstance().getBoletos().stream()
                .filter(b -> b.getIdTren().equals(idTren) && b.getIdHorario().equals(idHorario) && b.getCategoriaPasajero().equals(categoria))
                .toList().size();
        if (ocupados >= disponibles) {
            JOptionPane.showMessageDialog(frame, "No hay asientos disponibles en la categoría " + categoria, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No hay asientos disponibles en categoría: " + categoria);
            return;
        }

        Horario horario = GestorHorarios.getInstance().getHorarios().stream()
                .filter(h -> h.getIdHorario().equals(idHorario))
                .findFirst()
                .orElse(null);
        if (horario == null) {
            JOptionPane.showMessageDialog(frame, "Horario inválido", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Horario inválido: " + idHorario);
            return;
        }

        Ruta ruta = GestorRutas.getInstance().getRutas().stream()
                .filter(r -> r.getIdRuta().equals(idRuta))
                .findFirst()
                .orElse(null);
        if (ruta == null) {
            JOptionPane.showMessageDialog(frame, "Ruta inválida", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Ruta inválida: " + idRuta);
            return;
        }
        double basePrecio = ruta.getDistancia() * 0.1;
        double precio = switch (categoria) {
            case "Premium" -> basePrecio * 1.5;
            case "Ejecutiva" -> basePrecio * 1.2;
            default -> basePrecio;
        };

        LocalDateTime fechaSalida;
        try {
            fechaSalida = LocalDateTime.now()
                    .with(LocalTime.parse(horario.getHoraSalida(), DateTimeFormatter.ofPattern("HH:mm")));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Formato de hora inválido en el horario", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Formato de hora inválido: " + e.getMessage());
            return;
        }
        double distancia = ruta.getDistancia();
        long horasViaje = Math.round(distancia / 50.0);
        LocalDateTime fechaLlegada = fechaSalida.plusHours(horasViaje);

        PersonaContacto contacto = new PersonaContacto(contactoNombre, contactoApellido, contactoTelefonos);
        List<Equipaje> equipajes = new ArrayList<>();
        if (!idEquipaje1.isEmpty() && pesoEquipaje1 > 0) {
            equipajes.add(new Equipaje(idEquipaje1, pesoEquipaje1, idVagonCarga));
        }
        if (!idEquipaje2.isEmpty() && pesoEquipaje2 > 0) {
            equipajes.add(new Equipaje(idEquipaje2, pesoEquipaje2, idVagonCarga));
        }

        String idBoleto = "BOL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Boleto boleto = new Boleto(
                idBoleto, idRuta, idHorario, idTren, nombre, apellido,
                idPasajero, tipoId, direccion, telefonos, lugar, categoria, precio,
                fechaSalida, fechaLlegada, contacto, equipajes
        );

        GestorBoletos.getInstance().agregarBoleto(boleto);
        System.out.println("Boleto creado con ID: " + idBoleto);
        JOptionPane.showMessageDialog(frame, "Boleto comprado exitosamente\nID Boleto: " + idBoleto + 
            "\nGuarde este ID para validación", "Compra Exitosa", JOptionPane.INFORMATION_MESSAGE);
        frame.setContentPane(new MenuPasajerosPanel(frame));
        frame.revalidate();
    }
}