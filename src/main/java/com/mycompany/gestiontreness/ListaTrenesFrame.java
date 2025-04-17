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
import java.util.List;

public class ListaTrenesFrame extends JFrame {
    private JPanel contenedor;
    private List<Tren> trenes;

    public ListaTrenesFrame() {
        setTitle("Eliminar o Modificar Trenes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        trenes = TrenService.cargarTrenes();
        contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contenedor);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnAgregar = new JButton("Agregar Tren");
        btnAgregar.addActionListener(e -> agregarTren());
        add(btnAgregar, BorderLayout.SOUTH);

        refrescarPaneles();
    }

    private void agregarTren() {
        String marca = JOptionPane.showInputDialog("Marca del tren:");
        if (marca == null) return;

        String id = JOptionPane.showInputDialog("Identificador:");
        if (id == null) return;

        String capacidadStr = JOptionPane.showInputDialog("Capacidad:");
        if (capacidadStr == null) return;

        try {
            int capacidad = Integer.parseInt(capacidadStr);
            Tren tren = new Tren(marca, id, capacidad);
            trenes.add(tren);
            TrenService.guardarTrenes(trenes);
            refrescarPaneles();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Capacidad inválida.");
        }
    }

    private void refrescarPaneles() {
        contenedor.removeAll();
        for (Tren tren : trenes) {
            contenedor.add(new PanelTren(tren, trenes, contenedor, this::refrescarPaneles));
        }
        contenedor.revalidate();
        contenedor.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ListaTrenesFrame().setVisible(true());
    }
}
