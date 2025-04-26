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

    private GestorRutas() {
        rutas = new ArrayList<>();
        System.out.println("Nueva instancia de GestorRutas creada");
    }

    public static synchronized GestorRutas getInstance() {
        if (instance == null) {
            instance = new GestorRutas();
        }
        return instance;
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
        System.out.println("Ruta agregada - ID: " + ruta.getIdRuta() + ", Total rutas: " + rutas.size());
    }

    public boolean eliminarRuta(String idRuta) {
        boolean removed = rutas.removeIf(r -> r.getIdRuta().equals(idRuta));
        if (removed) {
            System.out.println("Ruta eliminada - ID: " + idRuta + ", Total rutas: " + rutas.size());
        } else {
            System.out.println("No se encontró ruta con ID: " + idRuta);
        }
        return removed;
    }

    public void modificarRuta(String idRuta, String nuevoOrigen, String nuevoDestino, 
                            double nuevaDistancia, String nuevoEstado) {
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta().equals(idRuta)) {
                ruta.setEstacionOrigen(nuevoOrigen);
                ruta.setEstacionDestino(nuevoDestino);
                ruta.setDistancia(nuevaDistancia);
                ruta.setEstado(nuevoEstado);
                System.out.println("Ruta modificada - ID: " + idRuta + ", Nuevo origen: " + nuevoOrigen);
                return;
            }
        }
        System.out.println("No se encontró ruta con ID: " + idRuta + " para modificar");
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }

    public List<Ruta> getRutasOptimas() {
        List<Ruta> optimas = new ArrayList<>();
        for (Ruta ruta : rutas) {
            if ("Óptima".equals(ruta.getEstado())) {
                optimas.add(ruta);
            }
        }
        System.out.println("Obteniendo rutas óptimas - Total: " + optimas.size());
        return optimas;
    }

    public Ruta encontrarRutaMasCorta(String origen, String destino) {
        Ruta rutaMasCorta = null;
        double menorDistancia = Double.MAX_VALUE;

        for (Ruta ruta : rutas) {
            if (ruta.getEstacionOrigen().equalsIgnoreCase(origen) && 
                ruta.getEstacionDestino().equalsIgnoreCase(destino)) {
                if (ruta.getDistancia() < menorDistancia) {
                    menorDistancia = ruta.getDistancia();
                    rutaMasCorta = ruta;
                }
            }
        }

        if (rutaMasCorta != null) {
            System.out.println("Ruta más corta encontrada - ID: " + rutaMasCorta.getIdRuta() + 
                              ", Origen: " + origen + ", Destino: " + destino + 
                              ", Distancia: " + rutaMasCorta.getDistancia());
        } else {
            System.out.println("No se encontró ruta directa de " + origen + " a " + destino);
        }

        return rutaMasCorta;
    }

    public void printRutas() {
        System.out.println("Lista de rutas:");
        if (rutas.isEmpty()) {
            System.out.println("  (Vacía)");
        } else {
            for (Ruta r : rutas) {
                System.out.println("  ID: " + r.getIdRuta() + ", Origen: " + r.getEstacionOrigen() +
                                   ", Destino: " + r.getEstacionDestino() + ", Distancia: " + r.getDistancia() +
                                   ", Estado: " + r.getEstado());
            }
        }
    }
}