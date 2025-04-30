/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestiontreness;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // Registrar el módulo para manejar LocalDateTime
        mapper.registerModule(new JavaTimeModule());
    }

    public static <T> void writeJson(String filePath, List<T> objects) {
        try {
            File file = new File(filePath);
            // Crear directorio si no existe
            file.getParentFile().mkdirs();
            mapper.writeValue(file, objects);
            System.out.println("Datos guardados en: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al guardar JSON en " + filePath + ": " + e.getMessage());
        }
    }

    public static <T> List<T> readJson(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Archivo no encontrado: " + filePath + ". Retornando lista vacía.");
                return new ArrayList<>();
            }
            T[] array = mapper.readValue(file, mapper.getTypeFactory().constructArrayType(clazz));
            System.out.println("Datos cargados desde: " + filePath);
            return new ArrayList<>(List.of(array));
        } catch (IOException e) {
            System.err.println("Error al leer JSON desde " + filePath + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}