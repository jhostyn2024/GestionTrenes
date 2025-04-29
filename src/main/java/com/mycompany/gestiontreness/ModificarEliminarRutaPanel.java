/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModificarEliminarRutaPanel extends JPanel {
    private JFrame frame;
    private JComboBox<String> cbRutas;
    private JTextField txtOrigen, txtDestino, txtDistancia;
    private JComboBox<String> cbEstado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ModificarEliminarRutaPanel(JFrame frame) {
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
        JLabel title = new JLabel("MODIFICAR/ELIMINAR RUTA", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Formulario
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        cbRutas = new JComboBox<>(rutas.stream()
                .map(r -> r.getIdRuta() + ": " + r.getEstacionOrigen() + " a " + r.getEstacionDestino() + " (" + r.getDistancia() + " km)")
                .toArray(String[]::new));
        cbRutas.addActionListener(e -> cargarDatosRuta());

        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        txtDistancia = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});

        addFormField(formPanel, "Seleccionar Ruta:", cbRutas);
        addFormField(formPanel, "Origen:", txtOrigen);
        addFormField(formPanel, "Destino:", txtDestino);
        addFormField(formPanel, "Distancia (km):", txtDistancia);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarRuta());

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(GOLD_COLOR);
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarRuta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(150, 40, 40));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            frame.setContentPane(new GestionRutasPanel(frame));
            frame.revalidate();
        });

        formPanel.add(btnModificar);
        formPanel.add(btnEliminar);
        formPanel.add(btnVolver);

        add(new JScrollPane(formPanel), BorderLayout.CENTER);

        // Cargar datos de la primera ruta (si existe)
        if (cbRutas.getItemCount() > 0) {
            cbRutas.setSelectedIndex(0);
            cargarDatosRuta();
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

    private void cargarDatosRuta() {
        String selectedRuta = (String) cbRutas.getSelectedItem();
        if (selectedRuta != null) {
            String idRuta = selectedRuta.split(":")[0];
            Ruta ruta = GestorRutas.getInstance().getRutas().stream()
                    .filter(r -> r.getIdRuta().equals(idRuta))
                    .findFirst()
                    .orElse(null);
            if (ruta != null) {
                txtOrigen.setText(ruta.getEstacionOrigen());
                txtDestino.setText(ruta.getEstacionDestino());
                txtDistancia.setText(String.valueOf(ruta.getDistancia()));
                cbEstado.setSelectedItem(ruta.getEstado());
            }
        }
    }

    private void modificarRuta() {
        String selectedRuta = (String) cbRutas.getSelectedItem();
        if (selectedRuta == null) {
            JOptionPane.showMessageDialog(frame, "Seleccione una ruta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRuta = selectedRuta.split(":")[0];
        String origen = txtOrigen.getText().trim();
        String destino = txtDestino.getText().trim();
        String distanciaStr = txtDistancia.getText().trim();
        String estado = (String) cbEstado.getSelectedItem();

        if (origen.isEmpty() || destino.isEmpty() || distanciaStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double distancia;
        try {
            distancia = Double.parseDouble(distanciaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Distancia inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = GestorRutas.getInstance().modificarRuta(idRuta, origen, destino, distancia, estado);
        if (exito) {
            JOptionPane.showMessageDialog(frame, "Ruta modificada exitosamente - ID: " + idRuta);
            frame.setContentPane(new ModificarEliminarRutaPanel(frame));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(frame, "No se pudo modificar la ruta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarRuta() {
        String selectedRuta = (String) cbRutas.getSelectedItem();
        if (selectedRuta == null) {
            JOptionPane.showMessageDialog(frame, "Seleccione una ruta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String idRuta = selectedRuta.split(":")[0];
        int confirm = JOptionPane.showConfirmDialog(frame, "¿Eliminar ruta " + idRuta + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean exito = GestorRutas.getInstance().eliminarRuta(idRuta);
            if (exito) {
                JOptionPane.showMessageDialog(frame, "Ruta eliminada exitosamente - ID: " + idRuta);
                frame.setContentPane(new ModificarEliminarRutaPanel(frame));
                frame.revalidate();
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo eliminar la ruta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}