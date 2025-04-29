/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorTrenes {
    private static GestorTrenes instance;
    private List<Tren> trenes;
    private static final String FILE_PATH = "data/trenes.json";

    private GestorTrenes() {
        trenes = JsonUtil.readJson(FILE_PATH, Tren.class);
        System.out.println("GestorTrenes inicializado con " + trenes.size() + " trenes cargados");
    }

    public static synchronized GestorTrenes getInstance() {
        if (instance == null) {
            instance = new GestorTrenes();
        }
        return instance;
    }

    public void agregarTren(Tren tren) {
        System.out.println("Agregando tren con ID: " + tren.getIdTren());
        trenes.add(tren);
        JsonUtil.writeJson(FILE_PATH, trenes);
        System.out.println("Trenes almacenados: " + trenes.size() + " [" + 
            trenes.stream().map(Tren::getIdTren).collect(Collectors.joining(", ")) + "]");
    }

    public List<Tren> getTrenes() {
        return new ArrayList<>(trenes);
    }

    boolean modificarTren(String idTren, String nombre, double kilometraje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean eliminarTren(String idTren) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}