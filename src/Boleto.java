/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jhost
 */
import java.util.Objects;

/**
 *
 * @author jhost
 */


public class Boleto {
    private String id;
    private String idRuta;
    private String idHorario;
    private String categoriaAsiento; // "premium", "ejecutivo", "estandar"
    private int numeroAsiento;
    private double pesoMaleta1;
    private double pesoMaleta2;

    public Boleto(String id, String idRuta, String idHorario, String categoriaAsiento, int numeroAsiento, double pesoMaleta1, double pesoMaleta2) {
        this.id = id;
        this.idRuta = idRuta;
        this.idHorario = idHorario;
        this.categoriaAsiento = categoriaAsiento;
        this.numeroAsiento = numeroAsiento;
        this.pesoMaleta1 = pesoMaleta1;
        this.pesoMaleta2 = pesoMaleta2;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public String getCategoriaAsiento() {
        return categoriaAsiento;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public double getPesoMaleta1() {
        return pesoMaleta1;
    }

    public double getPesoMaleta2() {
        return pesoMaleta2;
    }
}
