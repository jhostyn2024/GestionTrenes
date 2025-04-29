/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;

public class GestorRutas {
    private static GestorRutas instance;
    private List<Ruta> rutas;
    private static final String FILE_PATH = "data/rutas.json";

    private GestorRutas() {
        rutas = JsonUtil.readJson(FILE_PATH, Ruta.class);
        System.out.println("GestorRutas inicializado con " + rutas.size() + " rutas cargadas");
    }

    public static synchronized GestorRutas getInstance() {
        if (instance == null) {
            instance = new GestorRutas();
        }
        return instance;
    }

    public void agregarRuta(Ruta ruta) {
        System.out.println("Agregando ruta con ID: " + ruta.getIdRuta());
        rutas.add(ruta);
        JsonUtil.writeJson(FILE_PATH, rutas);
        System.out.println("Rutas almacenadas: " + rutas.size());
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }

    public List<Ruta> getRutasOptimas() {
        return new ArrayList<>(rutas);
    }

    public Ruta encontrarRutaMasCorta(String origen, String destino) {
        return rutas.stream()
                .filter(r -> r.getEstacionOrigen().equalsIgnoreCase(origen) && 
                             r.getEstacionDestino().equalsIgnoreCase(destino))
                .findFirst()
                .orElse(null);
    }

    void modificarRuta(String idRuta, String estacionOrigen, String estacionDestino, double distancia, String estado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean eliminarRuta(String idRuta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}