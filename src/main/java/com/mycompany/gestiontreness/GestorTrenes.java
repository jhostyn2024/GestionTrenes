/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorTrenes {
    private static GestorTrenes instance;
    private List<Tren> trenes;
    private static final String FILE_PATH = "data/trenes.json";

    private GestorTrenes() {
        trenes = JsonUtil.readJson(FILE_PATH, Tren.class);
        System.out.println("GestorTrenes inicializado con " + trenes.size() + " trenes cargados");
    }

    public static synchronized GestorTrenes getInstance() {
        if (instance == null) {
            instance = new GestorTrenes();
        }
        return instance;
    }

    public void agregarTren(Tren tren) {
        System.out.println("Agregando tren con ID: " + tren.getIdTren());
        trenes.add(tren);
        JsonUtil.writeJson(FILE_PATH, trenes);
        System.out.println("Trenes almacenados: " + trenes.size());
    }

    public boolean modificarTren(String idTren, String nombre, int capacidad, String estado) {
        System.out.println("Modificando tren con ID: " + idTren);
        for (Tren tren : trenes) {
            if (tren.getIdTren().equals(idTren)) {
                tren.setNombre(nombre);
                tren.setCapacidad(capacidad);
                tren.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, trenes);
                System.out.println("Tren modificado y almacenado: " + idTren);
                return true;
            }
        }
        System.out.println("Tren no encontrado para modificar: " + idTren);
        return false;
    }

    public boolean eliminarTren(String idTren) {
        System.out.println("Eliminando tren con ID: " + idTren);
        boolean removed = trenes.removeIf(tren -> tren.getIdTren().equals(idTren));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, trenes);
            System.out.println("Tren eliminado y almacenado: " + idTren);
        } else {
            System.out.println("Tren no encontrado para eliminar: " + idTren);
        }
        return removed;
    }

    public List<Tren> getTrenes() {
        return new ArrayList<>(trenes);
    }

    public List<Tren> getTrenesActivos() {
        return trenes.stream()
                .filter(tren -> "Activo".equals(tren.getEstado()))
                .collect(Collectors.toList());
    }
}