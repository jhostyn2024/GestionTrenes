/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;

public class GestorVagones {
    private static GestorVagones instance;
    private List<Vagon> vagones;

    private GestorVagones() {
        vagones = new ArrayList<>();
        System.out.println("Nueva instancia de GestorVagones creada");
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
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

    public boolean eliminarVagon(String idVagon) {
        boolean removed = vagones.removeIf(v -> v.getIdVagon().equals(idVagon));
        if (removed) {
            System.out.println("Vagón eliminado - ID: " + idVagon + ", Total vagones: " + vagones.size());
        } else {
            System.out.println("No se encontró vagón con ID: " + idVagon);
        }
        return removed;
    }

    public void printVagones() {
        System.out.println("Lista de vagones:");
        if (vagones.isEmpty()) {
            System.out.println("  (Vacía)");
        } else {
            for (Vagon v : vagones) {
                System.out.println("  ID: " + v.getIdVagon() + ", Total Vagones: " + v.getTotalVagones() +
                                   ", Asientos Totales: " + v.getTotalAsientos());
            }
        }
    }
}