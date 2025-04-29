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
        System.out.println("Vagones almacenados: " + vagones.size());
    }

    public boolean modificarVagon(String idVagon, String idTren, String tipo, int capacidad, String estado) {
        System.out.println("Modificando vagón con ID: " + idVagon);
        for (Vagon vagon : vagones) {
            if (vagon.getIdVagon().equals(idVagon)) {
                vagon.setIdTren(idTren);
                vagon.setTipo(tipo);
                vagon.setCapacidad(capacidad);
                vagon.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, vagones);
                System.out.println("Vagón modificado y almacenado: " + idVagon);
                return true;
            }
        }
        System.out.println("Vagón no encontrado para modificar: " + idVagon);
        return false;
    }

    public boolean eliminarVagon(String idVagon) {
        System.out.println("Eliminando vagón con ID: " + idVagon);
        boolean removed = vagones.removeIf(vagon -> vagon.getIdVagon().equals(idVagon));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, vagones);
            System.out.println("Vagón eliminado y almacenado: " + idVagon);
        } else {
            System.out.println("Vagón no encontrado para eliminar: " + idVagon);
        }
        return removed;
    }

    public List<Vagon> getVagones() {
        return new ArrayList<>(vagones);
    }

    public List<Vagon> getVagonesActivos() {
        return vagones.stream()
                .filter(vagon -> "Activo".equals(vagon.getEstado()))
                .collect(Collectors.toList());
    }

    void printVagones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean validarProporcionVagones(String idTren) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getVagonesPorTren(String idTren) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}