/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VentaBoletosPanel extends JPanel {
    private JFrame frame;
    private JComboBox<String> cbRutas;
    private JComboBox<String> cbHorarios;
    private JTextField txtNombre, txtApellido, txtIdentificacion, txtDireccion, txtTelefono, txtLugar;
    private JComboBox<String> cbTipoIdentificacion, cbCategoriaPasajero;
    private JTextField txtContactoNombre, txtContactoApellido, txtContactoTelefono;
    private JTextField txtEquipajeId, txtEquipajePeso, txtVagonCarga;
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
        // Header
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("COMPRAR BOLETO", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        // Campos de ruta y horario
        cbRutas = new JComboBox<>(GestorRutas.getInstance().getRutasOptimas().stream()
                .map(r -> r.getIdRuta() + ": " + r.getEstacionOrigen() + " a " + r.getEstacionDestino() + " (" + r.getDistancia() + " km)")
                .toArray(String[]::new));
        cbHorarios = new JComboBox<>();
        updateHorarios();
        cbRutas.addActionListener(e -> updateHorarios());

        btnRecomendarRuta = new JButton("Recomendar Ruta Más Corta");
        btnRecomendarRuta.setBackground(GOLD_COLOR);
        btnRecomendarRuta.setForeground(Color.WHITE);
        btnRecomendarRuta.addActionListener(e -> recomendarRutaMasCorta());

        // Campos del pasajero
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtIdentificacion = new JTextField();
        cbTipoIdentificacion = new JComboBox<>(new String[]{"Cédula", "Pasaporte", "DNI"});
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        cbCategoriaPasajero = new JComboBox<>(new String[]{"Adulto", "Niño", "Estudiante", "Tercera Edad"});
        txtLugar = new JTextField("Asiento 1A"); // Ejemplo, puede ser dinámico

        // Campos de contacto
        txtContactoNombre = new JTextField();
        txtContactoApellido = new JTextField();
        txtContactoTelefono = new JTextField();

        // Campos de equipaje
        txtEquipajeId = new JTextField("EQ-" + System.currentTimeMillis());
        txtEquipajePeso = new JTextField();
        txtVagonCarga = new JTextField();

        // Añadir campos al formulario
        addFormField(formPanel, "Ruta:", cbRutas);
        addFormField(formPanel, "Horario:", cbHorarios);
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
        addFormField(formPanel, "ID Equipaje:", txtEquipajeId);
        addFormField(formPanel, "Peso Equipaje (kg):", txtEquipajePeso);
        addFormField(formPanel, "Vagón de Carga:", txtVagonCarga);

        // Botón de compra
        JButton btnComprar = new JButton("COMPRAR");
        btnComprar.setBackground(GOLD_COLOR);
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 16));
        btnComprar.addActionListener(e -> comprarBoleto());

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
            GestorHorarios.getInstance().getHorarios().stream()
                    .filter(h -> h.getIdRuta().equals(idRuta))
                    .forEach(h -> cbHorarios.addItem(h.getIdHorario() + ": " + h.getHoraSalida() + " (" + h.getDiasSemana() + ")"));
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
        // Validar campos
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() || txtDireccion.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() || txtLugar.getText().isEmpty() ||
                txtContactoNombre.getText().isEmpty() || txtContactoApellido.getText().isEmpty() ||
                txtContactoTelefono.getText().isEmpty() || txtEquipajePeso.getText().isEmpty() ||
                txtVagonCarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener datos
        String idRuta = ((String) cbRutas.getSelectedItem()).split(":")[0];
        String idHorario = cbHorarios.getSelectedItem() != null ? ((String) cbHorarios.getSelectedItem()).split(":")[0] : "";
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
        String idEquipaje = txtEquipajeId.getText();
        double pesoEquipaje;
        try {
            pesoEquipaje = Double.parseDouble(txtEquipajePeso.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Peso de equipaje inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String idVagonCarga = txtVagonCarga.getText();

        // Validar vagón de carga
        Vagon vagon = GestorVagones.getInstance().getVagones().stream()
                .filter(v -> v.getIdVagon().equals(idVagonCarga))
                .findFirst()
                .orElse(null);
        if (vagon == null) {
            JOptionPane.showMessageDialog(frame, "Vagón de carga inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calcular precio (basado en distancia)
        Ruta ruta = GestorRutas.getInstance().getRutas().stream()
                .filter(r -> r.getIdRuta().equals(idRuta))
                .findFirst()
                .orElse(null);
        double precio = ruta != null ? ruta.getDistancia() * 0.1 : 50.0; // $0.1 por km

        // Crear objetos
        PersonaContacto contacto = new PersonaContacto(contactoNombre, contactoApellido, contactoTelefonos);
        Equipaje equipaje = new Equipaje(idEquipaje, pesoEquipaje, idVagonCarga);
        // Fechas ficticias (deberían venir del horario o calcularse)
        LocalDateTime fechaSalida = LocalDateTime.now().plusHours(1);
        LocalDateTime fechaLlegada = LocalDateTime.now().plusHours(3);

        // Crear boleto
        Boleto boleto = new Boleto(
                "TREN-" + System.currentTimeMillis(), idRuta, idHorario, nombre, apellido,
                idPasajero, tipoId, direccion, telefonos, lugar, categoria, precio,
                fechaSalida, fechaLlegada, contacto, equipaje
        );

        // Guardar boleto
        GestorBoletos.getInstance().agregarBoleto(boleto);
        JOptionPane.showMessageDialog(frame, "Boleto comprado exitosamente - ID: " + boleto.getIdBoleto());
        frame.setContentPane(new MenuPasajerosPanel(frame));
        frame.revalidate();
    }
}
