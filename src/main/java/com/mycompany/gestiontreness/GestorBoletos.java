/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.util.ArrayList;
import java.util.List;

public class GestorBoletos {
    private static GestorBoletos instance;
    private List<Boleto> boletos;
    private static final String FILE_PATH = "data/boletos.json";

    private GestorBoletos() {
        boletos = JsonUtil.readJson(FILE_PATH, Boleto.class);
        System.out.println("GestorBoletos inicializado con " + boletos.size() + " boletos cargados");
    }

    public static synchronized GestorBoletos getInstance() {
        if (instance == null) {
            instance = new GestorBoletos();
        }
        return instance;
    }

    public void agregarBoleto(Boleto boleto) {
        System.out.println("Agregando boleto con ID: " + boleto.getIdBoleto());
        boletos.add(boleto);
        JsonUtil.writeJson(FILE_PATH, boletos);
        System.out.println("Boletos almacenados: " + boletos.size());
    }

    public boolean modificarBoleto(String idBoleto, String idHorario, String idVagon, String cedula, String nombre, String apellido, String estado) {
        System.out.println("Modificando boleto con ID: " + idBoleto);
        for (Boleto boleto : boletos) {
            if (boleto.getIdBoleto().equals(idBoleto)) {
                boleto.setIdHorario(idHorario);
                boleto.setIdVagon(idVagon);
                boleto.setCedula(cedula);
                boleto.setNombre(nombre);
                boleto.setApellido(apellido);
                boleto.setEstado(estado);
                JsonUtil.writeJson(FILE_PATH, boletos);
                System.out.println("Boleto modificado y almacenado: " + idBoleto);
                return true;
            }
        }
        System.out.println("Boleto no encontrado para modificar: " + idBoleto);
        return false;
    }

    public boolean eliminarBoleto(String idBoleto) {
        System.out.println("Eliminando boleto con ID: " + idBoleto);
        boolean removed = boletos.removeIf(boleto -> boleto.getIdBoleto().equals(idBoleto));
        if (removed) {
            JsonUtil.writeJson(FILE_PATH, boletos);
            System.out.println("Boleto eliminado y almacenado: " + idBoleto);
        } else {
            System.out.println("Boleto no encontrado para eliminar: " + idBoleto);
        }
        return removed;
    }

    public List<Boleto> getBoletos() {
        return new ArrayList<>(boletos);
    }

    Boleto buscarBoletoPorId(String idBoleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean marcarBoletoUsado(String idBoleto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}