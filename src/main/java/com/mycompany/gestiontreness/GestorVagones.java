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

public class GestorVagones {
    private static GestorVagones instance;
    private List<Vagon> vagones;

    private GestorVagones() {
        vagones = new ArrayList<>();
    }

    public static synchronized GestorVagones getInstance() {
        if (instance == null) {
            instance = new GestorVagones();
        }
        return instance;
    }

    public void agregarVagon(Vagon vagon) {
        vagones.add(vagon);
    }

    public List<Vagon> getVagones() {
        return new ArrayList<>(vagones);
    }
}