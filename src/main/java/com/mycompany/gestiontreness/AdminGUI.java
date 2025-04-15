/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI {
    private JFrame frame;
    private GestorTren gestorTren;
    private GestorRuta gestorRuta;

    public AdminGUI() {
        gestorTren = new GestorTren();
        gestorRuta = new GestorRuta();
        frame = new JFrame("Administración del Sistema de Trenes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(0, 1)); // Layout vertical

        // Botón para agregar tren
        JButton btnAgregarTren = new JButton("Agregar Tren");
        btnAgregarTren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarTren();
            }
        });
        frame.add(btnAgregarTren);

        // Botón para listar trenes
        JButton btnListarTrenes = new JButton("Listar Trenes");
        btnListarTrenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarTrenes();
            }
        });
        frame.add(btnListarTrenes);

        // Botón para agregar ruta
        JButton btnAgregarRuta = new JButton("Agregar Ruta");
        btnAgregarRuta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRuta();
            }
        });
        frame.add(btnAgregarRuta);

        // Botón para listar rutas
        JButton btnListarRutas = new JButton("Listar Rutas");
        btnListarRutas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarRutas();
            }
        });
        frame.add(btnListarRutas);

        // Botón para cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Cerrar la ventana de administración
                new LoginGUI(); // Volver a la ventana de login
            }
        });
        frame.add(btnCerrarSesion);

        // Configurar el tamaño y hacer que se ajuste al contenido
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }

    private void agregarTren() {
        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        Object[] message = {
            "ID del Tren:", txtId,
            "Nombre del Tren:", txtNombre
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Agregar Tren", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            gestorTren.agregarTren(new Tren(id, nombre));
            JOptionPane.showMessageDialog(frame, "Tren agregado exitosamente.");
        }
    }

    private void listarTrenes() {
        StringBuilder trenesList = new StringBuilder("Trenes:\n");
        for (Tren tren : gestorTren.listarTrenes()) {
            trenesList.append("ID: ").append(tren.getId()).append(", Nombre: ").append(tren.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, trenesList.toString());
    }

    private void agregarRuta() {
        JTextField txtId = new JTextField();
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        JTextField txtDistancia = new JTextField();
        JTextField txtHorario = new JTextField();

        Object[] message = {
            "ID de la Ruta:", txtId,
            "Origen:", txtOrigen,
            "Destino:", txtDestino,
            "Distancia (km):", txtDistancia,
            "Horario:", txtHorario
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Agregar Ruta", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = txtId.getText();
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            int distancia = Integer.parseInt(txtDistancia.getText());
            String horario = txtHorario.getText();

                        // Crear y agregar la nueva ruta
            Ruta nuevaRuta = new Ruta(id, origen, destino, distancia, horario);
            gestorRuta.agregarRuta(nuevaRuta);
            JOptionPane.showMessageDialog(frame, "Ruta agregada exitosamente.");
        }
    }

    private void listarRutas() {
        StringBuilder rutasList = new StringBuilder("Rutas:\n");
        for (Ruta ruta : gestorRuta.listarRutas()) {
            rutasList.append("ID: ").append(ruta.getId())
                     .append(", Origen: ").append(ruta.getOrigen())
                     .append(", Destino: ").append(ruta.getDestino())
                     .append(", Distancia: ").append(ruta.getDistancia())
                     .append(" km, Horario: ").append(ruta.getHorario())
                     .append("\n");
        }
        JOptionPane.showMessageDialog(frame, rutasList.toString());
    }

    public static void main(String[] args) {
        new AdminGUI(); // Iniciar la interfaz administrativa
    }
}