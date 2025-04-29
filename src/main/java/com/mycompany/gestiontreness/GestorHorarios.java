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
        System.out.println("Horarios almacenados: " + horarios.size());
    }

    public boolean modificarHorario(String idHorario, String idRuta, String idTren, String fechaSalida, String fechaLlegada, String estado) {
        System.out.println("Modificando horario con ID: " + idHorario);
        for (Horario horario : horarios) {
            if (horario.getIdHorario().equals(idHorario)) {
                horario.setIdRuta(idRuta);
                horario.setIdTren(idTren);
                horario.setFechaSalida(fechaSalida);
                horario.setFechaLlegada(fechaLlegada);
                horario.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, horarios);
                System.out.println("Horario modificado y almacenado: " + idHorario);
                return true;
            }
        }
        System.out.println("Horario no encontrado para modificar: " + idHorario);
        return false;
    }

    public boolean eliminarHorario(String idHorario) {
        System.out.println("Eliminando horario con ID: " + idHorario);
        boolean removed = horarios.removeIf(horario -> horario.getIdHorario().equals(idHorario));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, horarios);
            System.out.println("Horario eliminado y almacenado: " + idHorario);
        } else {
            System.out.println("Horario no encontrado para eliminar: " + idHorario);
        }
        return removed;
    }

    public List<Horario> getHorarios() {
        return new ArrayList<>(horarios);
    }

    public List<Horario> getHorariosActivos() {
        return horarios.stream()
                .filter(horario -> "Activo".equals(horario.getEstado()))
                .collect(Collectors.toList());
    }

    boolean modificarHorario(String idHorario, String idRuta, String horaSalida, String diasSemana) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getHorariosPorRuta(String idRuta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}