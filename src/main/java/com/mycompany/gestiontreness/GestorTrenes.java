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

public class GestorTrenes {
    private static GestorTrenes instance;
    private List<Tren> trenes;

    private GestorTrenes() {
        trenes = new ArrayList<>();
        // Datos de ejemplo (opcional)
        trenes.add(new Tren("Alstom", "TR-001", 150, "08:00", "10:30", 5, "Ruta Norte"));
    }

    public static GestorTrenes getInstance() {
        if (instance == null) {
            instance = new GestorTrenes();
        }
        return instance;
    }

    public void agregarTren(Tren tren) {
        trenes.add(tren);
    }

    public List<Tren> getTrenes() {
        return new ArrayList<>(trenes);
    }
}