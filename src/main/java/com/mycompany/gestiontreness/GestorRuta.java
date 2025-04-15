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

public class GestorRuta {
    private List<Ruta> rutas;

    public GestorRuta() {
        rutas = new ArrayList<>(); // Inicializar la lista de rutas
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta); // Agregar la ruta a la lista
    }

    public List<Ruta> listarRutas() {
        return rutas; // Retornar la lista de rutas
    }
}