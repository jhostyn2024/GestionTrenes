/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import java.time.LocalDateTime;
import java.util.List;

public class Boleto {
    private String idBoleto;
    private LocalDateTime fechaCompra;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String pasajeroNombre;
    private String pasajeroApellido;
    private String pasajeroId;
    private String tipoIdentificacion;
    private String direccion;
    private List<String> telefonos;
    private String idTren;
    private String idRuta;
    private String idHorario;
    private String lugar;
    private String categoriaPasajero;
    private double precio;
    private PersonaContacto contacto;
    private Equipaje equipaje;
    private boolean usado;

    public Boleto(String idTren, String idRuta, String idHorario, String pasajeroNombre, String pasajeroApellido,
                  String pasajeroId, String tipoIdentificacion, String direccion, List<String> telefonos,
                  String lugar, String categoriaPasajero, double precio, LocalDateTime fechaSalida,
                  LocalDateTime fechaLlegada, PersonaContacto contacto, Equipaje equipaje) {
        this.idBoleto = "BOL-" + System.currentTimeMillis();
        this.fechaCompra = LocalDateTime.now();
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.pasajeroNombre = pasajeroNombre;
        this.pasajeroApellido = pasajeroApellido;
        this.pasajeroId = pasajeroId;
        this.tipoIdentificacion = tipoIdentificacion;
        this.direccion = direccion;
        this.telefonos = telefonos;
        this.idTren = idTren;
        this.idRuta = idRuta;
        this.idHorario = idHorario;
        this.lugar = lugar;
        this.categoriaPasajero = categoriaPasajero;
        this.precio = precio;
        this.contacto = contacto;
        this.equipaje = equipaje;
        this.usado = false;
    }

    // Getters
    public String getIdBoleto() { return idBoleto; }
    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public String getPasajeroNombre() { return pasajeroNombre; }
    public String getPasajeroApellido() { return pasajeroApellido; }
    public String getPasajeroId() { return pasajeroId; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public String getDireccion() { return direccion; }
    public List<String> getTelefonos() { return telefonos; }
    public String getIdTren() { return idTren; }
    public String getIdRuta() { return idRuta; }
    public String getIdHorario() { return idHorario; }
    public String getLugar() { return lugar; }
    public String getCategoriaPasajero() { return categoriaPasajero; }
    public double getPrecio() { return precio; }
    public PersonaContacto getContacto() { return contacto; }
    public Equipaje getEquipaje() { return equipaje; }
    public boolean isUsado() { return usado; }

    // Setters
    public void setUsado(boolean usado) { this.usado = usado; }
}