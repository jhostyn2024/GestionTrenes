/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.*;

public class GestorRutas {
    private static GestorRutas instance;
    private List<Ruta> rutas;

    private GestorRutas() {
        rutas = new ArrayList<>();
        inicializarRutas();
    }

    public static synchronized GestorRutas getInstance() {
        if (instance == null) {
            instance = new GestorRutas();
        }
        return instance;
    }

    private void inicializarRutas() {
        // Añadir rutas predefinidas (solo una dirección para evitar duplicados)
        rutas.add(new Ruta("RUTA-1", "Cabecera del Llano", "San Francisco", 30.0, "Activa"));
        rutas.add(new Ruta("RUTA-2", "Cabecera del Llano", "La Universidad", 40.0, "Activa"));
        rutas.add(new Ruta("RUTA-3", "Cabecera del Llano", "Sotomayor", 50.0, "Activa"));
        rutas.add(new Ruta("RUTA-4", "Cabecera del Llano", "La Concordia", 50.0, "Activa"));
        rutas.add(new Ruta("RUTA-5", "La Universidad", "Girardot", 80.0, "Activa"));
        rutas.add(new Ruta("RUTA-6", "La Universidad", "La Joya", 120.0, "Activa"));
        rutas.add(new Ruta("RUTA-7", "La Universidad", "Kennedy", 110.0, "Activa"));
        rutas.add(new Ruta("RUTA-8", "Sotomayor", "Provenza", 20.0, "Activa"));
        rutas.add(new Ruta("RUTA-9", "Provenza", "La Concordia", 65.0, "Activa"));
        rutas.add(new Ruta("RUTA-10", "La Concordia", "Diamante II", 80.0, "Activa"));
        rutas.add(new Ruta("RUTA-11", "Diamante II", "Mutis", 30.0, "Activa"));
        rutas.add(new Ruta("RUTA-12", "Diamante II", "Girardot", 145.0, "Activa"));
    }

    public void agregarRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    public boolean eliminarRuta(String idRuta) {
        return rutas.removeIf(r -> r.getIdRuta().equals(idRuta));
    }

    public boolean modificarRuta(String idRuta, String estacionOrigen, String estacionDestino, double distancia, String estado) {
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta().equals(idRuta)) {
                ruta.setEstacionOrigen(estacionOrigen);
                ruta.setEstacionDestino(estacionDestino);
                ruta.setDistancia(distancia);
                ruta.setEstado(estado);
                return true;
            }
        }
        return false;
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }

    public List<Ruta> getRutasOptimas() {
        return rutas.stream()
                .filter(r -> r.getEstado().equals("Activa"))
                .collect(Collectors.toList());
    }

    public Ruta encontrarRutaMasCorta(String origen, String destino) {
        // Construir grafo
        Map<String, List<Ruta>> grafo = new HashMap<>();
        for (Ruta ruta : rutas) {
            grafo.computeIfAbsent(ruta.getEstacionOrigen(), k -> new ArrayList<>()).add(ruta);
            // Añadir ruta inversa para bidireccionalidad
            Ruta inversa = new Ruta(ruta.getIdRuta() + "-INV", ruta.getEstacionDestino(), ruta.getEstacionOrigen(),
                    ruta.getDistancia(), ruta.getEstado());
            grafo.computeIfAbsent(ruta.getEstacionDestino(), k -> new ArrayList<>()).add(inversa);
        }

        // Algoritmo de Dijkstra
        Map<String, Double> distancias = new HashMap<>();
        Map<String, Ruta> rutasPrevias = new HashMap<>();
        PriorityQueue<String> cola = new PriorityQueue<>(Comparator.comparingDouble(distancias::get));
        Set<String> visitados = new HashSet<>();

        distancias.put(origen, 0.0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            String actual = cola.poll();
            if (visitados.contains(actual)) continue;
            visitados.add(actual);

            if (actual.equals(destino)) break;

            List<Ruta> vecinos = grafo.getOrDefault(actual, Collections.emptyList());
            for (Ruta ruta : vecinos) {
                String vecino = ruta.getEstacionDestino();
                double nuevaDistancia = distancias.get(actual) + ruta.getDistancia();

                if (!distancias.containsKey(vecino) || nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    rutasPrevias.put(vecino, ruta);
                    cola.add(vecino);
                }
            }
        }

        // Reconstruir ruta
        if (!distancias.containsKey(destino)) return null;

        List<Ruta> camino = new ArrayList<>();
        String actual = destino;
        while (rutasPrevias.containsKey(actual)) {
            Ruta ruta = rutasPrevias.get(actual);
            camino.add(ruta);
            actual = ruta.getEstacionOrigen();
        }
        Collections.reverse(camino);

        // Devolver la primera ruta del camino (simplificación para VentaBoletosPanel)
        return camino.isEmpty() ? null : camino.get(0);
    }
}