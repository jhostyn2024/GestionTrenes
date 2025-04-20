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

public class GestorRutas {
    private static GestorRutas instance;
    private List<Ruta> rutas;

    private GestorRutas() {
        rutas = new ArrayList<>();
        // Datos de ejemplo (opcional)
        rutas.add(new Ruta("RUTA-001", "Lima", "Arequipa", 1005.5, "Activa"));
    }

    public static synchronized GestorRutas getInstance() {
        if (instance == null) {
            instance = new GestorRutas();
        }
        return instance;
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }
}