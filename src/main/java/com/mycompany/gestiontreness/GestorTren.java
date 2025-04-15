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

public class GestorTren {
    private List<Tren> trenes;

    public GestorTren() {
        trenes = new ArrayList<>();
    }

    public void agregarTren(Tren tren) {
        trenes.add(tren);
    }

    public void eliminarTren(String id) {
        trenes.removeIf(tren -> tren.getId().equals(id));
    }

    public List<Tren> listarTrenes() {
        return trenes;
    }
}
