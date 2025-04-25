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
import java.util.concurrent.CopyOnWriteArrayList; // Para seguridad en hilos

public class GestorVagones {
    private static GestorVagones instance;
    private final List<Vagon> vagones; // final para evitar reasignación

    private GestorVagones() {
        vagones = new CopyOnWriteArrayList<>(); // Lista thread-safe
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
        }
        return instance;
    }

    public boolean agregarVagon(Vagon vagon) {
        return vagones.add(vagon); // Retorna true si se añade
    }

    public boolean eliminarVagon(String idVagon) {
        return vagones.removeIf(v -> v.getIdVagon().equals(idVagon));
    }

    public boolean actualizarVagon(String idOriginal, Vagon vagonActualizado) {
        for (int i = 0; i < vagones.size(); i++) {
            if (vagones.get(i).getIdVagon().equals(idOriginal)) {
                vagones.set(i, vagonActualizado);
                return true;
            }
        }
        return false;
    }

    public List<Vagon> getVagones() {
        return new ArrayList<>(vagones); // Copia defensiva
    }

    public Vagon buscarVagon(String idVagon) {
        return vagones.stream()
                     .filter(v -> v.getIdVagon().equals(idVagon))
                     .findFirst()
                     .orElse(null);
    }
}