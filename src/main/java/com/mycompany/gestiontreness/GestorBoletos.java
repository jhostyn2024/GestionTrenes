/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestorBoletos {
    private static GestorBoletos instance;
    private List<Boleto> boletos;

    private GestorBoletos() {
        boletos = new ArrayList<>();
    }

    public static synchronized GestorBoletos getInstance() {
        if (instance == null) {
            instance = new GestorBoletos();
        }
        return instance;
    }

    public void agregarBoleto(Boleto boleto) {
        boletos.add(boleto);
    }

    public Boleto buscarBoletoPorId(String idBoleto) {
        return boletos.stream()
                .filter(b -> b.getIdBoleto().equals(idBoleto))
                .findFirst()
                .orElse(null);
    }

    public List<Boleto> buscarBoletosPorEquipaje(String idEquipaje) {
        List<Boleto> resultados = new ArrayList<>();
        for (Boleto boleto : boletos) {
            for (Equipaje equipaje : boleto.getEquipajes()) {
                if (equipaje.getIdEquipaje().equals(idEquipaje)) {
                    resultados.add(boleto);
                    break;
                }
            }
        }
        return resultados;
    }

    public boolean validarBoleto(String idBoleto) {
        Boleto boleto = buscarBoletoPorId(idBoleto);
        if (boleto == null) {
            return false;
        }
        // Validar que el boleto no esté usado y que la fecha de salida sea válida
        return !boleto.isUsado() && boleto.getFechaSalida().isAfter(LocalDateTime.now());
    }

    public boolean marcarBoletoUsado(String idBoleto) {
        Boleto boleto = buscarBoletoPorId(idBoleto);
        if (boleto == null) {
            return false;
        }
        boleto.marcarBoletoUsado();
        return true;
    }

    public List<Boleto> getBoletos() {
        return new ArrayList<>(boletos);
    }
}