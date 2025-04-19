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
        // Datos de ejemplo
        trenes.add(new Tren("Alstom", "TR-001", 150, "08:00", "10:30", 5, "Ruta Norte"));
        trenes.add(new Tren("Bombardier", "TR-002", 200, "09:00", "12:15", 6, "Ruta Sur"));
    }

    public static synchronized GestorTrenes getInstance() {
        if (instance == null) {
            instance = new GestorTrenes();
        }
        return instance;
    }

    public void agregarTren(Tren tren) {
        trenes.add(tren);
    }

    public boolean eliminarTren(String identificador) {
        return trenes.removeIf(t -> t.getIdentificador().equals(identificador));
    }

    public Tren buscarTren(String identificador) {
        return trenes.stream()
                .filter(t -> t.getIdentificador().equals(identificador))
                .findFirst()
                .orElse(null);
    }

    public List<Tren> getTrenes() {
        return new ArrayList<>(trenes);
    }

    public boolean actualizarTren(String idOriginal, Tren trenActualizado) {
        for (int i = 0; i < trenes.size(); i++) {
            if (trenes.get(i).getIdentificador().equals(idOriginal)) {
                trenes.set(i, trenActualizado);
                return true;
            }
        }
        return false;
    }
}