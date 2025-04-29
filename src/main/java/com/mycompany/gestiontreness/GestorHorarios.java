/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorHorarios {
    private static GestorHorarios instance;
    private List<Horario> horarios;
    private static final String FILE_PATH = "data/horarios.json";

    private GestorHorarios() {
        horarios = JsonUtil.readJson(FILE_PATH, Horario.class);
        System.out.println("GestorHorarios inicializado con " + horarios.size() + " horarios cargados");
    }

    public static synchronized GestorHorarios getInstance() {
        if (instance == null) {
            instance = new GestorHorarios();
        }
        return instance;
    }

    public void agregarHorario(Horario horario) {
        System.out.println("Agregando horario con ID: " + horario.getIdHorario());
        horarios.add(horario);
        JsonUtil.writeJson(FILE_PATH, horarios);
        System.out.println("Horarios almacenados: " + horarios.size() + " [" + 
            horarios.stream().map(Horario::getIdHorario).collect(Collectors.joining(", ")) + "]");
    }

    public List<Horario> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> getHorariosPorRuta(String idRuta) {
        return horarios.stream()
                .filter(h -> h.getIdRuta().equals(idRuta))
                .collect(Collectors.toList());
    }

    boolean modificarHorario(String idHorario, String idRuta, String horaSalida, String diasSemana) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean eliminarHorario(String idHorario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}