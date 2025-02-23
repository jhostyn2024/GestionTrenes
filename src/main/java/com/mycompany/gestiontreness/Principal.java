/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.Tren;

import java.io.File;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        // Crear un objeto Tren
        Tren tren = new Tren("Tren Express", "TX-001", 28, 1500.5);

        // Serializar el objeto a JSON
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el objeto a JSON y guardarlo en un archivo
            mapper.writeValue(new File("tren.json"), tren);
            System.out.println("Tren guardado en tren.json");

            // Leer el JSON desde el archivo y convertirlo a un objeto Tren
            Tren trenLeido = mapper.readValue(new File("tren.json"), Tren.class);
            System.out.println("Tren leído: " + trenLeido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}