/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jhost
 */
import java.util.ArrayList;
import java.util.List;

public class Vagon {
    private int numero;
    private int asientosEstandar;
    private int asientosEjecutivos;
    private int asientosPremium;
    private boolean tieneEspacioEquipaje;
    private List<Integer> asientosEstandarOcupados;
    private List<Integer> asientosEjecutivosOcupados;
    private List<Integer> asientosPremiumOcupados;

    public Vagon(int numero, int asientosEstandar, int asientosEjecutivos, int asientosPremium, boolean tieneEspacioEquipaje) {
        this.numero = numero;
        this.asientosEstandar = asientosEstandar;
        this.asientosEjecutivos = asientosEjecutivos;
        this.asientosPremium = asientosPremium;
        this.tieneEspacioEquipaje = tieneEspacioEquipaje;
        this.asientosEstandarOcupados = new ArrayList<>();
        this.asientosEjecutivosOcupados = new ArrayList<>();
        this.asientosPremiumOcupados = new ArrayList<>();
    }

    
    public int getNumero() {
        return numero;
    }

    public int getAsientosEstandar() {
        return asientosEstandar;
    }

    public int getAsientosEjecutivos() {
        return asientosEjecutivos;
    }

    public int getAsientosPremium() {
        return asientosPremium;
    }

    public boolean isTieneEspacioEquipaje() {
        return tieneEspacioEquipaje;
    }

    
    public int asignarAsiento(String categoria) {
        switch (categoria.toLowerCase()) {
            case "estandar":
                for (int i = 1; i <= asientosEstandar; i++) {
                    if (!asientosEstandarOcupados.contains(i)) {
                        asientosEstandarOcupados.add(i);
                        return i;
                    }
                }
                break;
            case "ejecutivo":
                for (int i = 1; i <= asientosEjecutivos; i++) {
                    if (!asientosEjecutivosOcupados.contains(i)) {
                        asientosEjecutivosOcupados.add(i);
                        return i;
                    }
                }
                break;
            case "premium":
                for (int i = 1; i <= asientosPremium; i++) {
                    if (!asientosPremiumOcupados.contains(i)) {
                        asientosPremiumOcupados.add(i);
                        return i;
                    }
                }
                break;
        }
        return -1; // No hay asientos disponibles
    }

    public boolean hayAsientosDisponibles(String categoria) {
        switch (categoria.toLowerCase()) {
            case "estandar":
                return asientosEstandarOcupados.size() < asientosEstandar;
            case "ejecutivo":
                return asientosEjecutivosOcupados.size() < asientosEjecutivos;
            case "premium":
                return asientosPremiumOcupados.size() < asientosPremium;
            default:
                return false;
        }
    }
}