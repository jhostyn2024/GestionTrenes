/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void eliminarTren(Tren tren) {
        trenes.remove(tren);
    }

    public List<Tren> obtenerTrenes() {
        return trenes;
    }
}

