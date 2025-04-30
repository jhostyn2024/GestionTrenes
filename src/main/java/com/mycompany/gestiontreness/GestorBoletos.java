/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        System.out.println("Boletos almacenados: " + boletos.size() + " [" + 
            boletos.stream().map(Boleto::getIdBoleto).collect(Collectors.joining(", ")) + "]");
    }

    public Boleto buscarBoletoPorId(String idBoleto) {
        System.out.println("Buscando boleto con ID: " + idBoleto);
        Boleto boleto = boletos.stream()
                .filter(b -> b.getIdBoleto().equals(idBoleto.trim()))
                .findFirst()
                .orElse(null);
        if (boleto == null) {
            System.out.println("Boleto no encontrado. IDs disponibles: [" + 
                boletos.stream().map(Boleto::getIdBoleto).collect(Collectors.joining(", ")) + "]");
        } else {
            System.out.println("Boleto encontrado: " + boleto.getIdBoleto());
        }
        return boleto;
    }

    public List<Boleto> buscarBoletosPorEquipaje(String idEquipaje) {
        System.out.println("Buscando boletos con equipaje ID: " + idEquipaje);
        List<Boleto> resultados = new ArrayList<>();
        for (Boleto boleto : boletos) {
            for (Equipaje equipaje : boleto.getEquipajes()) {
                if (equipaje.getIdEquipaje().equals(idEquipaje.trim())) {
                    resultados.add(boleto);
                    System.out.println("Boleto encontrado para equipaje: " + boleto.getIdBoleto());
                    break;
                }
            }
        }
        System.out.println("Total boletos encontrados para equipaje: " + resultados.size());
        return resultados;
    }

    public boolean validarBoleto(String idBoleto) {
        System.out.println("Validando boleto con ID: " + idBoleto);
        Boleto boleto = buscarBoletoPorId(idBoleto);
        if (boleto == null) {
            System.out.println("Boleto no encontrado para validación");
            return false;
        }
        boolean valido = !boleto.isUsado() && boleto.getFechaSalida().isAfter(LocalDateTime.now());
        System.out.println("Resultado de validación: " + valido);
        return valido;
    }

    public boolean marcarBoletoUsado(String idBoleto) {
        System.out.println("Marcando boleto como usado: " + idBoleto);
        Boleto boleto = buscarBoletoPorId(idBoleto);
        if (boleto == null) {
            System.out.println("Boleto no encontrado para marcar como usado");
            return false;
        }
        boleto.marcarBoletoUsado();
        JsonUtil.writeJson(FILE_PATH, boletos);
        System.out.println("Boleto marcado como usado: " + idBoleto);
        return true;
    }

    public List<Boleto> getBoletos() {
        return new ArrayList<>(boletos);
    }
}