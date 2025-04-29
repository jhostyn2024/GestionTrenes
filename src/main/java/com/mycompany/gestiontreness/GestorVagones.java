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
    private static final String FILE_PATH = "data/vagones.json";

    private GestorVagones() {
        vagones = JsonUtil.readJson(FILE_PATH, Vagon.class);
        System.out.println("GestorVagones inicializado con " + vagones.size() + " vagones cargados");
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
        }
        return instance;
    }

    public void agregarVagon(Vagon vagon) {
        System.out.println("Agregando vagón con ID: " + vagon.getIdVagon());
        vagones.add(vagon);
        JsonUtil.writeJson(FILE_PATH, vagones);
        System.out.println("Vagones almacenados: " + vagones.size() + " [" + 
            vagones.stream().map(Vagon::getIdVagon).collect(Collectors.joining(", ")) + "]");
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
        long pasajeros = vagonesTren.stream().filter(v -> v.getTipo().equals("Pasajeros")).count();
        long carga = vagonesTren.stream().filter(v -> v.getTipo().equals("Carga")).count();
        return pasajeros >= 2 * carga; // Proporción 1:2
    }

    void printVagones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean eliminarVagon(String idVagon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}