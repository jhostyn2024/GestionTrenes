/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorVagones {
    private static GestorVagones instance;
    private List<Vagon> vagones;

    private GestorVagones() {
        vagones = new ArrayList<>();
        inicializarVagones();
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
        }
        return instance;
    }

    private void inicializarVagones() {
        // Ejemplo: Vagones para TREN-1 y TREN-2
        vagones.add(new Vagon("VAGON-1", "Carga", "TREN-1"));
        vagones.add(new Vagon("VAGON-2", "Pasajeros", "TREN-1"));
        vagones.add(new Vagon("VAGON-3", "Pasajeros", "TREN-1"));
        vagones.add(new Vagon("VAGON-4", "Carga", "TREN-2"));
        vagones.add(new Vagon("VAGON-5", "Pasajeros", "TREN-2"));
        vagones.add(new Vagon("VAGON-6", "Pasajeros", "TREN-2"));
    }

    public List<Vagon> getVagones() {
        return new ArrayList<>(vagones);
    }

    public List<Vagon> getVagonesPorTren(String idTren) {
        return vagones.stream()
                .filter(v -> v.getIdTren().equals(idTren))
                .collect(Collectors.toList());
    }

    public boolean validarProporcionVagones(String idTren) {
        List<Vagon> vagonesTren = getVagonesPorTren(idTren);
        long vagonesCarga = vagonesTren.stream().filter(v -> v.getTipo().equals("Carga")).count();
        long vagonesPasajeros = vagonesTren.stream().filter(v -> v.getTipo().equals("Pasajeros")).count();
        return vagonesCarga * 2 >= vagonesPasajeros;
    }

    public void agregarVagon(Vagon vagon) {
        vagones.add(vagon);
    }
}