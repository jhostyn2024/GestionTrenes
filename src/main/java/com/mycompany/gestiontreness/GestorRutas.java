/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public boolean modificarRuta(String idRuta, String origen, String destino, double distancia, String estado) {
        System.out.println("Modificando ruta con ID: " + idRuta);
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta().equals(idRuta)) {
                ruta.setEstacionOrigen(origen);
                ruta.setEstacionDestino(destino);
                ruta.setDistancia(distancia);
                ruta.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, rutas);
                System.out.println("Ruta modificada y almacenada: " + idRuta);
                return true;
            }
        }
        System.out.println("Ruta no encontrada para modificar: " + idRuta);
        return false;
    }

    public boolean eliminarRuta(String idRuta) {
        System.out.println("Eliminando ruta con ID: " + idRuta);
        boolean removed = rutas.removeIf(ruta -> ruta.getIdRuta().equals(idRuta));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, rutas);
            System.out.println("Ruta eliminada y almacenada: " + idRuta);
        } else {
            System.out.println("Ruta no encontrada para eliminar: " + idRuta);
        }
        return removed;
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }

    public List<Ruta> getRutasOptimas() {
        return rutas.stream()
                .filter(ruta -> "Activa".equals(ruta.getEstado()))
                .collect(Collectors.toList());
    }
}