/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ModificarEliminarHorarioPanel extends JPanel {
    private JFrame frame;
    private JList<Horario> listaHorarios;
    private DefaultListModel<Horario> listModel;

    public ModificarEliminarHorarioPanel(JFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(244, 244, 244));
        createUI();
    }

    private void createUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(26, 38, 116));
        header.setPreferredSize(new Dimension(800, 80));

        JLabel title = new JLabel("MODIFICAR/ELIMINAR HORARIOS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        JButton backButton = new JButton("Volver");
        backButton.setBackground(new Color(205, 163, 74));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> {
            frame.setContentPane(new GestionHorariosPanel(frame));
            frame.revalidate();
        });

        header.add(backButton, BorderLayout.WEST);
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        List<Horario> horarios = GestorHorarios.getInstance().getHorarios();
        System.out.println("Cargando horarios en ModificarEliminarHorarioPanel - Total: " + horarios.size());
        horarios.forEach(listModel::addElement);

        listaHorarios = new JList<>(listModel);
        listaHorarios.setCellRenderer(new HorarioListRenderer());
        listaHorarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listaHorarios);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(244, 244, 244));

        JButton modificarBtn = new JButton("Modificar");
        modificarBtn.setBackground(new Color(52, 152, 219));
        modificarBtn.setForeground(Color.WHITE);
        modificarBtn.addActionListener(this::modificarHorario);

        JButton eliminarBtn = new JButton("Eliminar");
        eliminarBtn.setBackground(new Color(231, 76, 60));
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.addActionListener(this::eliminarHorario);

        buttonPanel.add(modificarBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(eliminarBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void modificarHorario(ActionEvent e) {
        Horario seleccionado = listaHorarios.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione un horario para modificar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        frame.setContentPane(new EditarHorarioPanel(frame, seleccionado));
        frame.revalidate();
    }

    private void eliminarHorario(ActionEvent e) {
        Horario seleccionado = listaHorarios.getSelectedValue();
        if (seleccionado == null) {
            JOptionPane.showMessageDialog(frame, 
                "Seleccione un horario para eliminar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            frame, 
            "¿Está seguro de eliminar este horario? (ID: " + seleccionado.getIdHorario() + 
            ", " + seleccionado.getEstacionOrigen() + " - " + seleccionado.getEstacionDestino() + ")", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Intentando eliminar horario - ID: " + seleccionado.getIdHorario());
            GestorHorarios.getInstance().printHorarios(); // Depuración antes
            
            boolean removed = GestorHorarios.getInstance().eliminarHorario(seleccionado.getIdHorario());

            GestorHorarios.getInstance().printHorarios(); // Depuración después
            
            if (removed) {
                listModel.removeElement(seleccionado);
                System.out.println("Horario eliminado de JList - Total elementos: " + listModel.size());
                JOptionPane.showMessageDialog(frame, 
                    "Horario eliminado correctamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Fallo al eliminar horario de GestorHorarios");
                JOptionPane.showMessageDialog(frame, 
                    "Error al eliminar el horario", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static class HorarioListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
                                                     boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Horario) {
                Horario h = (Horario) value;
                setText(String.format("ID: %s - %s a %s, Salida: %s, Llegada: %s, Fecha: %s",
                    h.getIdHorario(), h.getEstacionOrigen(), h.getEstacionDestino(),
                    h.getHoraSalida(), h.getHoraLlegada(), h.getFecha()));
            }
            return this;
        }
    }
}