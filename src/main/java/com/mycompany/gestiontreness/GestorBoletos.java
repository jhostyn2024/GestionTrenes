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
        System.out.println("Boleto agregado - ID: " + boleto.getIdBoleto());
    }

    public List<Boleto> getBoletos() {
        return new ArrayList<>(boletos);
    }

    public Boleto buscarBoleto(String idBoleto) {
        return boletos.stream()
                .filter(b -> b.getIdBoleto().equals(idBoleto))
                .findFirst()
                .orElse(null);
    }

    public boolean validarBoleto(String idBoleto, String idTren, String idRuta, String idHorario) {
        Boleto boleto = buscarBoleto(idBoleto);
        if (boleto == null) {
            System.out.println("Boleto no encontrado - ID: " + idBoleto);
            return false;
        }
        if (boleto.isUsado()) {
            System.out.println("Boleto ya usado - ID: " + idBoleto);
            return false;
        }
        boolean valido = boleto.getIdTren().equals(idTren) &&
                         boleto.getIdRuta().equals(idRuta) &&
                         boleto.getIdHorario().equals(idHorario);
        System.out.println("Validación boleto - ID: " + idBoleto + ", Válido: " + valido);
        return valido;
    }

    public boolean marcarBoletoUsado(String idBoleto) {
        Boleto boleto = buscarBoleto(idBoleto);
        if (boleto != null && !boleto.isUsado()) {
            boleto.setUsado(true);
            System.out.println("Boleto marcado como usado - ID: " + idBoleto);
            return true;
        }
        System.out.println("No se pudo marcar boleto como usado - ID: " + idBoleto);
        return false;
    }

    public boolean actualizarBoleto(Boleto boletoActualizado) {
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getIdBoleto().equals(boletoActualizado.getIdBoleto())) {
                boletos.set(i, boletoActualizado);
                System.out.println("Boleto actualizado - ID: " + boletoActualizado.getIdBoleto());
                return true;
            }
        }
        System.out.println("No se encontró boleto para actualizar - ID: " + boletoActualizado.getIdBoleto());
        return false;
    }
}