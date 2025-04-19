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

public class GestionGeneralPanel extends JPanel {

    private JPanel contentPanel;

    public GestionGeneralPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel;
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("MEDINET", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(30, 58, 138));
        headerLabel.setPreferredSize(new Dimension(100, 60));
        add(headerLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        buttonPanel.setBackground(new Color(244, 244, 244));

        JButton trenesButton = new JButton("GESTIÓN TRENES");
        trenesButton.setBackground(new Color(205, 163, 74));
        trenesButton.setForeground(Color.WHITE);
        trenesButton.setFocusPainted(false);
        trenesButton.setFont(new Font("Arial", Font.BOLD, 16));
        trenesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(new GestionTrenesPanel(contentPanel));
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        JButton vagonesButton = new JButton("GESTIÓN VAGONES");
        vagonesButton.setBackground(new Color(205, 163, 74));
        vagonesButton.setForeground(Color.WHITE);
        vagonesButton.setFocusPainted(false);
        vagonesButton.setFont(new Font("Arial", Font.BOLD, 16));
        vagonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                contentPanel.add(new GestionVagonesPanel(contentPanel));
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        buttonPanel.add(trenesButton);
        buttonPanel.add(vagonesButton);

        add(buttonPanel, BorderLayout.CENTER);
    }
}

