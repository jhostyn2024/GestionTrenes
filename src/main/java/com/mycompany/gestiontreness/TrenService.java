/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

/**
 *
 * @author jhost
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrenService {
    private static final File archivo = new File("trenes.json");
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<Tren> cargarTrenes() {
        try {
            if (!archivo.exists()) return new ArrayList<>();
            return mapper.readValue(archivo, new TypeReference<List<Tren>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void guardarTrenes(List<Tren> trenes) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(archivo, trenes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
