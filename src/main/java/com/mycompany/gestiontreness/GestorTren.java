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

public class GestorTren {
    private List<Tren> trenes;
    private final File archivo = new File("trenes.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public GestorTren() {
        trenes = cargarTrenes();
    }

    public void agregarTren(Tren tren) {
        trenes.add(tren);
        guardarTrenes();
    }

    public void eliminarTren(String id) {
        trenes.removeIf(tren -> tren.getId().equals(id));
        guardarTrenes();
    }

    public List<Tren> listarTrenes() {
        return trenes;
    }

    private void guardarTrenes() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, trenes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Tren> cargarTrenes() {
        if (!archivo.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(archivo, new TypeReference<List<Tren>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

