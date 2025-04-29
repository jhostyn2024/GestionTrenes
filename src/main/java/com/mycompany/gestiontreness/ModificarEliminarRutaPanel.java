/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;

public class ModificarEliminarRutaPanel extends JPanel {
    private JFrame frame;
    private JTextField txtIdRuta, txtOrigen, txtDestino, txtDistancia;
    private JComboBox<String> cbEstado;
    private JTextArea txtResultado;
    private final Color BLUE_COLOR = new Color(0, 86, 179);
    private final Color GOLD_COLOR = new Color(198, 168, 77);

    public ModificarEliminarRutaPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel();
        header.setBackground(BLUE_COLOR);
        header.setPreferredSize(new Dimension(800, 100));
        JLabel title = new JLabel("MODIFICAR/ELIMINAR RUTA", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);
        add(header, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        txtIdRuta = new JTextField();
        txtOrigen = new JTextField();
        txtDestino = new JTextField();
        txtDistancia = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"Activa", "Inactiva"});
        txtResultado = new JTextArea(5, 20);
        txtResultado.setEditable(false);
        txtResultado.setFont(new Font("Arial", Font.PLAIN, 14));

        addFormField(formPanel, "ID Ruta:", txtIdRuta);
        addFormField(formPanel, "Origen:", txtOrigen);
        addFormField(formPanel, "Destino:", txtDestino);
        addFormField(formPanel, "Distancia (km):", txtDistancia);
        addFormField(formPanel, "Estado:", cbEstado);

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBackground(GOLD_COLOR);
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        btnBuscar.addActionListener(e -> buscarRuta());

        JButton btnModificar = new JButton("MODIFICAR");
        btnModificar.setBackground(GOLD_COLOR);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 16));
        btnModificar.addActionListener(e -> modificarRuta());

        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setBackground(new Color(200, 50, 50));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.addActionListener(e -> eliminarRuta());

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 16));
        btnVolver.addActionListener(e -> {
            System.out.println("Navegando a MainMenuPanel desde ModificarEliminarRutaPanel");
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

    private void buscarRuta() {
        try {
            String idRuta = txtIdRuta.getText().trim();
            if (idRuta.isEmpty()) {
                txtResultado.setText("Error: Ingrese el ID de la ruta.");
                System.out.println("ID de ruta vacío en buscarRuta");
                return;
            }

            GestorRutas gestor = GestorRutas.getInstance();
            Ruta ruta = gestor.getRutas().stream()
                    .filter(r -> r.getIdRuta().equals(idRuta))
                    .findFirst()
                    .orElse(null);

            if (ruta == null) {
                txtResultado.setText("Error: No se encontró una ruta con ID: " + idRuta);
                System.out.println("Ruta no encontrada: " + idRuta);
                return;
            }

            txtOrigen.setText(ruta.getEstacionOrigen());
            txtDestino.setText(ruta.getEstacionDestino());
            txtDistancia.setText(String.valueOf(ruta.getDistancia()));
            cbEstado.setSelectedItem(ruta.getEstado());
            txtResultado.setText("Ruta encontrada:\nID: " + idRuta + "\nOrigen: " + ruta.getEstacionOrigen() + "\nDestino: " + ruta.getEstacionDestino() + "\nDistancia: " + ruta.getDistancia() + " km\nEstado: " + ruta.getEstado());
            System.out.println("Ruta encontrada: " + idRuta);
        } catch (Exception e) {
            txtResultado.setText("Error al buscar ruta: " + e.getMessage());
            System.err.println("Excepción en buscarRuta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void modificarRuta() {
        try {
            String idRuta = txtIdRuta.getText().trim();
            String origen = txtOrigen.getText().trim();
            String destino = txtDestino.getText().trim();
            String distanciaStr = txtDistancia.getText().trim();
            String estado = (String) cbEstado.getSelectedItem();

            if (idRuta.isEmpty() || origen.isEmpty() || destino.isEmpty() || distanciaStr.isEmpty()) {
                txtResultado.setText("Error: Complete todos los campos.");
                System.out.println("Campos incompletos en modificarRuta");
                return;
            }

            if (!origen.matches("[a-zA-Z ]+") || !destino.matches("[a-zA-Z ]+")) {
                txtResultado.setText("Error: Origen y Destino solo deben contener letras y espacios.");
                System.out.println("Origen o Destino inválido en modificarRuta");
                return;
            }

            double distancia;
            try {
                distancia = Double.parseDouble(distanciaStr);
                if (distancia <= 0) {
                    txtResultado.setText("Error: La distancia debe ser mayor a 0.");
                    System.out.println("Distancia inválida en modificarRuta");
                    return;
                }
            } catch (NumberFormatException e) {
                txtResultado.setText("Error: La distancia debe ser un número válido.");
                System.out.println("Formato de distancia inválido en modificarRuta");
                return;
            }

            GestorRutas gestor = GestorRutas.getInstance();
            boolean modificado = gestor.modificarRuta(idRuta, origen, destino, distancia, estado);
            if (modificado) {
                txtResultado.setText("Ruta modificada exitosamente:\nID: " + idRuta + "\nOrigen: " + origen + "\nDestino: " + destino + "\nDistancia: " + distancia + " km\nEstado: " + estado);
                System.out.println("Ruta modificada: " + idRuta);
                limpiarFormulario();
            } else {
                txtResultado.setText("Error: No se encontró una ruta con ID: " + idRuta);
                System.out.println("Ruta no encontrada para modificar: " + idRuta);
            }
        } catch (Exception e) {
            txtResultado.setText("Error al modificar ruta: " + e.getMessage());
            System.err.println("Excepción en modificarRuta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void eliminarRuta() {
        try {
            String idRuta = txtIdRuta.getText().trim();
            if (idRuta.isEmpty()) {
                txtResultado.setText("Error: Ingrese el ID de la ruta.");
                System.out.println("ID de ruta vacío en eliminarRuta");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(frame, "¿Está seguro de que desea eliminar la ruta con ID: " + idRuta + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                txtResultado.setText("Eliminación cancelada.");
                System.out.println("Eliminación cancelada para ruta: " + idRuta);
                return;
            }

            GestorRutas gestor = GestorRutas.getInstance();
            boolean eliminado = gestor.eliminarRuta(idRuta);
            if (eliminado) {
                txtResultado.setText("Ruta eliminada exitosamente: ID: " + idRuta);
                System.out.println("Ruta eliminada: " + idRuta);
                limpiarFormulario();
            } else {
                txtResultado.setText("Error: No se encontró una ruta con ID: " + idRuta);
                System.out.println("Ruta no encontrada para eliminar: " + idRuta);
            }
        } catch (Exception e) {
            txtResultado.setText("Error al eliminar ruta: " + e.getMessage());
            System.err.println("Excepción en eliminarRuta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        txtIdRuta.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        txtDistancia.setText("");
        cbEstado.setSelectedIndex(0);
    }
}