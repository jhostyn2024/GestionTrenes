/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RutaMasCortaPanel extends JPanel {
    private JFrame frame;
    private JList<Ruta> listaRutas;
    private DefaultListModel<Ruta> listModel;
    private JTextField txtOrigen;
    private JTextField txtDestino;

    public RutaMasCortaPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("GESTIÓN DE RUTAS MÁS CORTAS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new MainMenuPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Formulario para buscar ruta más corta
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        formPanel.setBackground(new Color(244, 244, 244));

        JLabel lblOrigen = new JLabel("Origen:");
        txtOrigen = new JTextField(15);
        JLabel lblDestino = new JLabel("Destino:");
        txtDestino = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar Ruta Más Corta");
        btnBuscar.setBackground(new Color(39, 174, 96));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(this::buscarRutaMasCorta);

        formPanel.add(lblOrigen);
        formPanel.add(txtOrigen);
        formPanel.add(lblDestino);
        formPanel.add(txtDestino);
        formPanel.add(btnBuscar);

        add(formPanel, BorderLayout.SOUTH);

        // Lista de rutas
        listModel = new DefaultListModel<>();
        List<Ruta> rutas = GestorRutas.getInstance().getRutas();
        System.out.println("Cargando rutas en RutaMasCortaPanel - Total: " + rutas.size());
        rutas.forEach(listModel::addElement);

        listaRutas = new JList<>(listModel);
        listaRutas.setCellRenderer(new RutaListRenderer());
        listaRutas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaRutas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones administrativos
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));

        JButton btnMarcarOptima = new JButton("Marcar como Óptima");
        btnMarcarOptima.setBackground(new Color(52, 152, 219));
        btnMarcarOptima.setForeground(Color.WHITE);
        btnMarcarOptima.addActionListener(this::marcarComoOptima);

        JButton btnEditar = new JButton("Editar Ruta");
        btnEditar.setBackground(new Color(241, 196, 15));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.addActionListener(this::editarRuta);

        buttonPanel.add(btnMarcarOptima);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnEditar);

        add(buttonPanel, BorderLayout.EAST);
    }

    private void buscarRutaMasCorta(ActionEvent e) {
        String origen = txtOrigen.getText().trim();
        String destino = txtDestino.getText().trim();

        if (origen.isEmpty() || destino.isEmpty()) {
            JOptionPane.showMessageDialog(frame, 
                "Por favor, ingrese origen y destino", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        Ruta rutaMasCorta = GestorRutas.getInstance().encontrarRutaMasCorta(origen, destino);
        if (rutaMasCorta != null) {
            listModel.clear();
            listModel.addElement(rutaMasCorta);
            JOptionPane.showMessageDialog(frame, 
                "Ruta más corta encontrada: " + rutaMasCorta.getEstacionOrigen() + 
                " a " + rutaMasCorta.getEstacionDestino() + ", Distancia: " + 
                rutaMasCorta.getDistancia() + " km", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, 
                "No se encontró una ruta directa de " + origen + " a " + destino, 
                "Sin resultados", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void marcarComoOptima(ActionEvent e) {
        Ruta seleccionada = listaRutas.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione una ruta para marcar como óptima", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        GestorRutas.getInstance().modificarRuta(
            seleccionada.getIdRuta(), 
            seleccionada.getEstacionOrigen(), 
            seleccionada.getEstacionDestino(), 
            seleccionada.getDistancia(), 
            "Óptima"
        );

        JOptionPane.showMessageDialog(frame, 
            "Ruta marcada como óptima correctamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);

        // Actualizar lista
        listModel.clear();
        GestorRutas.getInstance().getRutas().forEach(listModel::addElement);
    }

    private void editarRuta(ActionEvent e) {
        Ruta seleccionada = listaRutas.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione una ruta para editar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        frame.setContentPane(new EditarRutaPanel(frame, seleccionada));
        frame.revalidate();
    }

    private static class RutaListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Ruta) {
                Ruta r = (Ruta) value;
                setText(String.format("ID: %s - %s a %s, Distancia: %.2f km, Estado: %s", 
                    r.getIdRuta(), r.getEstacionOrigen(), r.getEstacionDestino(), 
                    r.getDistancia(), r.getEstado()));
            }
            return this;
        }
    }
}