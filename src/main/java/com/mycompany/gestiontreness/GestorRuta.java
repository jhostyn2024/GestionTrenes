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

public class GestorRuta {
    private List<Ruta> rutas;
    private final File archivo = new File("rutas.json");
    private final ObjectMapper mapper = new ObjectMapper();

    public GestorRuta() {
        rutas = cargarRutas();
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
        guardarRutas();
    }

    public List<Ruta> listarRutas() {
        return rutas;
    }

    private void guardarRutas() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, rutas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Ruta> cargarRutas() {
        if (!archivo.exists()) return new ArrayList<>();
        try {
            return mapper.readValue(archivo, new TypeReference<List<Ruta>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
