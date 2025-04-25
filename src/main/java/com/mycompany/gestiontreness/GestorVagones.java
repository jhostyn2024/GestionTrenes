/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */


import java.util.ArrayList;
import java.util.List;

public class GestorVagones {
    private static GestorVagones instance;
    private List<Vagon> vagones;

    private GestorVagones() {
        vagones = new ArrayList<>();
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
            System.out.println("Nueva instancia de GestorVagones creada");
        }
        return instance;
    }

    public void agregarVagon(Vagon vagon) {
        vagones.add(vagon);
        System.out.println("Vagón agregado - ID: " + vagon.getIdVagon() + ", Total vagones: " + vagones.size());
    }

    public List<Vagon> getVagones() {
        return new ArrayList<>(vagones);
    }

    // Método para depuración
    public void printVagones() {
        System.out.println("Lista de vagones:");
        for (Vagon v : vagones) {
            System.out.println("ID: " + v.getIdVagon() + ", Total Vagones: " + v.getTotalVagones() +
                               ", Asientos Totales: " + v.getTotalAsientos());
        }
    }
}