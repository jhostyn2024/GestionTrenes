/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Boleto {
    @JsonProperty("idBoleto")
    private String idBoleto;

    @JsonProperty("idHorario")
    private String idHorario;

    @JsonProperty("idVagon")
    private String idVagon;

    @JsonProperty("cedula")
    private String cedula;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("estado")
    private String estado;

    public Boleto() {}

    public Boleto(String idBoleto, String idHorario, String idVagon, String cedula, String nombre, String apellido, String estado) {
        this.idBoleto = idBoleto;
        this.idHorario = idHorario;
        this.idVagon = idVagon;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
    }

    public String getIdBoleto() { return idBoleto; }
    public String getIdHorario() { return idHorario; }
    public String getIdVagon() { return idVagon; }
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEstado() { return estado; }

    public void setIdBoleto(String idBoleto) { this.idBoleto = idBoleto; }
    public void setIdHorario(String idHorario) { this.idHorario = idHorario; }
    public void setIdVagon(String idVagon) { this.idVagon = idVagon; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEstado(String estado) { this.estado = estado; }

    Iterable<Equipaje> getEquipajes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    boolean isUsado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}