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

public class TrenManager {
    private List<Tren> trenes;

    public TrenManager() {
        trenes = new ArrayList<>();
    }

    public void addTren(Tren tren) {
        trenes.add(tren);
    }

    public void removeTren(Tren tren) {
        trenes.remove(tren);
    }

    public List<Tren> getTrenes() {
        return trenes;
    }
}