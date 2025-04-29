/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;

public class GestorTrenes {
    private static GestorTrenes instance;
    private List<Tren> trenes;

    private GestorTrenes() {
        trenes = new ArrayList<>();
        inicializarTrenes();
    }

    public static synchronized GestorTrenes getInstance() {
        if (instance == null) {
            instance = new GestorTrenes();
        }
        return instance;
    }

    private void inicializarTrenes() {
        trenes.add(new Tren("TREN-1", "Expreso Llano", "Mercedes-Benz", 0.0));
        trenes.add(new Tren("TREN-2", "Rápido Universidad", "Arnold", 0.0));
    }

    public void agregarTren(Tren tren) {
        trenes.add(tren);
    }

    public boolean eliminarTren(String idTren) {
        return trenes.removeIf(t -> t.getIdTren().equals(idTren));
    }

    public boolean modificarTren(String idTren, String nombre, double kilometraje) {
        for (Tren tren : trenes) {
            if (tren.getIdTren().equals(idTren)) {
                tren.setNombre(nombre);
                tren.setKilometraje(kilometraje);
                return true;
            }
        }
        return false;
    }

    public List<Tren> getTrenes() {
        return new ArrayList<>(trenes);
    }
}